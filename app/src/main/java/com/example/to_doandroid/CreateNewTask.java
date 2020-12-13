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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class CreateNewTask extends AppCompatActivity {

    EditText taskDoesTitle, taskNote;
    CheckBox taskCB;

    Button btnSaveTask, btnClose;
    DatabaseReference reference;
    Integer taskId = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task);

        this.taskDoesTitle = findViewById(R.id.taskDoesTitle);
        this.taskNote = findViewById(R.id.taskNote);
        this.taskCB = findViewById(R.id.taskCB);


        this.btnSaveTask = findViewById(R.id.btnSaveTask);
        this.btnClose = findViewById(R.id.btnClose);

        this.btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Добавляем задачу в базу данных
                reference = FirebaseDatabase.getInstance().getReference().child("TaskList").child("Task " + taskId);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("taskDoesTitle").setValue(taskDoesTitle.getText().toString());
                        snapshot.getRef().child("taskNote").setValue(taskNote.getText().toString());
                        snapshot.getRef().child("taskCB").setValue(taskCB.isChecked());

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