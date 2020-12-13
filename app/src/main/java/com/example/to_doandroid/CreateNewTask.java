package com.example.to_doandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class CreateNewTask extends AppCompatActivity {

    TextView taskDate; // дата задачи
    EditText taskDoesTitle, taskNote; // Название задачи и заметка
    CheckBox taskCB; // Статус задачи

    LinearLayout addTaskDate; // Layout для календаря

    Button btnSaveTask, btnClose; // Кнопки сохранения задачи и выхода из активити
    DatabaseReference reference;
    Integer taskId = new Random().nextInt(); // генерируем новый id ля задачи

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task);

        this.taskDoesTitle = findViewById(R.id.taskDoesTitle);
        this.taskNote = findViewById(R.id.taskNote);
        this.taskCB = findViewById(R.id.taskCB);
        this.taskDate = findViewById(R.id.taskDate);

        this.addTaskDate = findViewById(R.id.addTaskDate);

        this.btnSaveTask = findViewById(R.id.btnSaveTask);
        this.btnClose = findViewById(R.id.btnClose);

        // Добавление даты из календаря
        String date = getIntent().getStringExtra("date");
        if (date != null) {
            this.taskDate.setText(date);
        }

        this.addTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewTask.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        this.btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Добавляем задачу в базу данных
                reference = FirebaseDatabase.getInstance().getReference().child("TaskList").child("Task " + taskId);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("taskDoesTitle").setValue(taskDoesTitle.getText().toString()); // Передаем название задачи
                        snapshot.getRef().child("taskNote").setValue(taskNote.getText().toString()); // Передаем заметку
                        snapshot.getRef().child("taskCB").setValue(taskCB.isChecked()); // Передаем статус задачи
                        snapshot.getRef().child("taskDate").setValue(taskDate.getText().toString()); // Передаем дату задачи

                        Intent intent = new Intent(CreateNewTask.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    //Кнопка назад
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}