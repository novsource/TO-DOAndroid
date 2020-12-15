package com.example.to_doandroid.View.ActionsWithTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.to_doandroid.MainActivity;
import com.example.to_doandroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskActivity extends AppCompatActivity {

    TextView btnClose;

    TextView taskDate, taskInfo;
    EditText taskDoesTitle, taskNote; // Название задачи и заметка
    CheckBox taskCB; // Статус задачи
    ImageView deleteTask; // Иконка удаления задачи

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Button btnUpdateTask;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        this.taskDoesTitle = findViewById(R.id.taskDoesTitle);
        this.taskNote = findViewById(R.id.taskNote);
        this.taskCB = findViewById(R.id.taskCB);
        this.taskDate = findViewById(R.id.taskDate);
        this.taskInfo = findViewById(R.id.taskInfo);

        this.taskDoesTitle.setText(getIntent().getStringExtra("taskDoesTitle"));
        this.taskNote.setText(getIntent().getStringExtra("taskNote"));
        this.taskCB.setChecked(getIntent().getBooleanExtra("taskCB", taskCB.isChecked()));
        this.taskDate.setText(getIntent().getStringExtra("taskDate"));
        this.taskInfo.setText("Создано " + getIntent().getStringExtra("taskWasCreated"));

        this.btnUpdateTask = findViewById(R.id.btnUpdateTask);
        this.btnClose = findViewById(R.id.btnClose);
        this.deleteTask = findViewById(R.id.deleteTaskIcon);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        String taskId = getIntent().getStringExtra("taskId");

        reference = FirebaseDatabase.getInstance().getReference().child("TaskList").child(firebaseUser.getUid()).child("Task " + taskId);

        this.btnUpdateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("taskDoesTitle").setValue(taskDoesTitle.getText().toString()); // Передаем название задачи
                        snapshot.getRef().child("taskNote").setValue(taskNote.getText().toString()); // Передаем заметку
                        snapshot.getRef().child("taskCB").setValue(taskCB.isChecked()); // Передаем статус задачи
                        snapshot.getRef().child("taskDate").setValue(taskDate.getText().toString()); // Передаем дату задачи

                        Intent intent = new Intent(EditTaskActivity.this, MainActivity.class);
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
                Intent intent = new Intent(EditTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        this.deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(EditTaskActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

    }
}