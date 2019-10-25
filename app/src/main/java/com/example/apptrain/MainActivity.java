package com.example.apptrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    //Задание начальных переменных
    private FirebaseAuth mAuth;
    //private FirebaseDatabase db;
    //private DatabaseReference users;
    private Button btnSignIn, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // users = db.getReference()
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Нажатие кнопки Войти
        btnSignIn = findViewById(R.id.btnSign_in);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSignInWindow();
            }
        });
        //Нажатие кнопки зарегистрироваться
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRegisterWindow();
            }
        });

    }

    //Показ окна с регистрацией
    private void ShowRegisterWindow(){


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите все данные для регистрации");


        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window,null);
        dialog.setView(register_window);
        //Получаем поля для регистрации
        final MaterialEditText email = register_window.findViewById(R.id.RegisterEmail_field);
        final MaterialEditText password = register_window.findViewById(R.id.RegisterPassword_field);
        final MaterialEditText login = register_window.findViewById(R.id.RegisterLogin_field);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(email.getText().toString()) ||
                    TextUtils.isEmpty(login.getText().toString()) ||
                        TextUtils.isEmpty(password.getText().toString())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Заполните все поля!")
                            .setCancelable(false)
                            .setNegativeButton("ОК",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else{
                    try {
                        mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(MainActivity.this,ShowCase.class);
                                            startActivity(intent);

                                        }
                                        else if(task.getException().getMessage() == "The email address is already in use by another account."){
                                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                            builder.setTitle("Пользователь с таким Email уже существует")
                                                    .setCancelable(false)
                                                    .setNegativeButton("ОК",
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        }
                                        else
                                            {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                            builder.setTitle("Некорректно введён Email")
                                                    .setCancelable(false)
                                                    .setNegativeButton("ОК",
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        }
                                    }
                                });
                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this,"Ошибка: "+ e,Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
        dialog.show();
    }
    private void ShowSignInWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите все данные для регистрации");


        LayoutInflater inflater = LayoutInflater.from(this);
        View signInWindow = inflater.inflate(R.layout.signin_window,null);
        dialog.setView(signInWindow);

        final MaterialEditText email = signInWindow.findViewById(R.id.SignInEmail_field);
        final MaterialEditText password = signInWindow.findViewById(R.id.SignPassword_field);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(email.getText().toString())||
                        TextUtils.isEmpty(password.getText().toString())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Заполните все поля!")
                            .setCancelable(false)
                            .setNegativeButton("ОК",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    try {
                        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            //FirebaseUser user = mAuth.getCurrentUser();
                                            startActivity(new Intent(new Intent(MainActivity.this, ShowCase.class)));
                                        }
                                        else{
                                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                            builder.setTitle("Неверный логин или пароль")
                                                    .setCancelable(false)
                                                    .setNegativeButton("ОК",
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        }
                                    }
                                });
                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this,"Ошибка: "+ e,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        dialog.show();
    }
}
