package com.example.practiceandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.home.Comment.classComment;
import com.example.practiceandroid.home.class_Information_Product;
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
    TextView forgotpass;

    ImageView imgview_eye;
    List<User> DS = new ArrayList<>();
    public static User userlogin;


    //Declaration TextInputLayout

    //Declaration Button
    Button btnLogin;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference("User");

    //Declaration SqliteHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__login);
        btnLogin = (Button) findViewById(R.id.imageButton2);
        editTextUser=(EditText) findViewById(R.id.editTextuser);
        editTextPassword=(EditText) findViewById(R.id.editTextPass);

        imgview_eye= findViewById(R.id.ImageView_eye);
        dk = (TextView) findViewById(R.id.textView_description);
        forgotpass = (TextView) findViewById(R.id.textViewforgotpass);

        // add
        databaseUserLogin = new DatabaseUserLogin(this, "user.sqlite", null, 1);
        //databaseUserLogin.QueryData("Drop table User");
        databaseUserLogin.QueryData("Create table if not exists User (Id Integer Primary key autoincrement, User nvarchar(20), Email nvarchar(50), Password nvarchar(50), Address nvarchar(100),Phone nvarchar(50), Accountid nvarchar(100) )");
        Cursor datauser = databaseUserLogin.GetData("Select * from User ");
        int count = 0;
        while (datauser.moveToNext()) {
            count ++;
            userlogin = new User(datauser.getString(4),"0",datauser.getString(2),datauser.getString(6),datauser.getString(1),datauser.getString(3),datauser.getString(5));
        }
        if(count != 0 )
        {
            if(userlogin.name_user.equals("admin"))
            {
                Intent mh = new Intent(Manhinh_Login.this, admin_Home.class);
                startActivity(mh);
            }
            else
            {
                Intent mh = new Intent(Manhinh_Login.this, Manhinh_Home.class);
                startActivity(mh);
            }

        }

        //Cursor datauser = databaseUserLogin.GetData("Select * from User");
        //if(datauser !=null)
        //{
        //Intent mh = new Intent(Manhinh_Login.this,Manhinh_Home.class);
        //startActivity(mh);
        //}
        imgview_eye.setImageResource(R.drawable.icon_eye_close);
        imgview_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                classComment a1= new classComment(
//                        "SP0003",
//                        "Electronics",
//                        "Airpods Pro",
//                        "long",
//                        "4",
//                        "rất đáng tiền"
//                );
//
//                FIREBASE.MDATA.child("Comment").child("Electronics").child("SP0003").push().setValue(a1);
                if(editTextPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    editTextPassword.setInputType( InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgview_eye.setImageResource(R.drawable.icon_eye_close);
                }else {
                    editTextPassword.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                    imgview_eye.setImageResource(R.drawable.icon_eye_open);
                }
            }
        });

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
                        if(editTextUser.getText().toString().equals("admin"))
                        {

                            Cursor datauser = databaseUserLogin.GetData("Select * from User");
                            while (datauser.moveToNext()) {
                                userlogin = new User(datauser.getString(4),"0",datauser.getString(2),datauser.getString(6),datauser.getString(1),datauser.getString(3),datauser.getString(5));
                            }
                            Intent mh = new Intent(Manhinh_Login.this, admin_Home.class);
                            startActivity(mh);
                        }
                        else {

                            Cursor datauser = databaseUserLogin.GetData("Select * from User");
                            while (datauser.moveToNext()) {
                                userlogin = new User(datauser.getString(4),"0",datauser.getString(2),datauser.getString(6),datauser.getString(1),datauser.getString(3),datauser.getString(5));
                            }
                            Intent mh = new Intent(Manhinh_Login.this, Manhinh_Home.class);
                            startActivity(mh);
                        }
                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(),  "Tài khoản / mật khẩu không đúng!",

                                Toast.LENGTH_LONG).show();
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
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh = new Intent(Manhinh_Login.this,Manhinh_Mailreset.class);
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
                databaseUserLogin.QueryData("insert into User values(0, '"
                        + DS.get(i).name_user + "', '"
                        + DS.get(i).email+ "', '"
                        + DS.get(i).password+ "', '"
                        + DS.get(i).address+ "', '"
                        + DS.get(i).phone+ "', '"
                        + DS.get(i).id+"')"
                );
                return  true;
            }
        }
        return false;
    }

}