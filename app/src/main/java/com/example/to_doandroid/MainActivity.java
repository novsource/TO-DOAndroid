package com.example.to_doandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView titlePage, subTitle, versionTitle;

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