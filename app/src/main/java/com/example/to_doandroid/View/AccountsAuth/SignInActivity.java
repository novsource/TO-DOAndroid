package com.example.to_doandroid.View.AccountsAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_doandroid.R;
import com.example.to_doandroid.View.CategoriesActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText emailAuthentication, password; // E-mail и пароль для входа
    private Button btnSignIn; // Кнопка "Войти"
    private TextView signUp;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        this.progressDialog = new ProgressDialog(this);

        // Получаем id

        this.emailAuthentication = findViewById(R.id.emailAuthentication);
        this.password = findViewById(R.id.passwordAuthentication);
        this.btnSignIn = findViewById(R.id.login);
        this.signUp = findViewById(R.id.signUp);

        firebaseAuth = FirebaseAuth.getInstance();

        this.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginInFirebase();
            }
        });

        this.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isEmailCorrect(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void LoginInFirebase() {
        String email = this.emailAuthentication.getText().toString();
        String password = this.password.getText().toString();

        // Проверка полей
        if (TextUtils.isEmpty(email)) {
            this.emailAuthentication.setError("Введите свой email...");
            return;
        }

        else if (TextUtils.isEmpty(password)) {
            this.password.setError("Введите пароль...");
            return;
        }

        else if (password.length() < 4) {
            this.password.setError("Пароль должен быть длинее 4 символов");
        }

        else if (!isEmailCorrect(email)) {
            this.emailAuthentication.setError("Неверный e-mail!");
        }

        progressDialog.setMessage("Пожалуйста подождите. Идет вход в аккаунт...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignInActivity.this, "Вы вошли в аккаунт!", Toast.LENGTH_LONG);
                    Intent intent = new Intent(SignInActivity.this, CategoriesActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(SignInActivity.this, "Ошибка входа! Попробуйте еще раз!", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}
