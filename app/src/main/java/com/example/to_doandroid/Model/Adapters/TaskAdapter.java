package com.example.to_doandroid.Model.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doandroid.MainActivity;
import com.example.to_doandroid.View.ActionsWithTask.EditTaskActivity;
import com.example.to_doandroid.Model.Task;
import com.example.to_doandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    Context context;
    ArrayList<Task> tasks;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    DatabaseReference reference;

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
        String taskDoesTitle = tasks.get(position).getTaskDoesTitle();
        String taskNote = tasks.get(position).getTaskNote();
        String taskDate = tasks.get(position).getTaskDate();
        Boolean taskCB = tasks.get(position).getTaskCB();
        String taskId = String.valueOf(tasks.get(position).getTaskId());
        String taskWasCreated = tasks.get(position).getTaskWasCreated();

        holder.taskDate.setText(tasks.get(position).getTaskDate());
        holder.taskDoesTitle.setText(tasks.get(position).getTaskDoesTitle());
        holder.taskCB.setChecked(tasks.get(position).getTaskCB());
        holder.taskNote.setText(tasks.get(position).getTaskNote());

        // Вызов редактора задачи по нажатию в RecyclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTaskActivity.class);

                // Передаем данные форме редактирования
                intent.putExtra("taskDoesTitle", taskDoesTitle);
                intent.putExtra("taskNote", taskNote);
                intent.putExtra("taskDate", taskDate);
                intent.putExtra("taskCB", taskCB);
                intent.putExtra("taskId", taskId);
                intent.putExtra("taskWasCreated", taskWasCreated);

                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskDoesTitle, taskDate, taskNote, taskId;
        CheckBox taskCB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDoesTitle = (TextView) itemView.findViewById(R.id.taskDoesTitle);
            taskCB = (CheckBox) itemView.findViewById(R.id.taskCB);
            taskDate = (TextView) itemView.findViewById(R.id.taskDate);
            taskNote = (TextView) itemView.findViewById(R.id.taskNote);

            /*taskCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    reference = FirebaseDatabase.getInstance().getReference().child("TaskList").child(firebaseUser.getUid()).child("Work Tasks Category").child("Task " + "1722400447");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            snapshot.getRef().child("taskCB").setValue(isChecked);// Передаем статус задачи
                            return;
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                    return;
                }
            });*/
        }


    }
}
