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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Manhinh_Resetpass extends AppCompatActivity {

    EditText pass,repass,codexm;
    ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__resetpass);
        pass = (EditText) findViewById(R.id.editTextnewPass);
        repass = (EditText) findViewById(R.id.editTextretypePass);
        codexm = (EditText) findViewById(R.id.editTextcode);
        btn = (ImageButton) findViewById(R.id.imageButtonReset);
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Manhinh_Mailreset.email;
                if(Integer.parseInt(codexm.getText().toString()) == Manhinh_Mailreset.Code)
                {
                    if(pass.getText().toString().equals(repass.getText().toString()))
                    {
                        mData.child("User").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                if (snapshot.child("email").getValue().toString().equals(email)) {
                                    mData.child("User").child(snapshot.getKey()).child("password").setValue(pass.getText().toString());

                                }

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
                        Intent mh = new Intent(Manhinh_Resetpass.this,Manhinh_Login.class);
                        startActivity(mh);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),  "Mật khẩu mới không trùng nhau!",

                                Toast.LENGTH_LONG).show();
                    }
                }
                 else
                {
                    Toast.makeText(getApplicationContext(),  "Mã xác minh không đúng!",

                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}