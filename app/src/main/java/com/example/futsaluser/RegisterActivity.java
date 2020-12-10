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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText ETRegisterNama,ETRegisterEmail,ETRegisterPassword,ETRegisterKonfirmasiPassword;
    private CheckBox RShowPassword;
    private Button Btn_Register;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ETRegisterNama = findViewById(R.id.ETRegisterNama);
        ETRegisterEmail = findViewById(R.id.ETRegisterEmail);
        ETRegisterPassword = findViewById(R.id.ETRegisterPassword);
        ETRegisterKonfirmasiPassword= findViewById(R.id.ETRegisterKonfirmasiPassword);
        RShowPassword = findViewById(R.id.RShowPassword);
        progressBar = findViewById(R.id.progressBar);
        Btn_Register = findViewById(R.id.Btn_Register);
        RShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RShowPassword.isChecked()){
                    ETRegisterPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ETRegisterKonfirmasiPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    ETRegisterPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ETRegisterKonfirmasiPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        //LOGIC REGISTER
        mAuth = FirebaseAuth.getInstance();
        Btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn_Register.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                final String name = ETRegisterNama.getText().toString();
                final String email = ETRegisterEmail.getText().toString();
                final String password = ETRegisterPassword.getText().toString();
                final String password2 = ETRegisterKonfirmasiPassword.getText().toString();
                if ( name.isEmpty() || email.isEmpty() || password.isEmpty() || !password.equals(password2) ){

                    //jika user tidak menginputkan semua field akan muncul pesan error berikut
                    showMessage("Data Tidak Boleh Kosong");
                    Btn_Register.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);


                }else{
                    //jika semua field telah terisi oleh user maka selanjutnya akan membuat akun

                    CreateUserAccount(name,email,password);
                }
            }
        });
    }

    private void CreateUserAccount(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //kondisi jika user akun berhasil dibuat
                            showMessage("Akun Berhasil Dibuat");
                            updateUI();
                        }else{
                            //kondisi jika user akun gagal dibuat
                            showMessage("Akun Tidak Berhasil Dibuat ,Coba Lagi !!" + task.getException().getMessage());
                            Btn_Register.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    private void updateUI() {
        Intent loginactivity = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(loginactivity);
        finish();
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }
}