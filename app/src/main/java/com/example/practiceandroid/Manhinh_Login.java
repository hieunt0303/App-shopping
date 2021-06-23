package com.example.practiceandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ArrayList;

public class Manhinh_Login extends AppCompatActivity {

    DatabaseUserLogin databaseUserLogin;
    EditText editTextEmail;
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
        editTextEmail=(EditText) findViewById(R.id.editTextuser);
        editTextPassword=(EditText) findViewById(R.id.editTextPass);
        dk = (TextView) findViewById(R.id.textView_description);
        setupDatabase();
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
                if(check())
                {
                    Intent mh = new Intent(Manhinh_Login.this,Manhinh_Home.class);
                    startActivity(mh);
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
            if(DS.get(i).name_user.equals(editTextEmail.getText().toString())&&
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
        databaseUserLogin.QueryData("Create table if not exists User (Id Integer Primary key autoincrement, User nvarchar(20), Email nvarchar(50), Password nvarchar(50))");
    }
}