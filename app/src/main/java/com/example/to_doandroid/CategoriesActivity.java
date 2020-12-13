package com.example.to_doandroid;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    TextView workCategoryTitle, homeCategoryTitle, shopCategoryTitle;
    LinearLayout workCategoryLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // убираем вверхную полоску с названием программы
        setContentView(R.layout.categories);

        this.workCategoryTitle = findViewById(R.id.workCategoryTitle);
        this.workCategoryLayout = findViewById(R.id.workCategoryLayout);

        this.homeCategoryTitle = findViewById(R.id.homeCategoryTitle);
        this.shopCategoryTitle = findViewById(R.id.shopCategoryTitle);

        this.workCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CategoriesActivity.this);
    }
}
