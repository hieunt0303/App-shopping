package com.example.practiceandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practiceandroid.reset_passwork.JavaMailAPI;

public class Manhinh_Mailreset extends AppCompatActivity {

    public EditText Email;
    public ImageButton btn;
    public static String email;
    public static int Code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__mailreset);
        Email = (EditText) findViewById(R.id.editTextEmail);
        btn = (ImageButton) findViewById(R.id.imageButtonReset);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
                Intent mh = new Intent(Manhinh_Mailreset.this,Manhinh_Resetpass.class);
                startActivity(mh);
            }
        });
    }
    private void sendMail() {
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        Code = code;
        String mail = Email.getText().toString().trim();
        email = mail;
        String message = "Mã để cài đặt lại mật khẩu của bạn là : "+ code;
        String subject = "Tìm lại mật khẩu";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);
        javaMailAPI.execute();
    }
}