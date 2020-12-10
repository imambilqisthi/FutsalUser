package com.example.futsaluser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText ETUserEmail, ETPassword;
    private CheckBox RCheckBoxPassword;
    private Button BtnLogin;
    private TextView btn_Register;
    private FirebaseAuth mAuth;
    private Intent homeAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ETPassword = findViewById(R.id.ETPassword);
        ETUserEmail = findViewById(R.id.ETUserMail);
        RCheckBoxPassword = findViewById(R.id.CBPassword);
        BtnLogin = findViewById(R.id.BTNLogin);
        btn_Register = findViewById(R.id.btn_Register);
        mAuth = FirebaseAuth.getInstance();
        homeAct = new Intent(this, HomeActivity.class);
        RCheckBoxPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RCheckBoxPassword.isChecked()) {
                    ETPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ETPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnLogin.setVisibility(View.VISIBLE);


                final String mail = ETUserEmail.getText().toString();
                final String password = ETPassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    showmessage("Email dan Kata Sandi Tidak Boleh Kosong");
                } else {
                    sigIn(mail, password);
                }

            }
        });

    }
    private void sigIn(String mail, String password) {
        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    BtnLogin.setVisibility(View.VISIBLE);
                    updateUI();
                } else {
                    Toast.makeText(LoginActivity.this, "Pastikan Email, Kata Sandi Benar dan Pastikan Perangkat Terhubung Dengan Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showmessage(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    private void updateUI() {
        startActivity(homeAct);
        finish();
        }
    }


