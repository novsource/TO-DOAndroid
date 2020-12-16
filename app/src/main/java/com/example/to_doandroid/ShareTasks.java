package com.example.to_doandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.to_doandroid.Model.Adapters.TaskAdapter;
import com.example.to_doandroid.Model.Adapters.UserAdapter;
import com.example.to_doandroid.Model.Task;
import com.example.to_doandroid.Model.User;
import com.example.to_doandroid.View.CategoriesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShareTasks extends AppCompatActivity {

    TextView btnClose;

    Button btnAddNewUser;

    DatabaseReference reference; // Подключенные пользователи
    DatabaseReference userReference;

    CheckBox userCheck;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    RecyclerView RVWithUsers;
    RecyclerView RVWithTasks;

    ArrayList<Task> tasks;
    ArrayList<User> users;

    TaskAdapter taskAdapter;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_tasks);

        this.btnClose = findViewById(R.id.btnClose);
        this.btnAddNewUser = findViewById(R.id.btnAddUser);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        this.userCheck = findViewById(R.id.userCheck);

        //this.userCheck.setVisibility(View.INVISIBLE);

        this.RVWithTasks = findViewById(R.id.shareTaskList);
        this.RVWithUsers = findViewById(R.id.usersList);

        this.RVWithUsers.setLayoutManager(new LinearLayoutManager(this));
        this.RVWithTasks.setLayoutManager(new LinearLayoutManager(this));

        this.RVWithTasks.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        this.RVWithUsers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        this.tasks = new ArrayList<>();
        this.users = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference().child(firebaseUser.getUid()).child("Connected Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                    User user = childDataSnapshot.getValue(User.class);
                    users.add(user);
                }
                userAdapter = new UserAdapter(ShareTasks.this, users);
                RVWithUsers.setAdapter(userAdapter);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        userReference = FirebaseDatabase.getInstance().getReference().child(firebaseUser.getUid()).child("Share Task Category");
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) { //получили всех потомков
                    Task task = dataSnapshot1.getValue(Task.class); // получили значение
                    tasks.add(task);
                }
                taskAdapter= new TaskAdapter(ShareTasks.this, tasks);
                RVWithTasks.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        this.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareTasks.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

        this.btnAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareTasks.this, AddNewUser.class);
                startActivity(intent);
            }
        });


    }
}