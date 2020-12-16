package com.example.to_doandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.to_doandroid.View.CategoriesActivity;

public class ShareTasks extends AppCompatActivity {

    TextView btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_tasks);

        this.btnClose = findViewById(R.id.btnClose);

        this.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareTasks.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });
    }
}