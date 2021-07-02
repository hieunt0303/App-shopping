package com.example.practiceandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practiceandroid.reset_passwork.JavaMailAPI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Manhinh_Mailreset extends AppCompatActivity {

    public EditText Email;
    public ImageButton btn;
    public static String email;
    public static int Code;
    List<User> DS = new ArrayList<>();
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__mailreset);
        Email = (EditText) findViewById(R.id.editTextEmail);
        btn = (ImageButton) findViewById(R.id.imageButtonReset);
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                DS.add(user);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email.getText().toString() !=null) {
                    if (check()) {
                        sendMail();
                        Intent mh = new Intent(Manhinh_Mailreset.this, Manhinh_Resetpass.class);
                        startActivity(mh);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email chưa được liên kết với tài khoản nào!",

                                Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Email không được để trống!",

                            Toast.LENGTH_LONG).show();
                }
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
    private boolean check()
    {
        for(int i=0;i<DS.size();i++)
        {
            if(DS.get(i).name_user.equals(Email.getText().toString()))
            {
                return  true;
            }
        }
        return false;
    }
}