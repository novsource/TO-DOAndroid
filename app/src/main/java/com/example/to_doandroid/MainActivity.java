package com.example.to_doandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlePage, subTitle, versionTitle;

    DatabaseReference reference;
    RecyclerView taskList; // Список с задачами
    ArrayList<Task> tasks; // ArrayList с тасками
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем по id
        titlePage = findViewById(R.id.titlePage);
        subTitle = findViewById(R.id.subTitle);
        versionTitle = findViewById(R.id.versionTitle);

        // добавляем свой шрифт
        /*Typeface TekoLight = Typeface.createFromAsset(getAssets(), "fonts/tekomedium.ttf");
        Typeface TekoMedium = Typeface.createFromAsset(getAssets(), "fonts/tekomedium.ttf");

        // Меняем шрифт у текста на главной панели

        titlePage.setTypeface(TekoMedium);
        subTitle.setTypeface(TekoMedium);
        versionTitle.setTypeface(TekoLight);*/
    }
}