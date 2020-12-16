package com.example.to_doandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_doandroid.Model.Task;
import com.example.to_doandroid.Model.Adapters.TaskAdapter;
import com.example.to_doandroid.View.ActionsWithTask.CreateNewTaskActivity;
import com.example.to_doandroid.View.CategoriesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView titlePage, subTitle, btnClose;
    CheckBox taskCB;

    Button btnAddTask;

    DatabaseReference reference; // Создаем DBReference для считывания и записи данных в Firebase
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    RecyclerView RVWithTasks; // Список с выполненными задачами

    ArrayList<Task> tasks;

    TaskAdapter taskAdapter; // Адаптер

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // убираем вверхную полоску с названием программы
        setContentView(R.layout.activity_main);

        // получаем TextView id
        this.titlePage = findViewById(R.id.titlePage);
        this.subTitle = findViewById(R.id.subTitle);

        // Статус выполнения задачи
        this.taskCB = findViewById(R.id.taskCB);

        //Кнопки выхода и добавления тасков
        this.btnAddTask = findViewById(R.id.btnAddTask);
        this.btnClose = findViewById(R.id.btnClose);

        this.RVWithTasks = findViewById(R.id.taskList);

        this.RVWithTasks.setLayoutManager(new LinearLayoutManager(this));

        this.RVWithTasks.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        this.tasks = new ArrayList<>();

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseUser = firebaseAuth.getCurrentUser();

        //Получаем данные из Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("TaskList").child(firebaseUser.getUid()).child("Work Tasks Category"); //название узла в Firebase для конкретного пользователя
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Извлекаем данные из узла

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) { //получили всех потомков
                    Task task = dataSnapshot1.getValue(Task.class); // получили значение
                    tasks.add(task);
                }
                taskAdapter= new TaskAdapter(MainActivity.this, tasks);
                RVWithTasks.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Нет задач", Toast.LENGTH_SHORT).show();
            }
        });

        dragTask(); // Перетаскивание объектов в RecyclerView

        this.btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNewTaskActivity.class);
                startActivity(intent);
            }
        });

        this.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

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

        helper.attachToRecyclerView(RVWithTasks);
    }


}