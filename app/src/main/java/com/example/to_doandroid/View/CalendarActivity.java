package com.example.to_doandroid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import com.example.to_doandroid.R;
import com.example.to_doandroid.View.ActionsWithTask.CreateNewTaskActivity;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        this.calendarView = findViewById(R.id.calendarView);

        this.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = String.format("%d/%d/%d", year, month, dayOfMonth);

                Intent intent = new Intent(CalendarActivity.this, CreateNewTaskActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}