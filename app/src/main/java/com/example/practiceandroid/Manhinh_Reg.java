package com.example.practiceandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
import java.util.ArrayList;
public class Manhinh_Reg extends AppCompatActivity {

    EditText edittextname = findViewById(R.id.editTextname);
    EditText edittextemail = findViewById(R.id.editTextuser);
    EditText edittextpass = findViewById(R.id.editTextPass);
    List<User> DS = new ArrayList<>();


    //Declaration TextInputLayout

    //Declaration Button
    ImageButton btnReg;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btnReg= (ImageButton) findViewById(R.id.imageButton2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__reg);
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
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check())
                {
                    User user = new User("0","0",edittextemail.getText().toString(),"0",edittextname.getText().toString(),edittextpass.getText().toString());
                    mData.push().setValue(user);
                }
            }
        });
    }
    public boolean check()
    {
        for(int i=0;i<DS.size();i++)
        {
            if(DS.get(i).name_user.equals(edittextemail.getText().toString()))
            {
                return  false;
            }
        }
        return true;
    }
}