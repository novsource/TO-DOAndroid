package com.example.to_doandroid.View.ActionsWithTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.to_doandroid.View.CalendarActivity;
import com.example.to_doandroid.MainActivity;
import com.example.to_doandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class CreateNewTaskActivity extends AppCompatActivity {

    TextView taskDate; // дата задачи
    TextView btnClose; // стрелка назад
    EditText taskDoesTitle, taskNote; // Название задачи и заметка
    CheckBox taskCB; // Статус задачи

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    LinearLayout addTaskDate; // Layout для календаря

    Button btnSaveTask; // Кнопки сохранения задачи и выхода из активити
    DatabaseReference reference;
    Integer taskId = new Random().nextInt(); // генерируем id для задачи

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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        // Добавление даты из календаря
        String date = getIntent().getStringExtra("date");
        if (date != null) {
            this.taskDate.setText(date);
        }

        this.addTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewTaskActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        this.btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Добавляем задачу в базу данных
                reference = FirebaseDatabase.getInstance().getReference().child("TaskList").child(firebaseUser.getUid()).child("Task " + taskId);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("taskId").setValue(taskId);
                        snapshot.getRef().child("taskDoesTitle").setValue(taskDoesTitle.getText().toString()); // Передаем название задачи
                        snapshot.getRef().child("taskNote").setValue(taskNote.getText().toString()); // Передаем заметку
                        snapshot.getRef().child("taskCB").setValue(taskCB.isChecked()); // Передаем статус задачи
                        snapshot.getRef().child("taskDate").setValue(taskDate.getText().toString()); // Передаем дату задачи

                        Intent intent = new Intent(CreateNewTaskActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        this.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}