package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText userName, Password, name, phonenumber;
    private Button RegisterBtn;
    private TextView LoginTv;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        Password = findViewById(R.id.PasswordET);
        RegisterBtn = findViewById(R.id.RegisterBtn);
        LoginTv = findViewById(R.id.LoginTV);
        name = findViewById(R.id.Name);
        phonenumber = findViewById(R.id.phoneNumber);
        db = new DBHelper(this);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = userName.getText().toString().trim();
                String pass = Password.getText().toString().trim();
                String phonenum = phonenumber.getText().toString().trim();
                String uname = name.getText().toString().trim();

                //validation

                boolean check = validate(uname, pass, phonenum, user);


                if (user.equals("") || pass.equals("") || phonenum.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    if (check) {
                        Boolean checkUser = db.checkUserName(user);
                        if (checkUser == false) {
                            Boolean insert = db.insertData(user, pass, phonenum);
                            if (insert) {
                                Toast.makeText(MainActivity.this, "Registered Success!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already exists Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        LoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private Boolean validate(String uname, String pass, String phonenum, String user) {
        if (uname.length() < 3 || uname.isEmpty()) {
            name.requestFocus();
            name.setError("Provide name more than 3 letters");
            return false;
        } else if (user.isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(user).matches())) {
            userName.requestFocus();
            userName.setError("invalide email");
            return false;
        } else if (phonenum.length() != 10) {
            phonenumber.requestFocus();
            phonenumber.setError("Invalid phone number");
            return false;
        } else if (!(validatePassword(pass)) && pass.length() < 6) {
            Password.requestFocus();
            Password.setError("Enter password with a number and special char");
            Toast.makeText(MainActivity.this, "Enter password with a number,special char and more than 6 characters ", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;

    }


    private boolean validatePassword(String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
}

