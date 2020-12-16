package com.example.to_doandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.to_doandroid.Model.User;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewUser extends AppCompatActivity {

    Button btnAddNewUser;
    EditText emailNewUser;
    DatabaseReference reference;

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        this.emailNewUser = findViewById(R.id.emailNewUser);
        this.btnAddNewUser = findViewById(R.id.btnAddUser);

        this.btnAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewUser();
            }
        });
    }

    private boolean isEmailCorrect(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void AddNewUser() {
        String email = this.emailNewUser.getText().toString().trim();
        Map<String, Object> taskMap = new HashMap<>();
        reference = FirebaseDatabase.getInstance().getReference();
        if (isEmailCorrect(email)) {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                        DatabaseReference objRef = reference.child(childDataSnapshot.getKey());
                        objRef.updateChildren(taskMap);
                        DataSnapshot emailSnapshot = childDataSnapshot.child("Email");
                        if (emailSnapshot.getValue().toString().equals(email)) {
                            firebaseAuth = FirebaseAuth.getInstance();
                            firebaseUser = firebaseAuth.getCurrentUser();
                            reference = FirebaseDatabase.getInstance().getReference().child(firebaseUser.getUid());
                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot addSnapshot) {
                                    addSnapshot.getRef().child("Connected Users").child(childDataSnapshot.getKey()).child("UserId").setValue(childDataSnapshot.getKey());
                                    addSnapshot.getRef().child("Connected Users").child(childDataSnapshot.getKey()).child("Email").setValue(emailSnapshot.getValue().toString());
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        else {
            this.emailNewUser.setError("Неверная почта");
        }
        Intent intent = new Intent(AddNewUser.this, ShareTasks.class);
        startActivity(intent);
    }
}