package com.example.practiceandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Manhinh_Login extends AppCompatActivity {

    DatabaseUserLogin databaseUserLogin;
    EditText editTextUser;
    EditText editTextPassword;
    TextView dk;
    List<User> DS = new ArrayList<>();
    public static User userlogin;


    //Declaration TextInputLayout

    //Declaration Button
    ImageButton btnLogin;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference("User");

    //Declaration SqliteHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__login);
        btnLogin = (ImageButton) findViewById(R.id.imageButton2);
        editTextUser=(EditText) findViewById(R.id.editTextuser);
        editTextPassword=(EditText) findViewById(R.id.editTextPass);
        dk = (TextView) findViewById(R.id.textView_description);
        setupDatabase();
        Cursor datauser = databaseUserLogin.GetData("Select * from User");
        if(datauser !=null)
        {
            Intent mh = new Intent(Manhinh_Login.this,Manhinh_Home.class);
            startActivity(mh);
        }
       
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPassword.getText() != null && editTextUser.getText() != null) {
                    if (check()) {
                        Intent mh = new Intent(Manhinh_Login.this, Manhinh_Home.class);
                        startActivity(mh);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),  "Tài khoản / mật khẩu không được để trống!",

                            Toast.LENGTH_LONG).show();
                }
            }
        });
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh = new Intent(Manhinh_Login.this,Manhinh_DK.class);
                startActivity(mh);
            }
        });
    }
    public boolean check()
    {

        for(int i=0;i<DS.size();i++)
        {
            if(DS.get(i).name_user.equals(editTextUser.getText().toString())&&
               DS.get(i).password.equals(editTextPassword.getText().toString()))
            {
                databaseUserLogin.QueryData("insert into User values(null, '"
                        + DS.get(i).name_user + "', '"
                        + DS.get(i).email+ "', '"
                        + DS.get(i).password +"')"
                        );
                return  true;
            }
        }
        return false;
    }
    private void setupDatabase() {
        databaseUserLogin = new DatabaseUserLogin(this, "user.sqlite", null, 1);
        databaseUserLogin.QueryData("Create table if not exists User (Id Integer Primary key autoincrement, User nvarchar(20), User nvarchar(50), Password nvarchar(50))");
    }
}