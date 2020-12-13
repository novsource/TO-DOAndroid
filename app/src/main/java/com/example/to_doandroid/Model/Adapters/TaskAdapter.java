package com.example.to_doandroid.Model.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doandroid.EditTaskActivity;
import com.example.to_doandroid.Model.Task;
import com.example.to_doandroid.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    Context context;
    ArrayList<Task> tasks;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.task_does, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.taskDate.setText(tasks.get(position).getTaskDate());
        holder.taskDoesTitle.setText(tasks.get(position).getTaskDoesTitle());
        holder.taskCB.setChecked(tasks.get(position).getTaskCB());
        holder.taskNote.setText(tasks.get(position).getTaskNote());

        // Вызов редактора задачи по нажатию в RecyclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTaskActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskDoesTitle, taskDate, taskNote;
        CheckBox taskCB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDoesTitle = (TextView) itemView.findViewById(R.id.taskDoesTitle);
            taskCB = (CheckBox) itemView.findViewById(R.id.taskCB);
            taskDate = (TextView) itemView.findViewById(R.id.taskDate);
            taskNote = (TextView) itemView.findViewById(R.id.taskNote);
        }
    }
}
