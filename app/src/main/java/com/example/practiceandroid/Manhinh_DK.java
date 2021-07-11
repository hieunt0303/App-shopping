package com.example.practiceandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
import java.util.ArrayList;

public class Manhinh_DK extends AppCompatActivity {

    EditText name,email,pass;
    Button buttondk;
    List<User> DS = new ArrayList<>();
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh_d_k);
        name = (EditText) findViewById(R.id.editTextName);
        email = (EditText) findViewById(R.id.editTextuser1);
        pass = (EditText) findViewById(R.id.editTextPass1);
        buttondk = (Button) findViewById(R.id.button9);
        mData.child("User").addChildEventListener(new ChildEventListener() {
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
        buttondk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    if (check()) {
                        int id = (int) Math.floor(((Math.random() * 899999) + 100000));
                        User user = new User("0", "0", email.getText().toString(), Integer.toString(id), name.getText().toString(), pass.getText().toString(),"0");
                        mData.child("User").child(name.getText().toString()).setValue(user);
                        mData.child("text_Notification").child(name.getText().toString()).child("number_Notification").setValue("0");
                        Intent mh = new Intent(Manhinh_DK.this, Manhinh_Login.class);
                        startActivity(mh);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),  "Email hoặc tên đăng nhập đã tồn tại",

                                Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),  "Email bạn nhập không đúng",

                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean check()
    {
        for(int i=0;i<DS.size();i++)
        {
            if(DS.get(i).name_user.equals(name.getText().toString())||DS.get(i).email.equals(email.getText().toString()))
            {
                return  false;
            }
        }
        return true;
    }
}