package com.example.to_doandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView titlePage, subTitle;
    CheckBox taskCB;

    DatabaseReference reference; // Создаем DBReference для считывания и записи данных в Firebase
    RecyclerView taskRecyclerView; // Список с задачами
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Task> tasks; // ArrayList с тасками
    TaskAdapter taskAdapter; // Адаптер



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // убираем вверхную полоску с названием программы
        setContentView(R.layout.activity_main);

        // получаем по id
        this.titlePage = findViewById(R.id.titlePage);
        this.subTitle = findViewById(R.id.subTitle);
        this.taskCB = findViewById(R.id.taskCB);

        // добавляем свой шрифт
      /*  Typeface TekoLight = Typeface.createFromAsset(getAssets(), "res/font/tekomedium.ttf");
        Typeface TekoMedium = Typeface.createFromAsset(getAssets(), "res/font/tekomedium.ttf");

        // Меняем шрифт у текста на главной панели
        this.titlePage.setTypeface(TekoMedium);
        this.subTitle.setTypeface(TekoMedium);
        this.versionTitle.setTypeface(TekoLight);*/

        this.layoutManager = new LinearLayoutManager(this);

        this.taskRecyclerView = findViewById(R.id.taskList);
        this.taskRecyclerView.setLayoutManager(this.layoutManager);
        this.tasks = new ArrayList<Task>();

        //Получаем данные из Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("TO-DOAndroid"); //название главного узла в Firebase
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Извлекаем данные из узла
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) { //получили всех потомков
                    Task task = dataSnapshot1.getValue(Task.class); // получили значение
                    tasks.add(task); // добавили в список задач
                }
                taskAdapter = new TaskAdapter(MainActivity.this, tasks);
                taskRecyclerView.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
            }
        });

        this.taskRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        dragTask();
    }

    //Метод для перетаскивания тасков
    private void dragTask() {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                int positionDragged = dragged.getAdapterPosition();
                int positionTarget = target.getAdapterPosition();

                Collections.swap(tasks, positionDragged, positionTarget);

                taskAdapter.notifyItemMoved(positionDragged, positionTarget);

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });

        helper.attachToRecyclerView(taskRecyclerView);
    }
}