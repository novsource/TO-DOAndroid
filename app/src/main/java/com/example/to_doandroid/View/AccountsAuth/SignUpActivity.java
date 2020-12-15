package com.example.to_doandroid.View.AccountsAuth;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.to_doandroid.R;
import com.example.to_doandroid.View.CategoriesActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText emailAuthentication, passwordReg1, passwordReg2; // E-mail и пароль для регистрации
    private Button btnRegister; // Кнопка "Зарегистрироваться"
    private TextView signIn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.progressDialog = new ProgressDialog(this);

        // Получаем id

        this.emailAuthentication = findViewById(R.id.emailAuthentication);
        this.passwordReg1 = findViewById(R.id.passwordReg1);
        this.passwordReg2 = findViewById(R.id.passwordReg2);
        this.btnRegister = findViewById(R.id.btnRegister);
        this.signIn = findViewById(R.id.signIn);

        firebaseAuth = FirebaseAuth.getInstance();

        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterInFirebase();
            }
        });

        this.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean isEmailCorrect(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void RegisterInFirebase() {
        String email = this.emailAuthentication.getText().toString();
        String password1 = this.passwordReg1.getText().toString();
        String password2 = this.passwordReg2.getText().toString();

        // Проверка полей
        if (TextUtils.isEmpty(email)) {
            this.emailAuthentication.setError("Введите свой email...");
            return;
        }

        else if (TextUtils.isEmpty(password1)) {
            this.passwordReg1.setError("Введите пароль...");
            return;
        }

        else if (TextUtils.isEmpty(password2)) {
            this.passwordReg2.setError("Подтвердите пароль...");
            return;
        }

        else if (!password1.equals(password2)) {
            this.passwordReg2.setError("Неверный пароль!");
        }

        else if (password1.length() < 4) {
            this.passwordReg1.setError("Пароль должен быть длинее 4 символов");
        }

        else if (!isEmailCorrect(email)) {
            this.emailAuthentication.setError("Неверный e-mail!");
        }

        progressDialog.setMessage("Пожалуйста подождите. Идет регистрация аккаунта...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Аккаунт успешно зарегистрирован!", Toast.LENGTH_LONG);
                    Intent intent = new Intent(SignUpActivity.this, CategoriesActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Ошибка регистрации! Попробуйте еще раз!", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}
