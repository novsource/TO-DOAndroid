package com.example.to_doandroid.View;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.to_doandroid.MainActivity;
import com.example.to_doandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CategoriesActivity extends AppCompatActivity {

    TextView workCategoryTitle, homeCategoryTitle, shopCategoryTitle, accountEmail;
    LinearLayout workCategoryLayout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // убираем вверхную полоску с названием программы
        setContentView(R.layout.categories);

        this.workCategoryTitle = findViewById(R.id.workCategoryTitle);
        this.workCategoryLayout = findViewById(R.id.workCategoryLayout);

        this.homeCategoryTitle = findViewById(R.id.homeCategoryTitle);
        this.shopCategoryTitle = findViewById(R.id.shopCategoryTitle);
        this.accountEmail = findViewById(R.id.accountEmail);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        this.accountEmail.setText(firebaseUser.getEmail());

        this.workCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
