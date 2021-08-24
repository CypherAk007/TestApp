package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userName,Password,phonenumber;
    private Button signInBtn;
    private TextView registerTv;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.userName);
        Password = findViewById(R.id.PasswordET);
        signInBtn = findViewById(R.id.SignInBtn);
        phonenumber = findViewById(R.id.phonenumber);
        registerTv = findViewById(R.id.registerTV);
        db = new DBHelper(this);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = Password.getText().toString();
                String phonenum = phonenumber.getText().toString();

                if(user.equals("") && phonenum.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter required fields.", Toast.LENGTH_SHORT).show();
                }
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkUserPassword = db.checkuserNamePassword(user,pass);
                    if(checkUserPassword){
                        Toast.makeText(LoginActivity.this, "Sign in Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,VideoActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

                if (phonenum.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkPhonePassword = db.checkPhonenumberPassword(phonenum,pass);
                    if(checkPhonePassword){
                        Toast.makeText(LoginActivity.this, "Sign in Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,VideoActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }
}