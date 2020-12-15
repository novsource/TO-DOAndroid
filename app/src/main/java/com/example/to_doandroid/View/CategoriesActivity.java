package com.example.to_doandroid.View;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.to_doandroid.MainActivity;
import com.example.to_doandroid.Model.Adapters.TaskAdapter;
import com.example.to_doandroid.Model.Task;
import com.example.to_doandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CategoriesActivity extends AppCompatActivity {

    TextView workCategoryTitle, homeCategoryTitle, shopCategoryTitle, accountEmail, workTaskCount;
    LinearLayout workCategoryLayout;

    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // убираем вверхную полоску с названием программы
        setContentView(R.layout.categories);

        this.workCategoryTitle = findViewById(R.id.workCategoryTitle);
        this.workCategoryLayout = findViewById(R.id.workCategoryLayout);
        this.workTaskCount = findViewById(R.id.workCategoryTaskSize);

        this.homeCategoryTitle = findViewById(R.id.homeCategoryTitle);
        this.shopCategoryTitle = findViewById(R.id.shopCategoryTitle);
        this.accountEmail = findViewById(R.id.accountEmail);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        this.accountEmail.setText(firebaseUser.getEmail());


        //Получаем данные из Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("TaskList").child(firebaseUser.getUid()); //название узла в Firebase для конкретного пользователя
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) { //получили всех потомков
                    count++;
                }
                workTaskCount.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Нет задач", Toast.LENGTH_SHORT).show();
            }
        });

        this.workCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
