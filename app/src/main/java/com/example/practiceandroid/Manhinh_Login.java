package com.example.practiceandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import android.os.Bundle;

public class Manhinh_Login extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    String tk = "long";
    String pw= "1234";

    //Declaration TextInputLayout

    //Declaration Button
    ImageButton btnLogin;

    //Declaration SqliteHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__login);
        btnLogin = (ImageButton) findViewById(R.id.imageButton2);
        editTextEmail=(EditText) findViewById(R.id.editTextuser);
        editTextPassword=(EditText) findViewById(R.id.editTextPass);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextEmail.getText().toString().equals(tk)&&editTextPassword.getText().toString().equals(pw)) {
                    Intent mhhome = new Intent(Manhinh_Login.this, Manhinh_Home.class);
                    startActivity(mhhome);
                }
            }
        });
    }
}