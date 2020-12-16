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
import com.example.to_doandroid.Model.User;
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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    ArrayList<User> users;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    DatabaseReference reference;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.connected_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Email.setText(users.get(position).getEmail());
        holder.status.setChecked(users.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView Email;
        CheckBox status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Email = (TextView) itemView.findViewById(R.id.userEmail);
            status = (CheckBox) itemView.findViewById(R.id.userCheck);

            status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == true) {

                    }
                }
            });
        }

    }
}
