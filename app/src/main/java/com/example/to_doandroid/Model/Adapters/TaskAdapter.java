package com.example.to_doandroid.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.taskDoesTitle.setText(tasks.get(position).getTaskDoesTitle());
        holder.taskCB.setChecked(tasks.get(position).getTaskCB());
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskDoesTitle;
        CheckBox taskCB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDoesTitle = (TextView) itemView.findViewById(R.id.taskDoesTitle);
            taskCB = (CheckBox) itemView.findViewById(R.id.taskCB);
        }
    }
}
