package com.example.practiceandroid.AdminContent.AdminChat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.chat.adapter_chat_recycleview;
import com.example.practiceandroid.chat.class_chat;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.function.getCurrent_Day_Time;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Activity_AdminChat extends AppCompatActivity {
    ImageButton btn_Send;
    EditText editText_content ;
    ImageView imgview_micro;
    ImageView imgview_hintchat;

    // HEADER
    ImageView imgview_back;
    ImageView imgview_Avatar;
    TextView txt_nameUser;

    // Các item trong nhắc trước chữ trong chat
    AlertDialog dialog;
    AlertDialog.Builder builder;
    String[] items={"I have a question for you",
            "I want to buy ..",
            "Any discounts coming soon?",
            "I want to know when my order arrives, what to do?",
            "can i exchange the item"};


    String message="";
    public  static String id_user="";


    RecyclerView recyclerView;
    adapter_chat_recycleview adapterRecyclerviewUser;
    List<class_chat> listChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__admin_chat);

        // Anh xa Header
        imgview_back = findViewById(R.id.imageview_back_chatAdmin);
        imgview_Avatar= findViewById(R.id.imageView_avatar_chatAdmin);
        txt_nameUser= findViewById(R.id.textview_nameUserChatAdmin);

        // Gan gia tri header

        // SAU NÀY ĐỎI LẠI ĐƯỜNG DẪN AVÂTRR THEO TÊN USER
        imgview_Avatar.setBackground(ContextCompat.getDrawable(this, R.drawable.avatar));

        // LẤY TÊN USER ĐỂ HIỂN THỊ
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("nameUser");
        txt_nameUser.setText(nameUser);

        imgview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FIREBASE.MDATA.child("Message").child(nameUser).addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.child("id").getValue().toString().equals("false")){
                    listChat.add(new class_chat(snapshot.child("content").getValue().toString(),
                            false,
                            snapshot.child("time").getValue().toString(),
                            false,
                            snapshot.child("code").getValue().toString()));
                    adapterRecyclerviewUser.notifyDataSetChanged();
                }
                else if(snapshot.child("id").getValue().toString().equals("true")){
                    listChat.add(new class_chat(snapshot.child("content").getValue().toString(),
                            true,
                            snapshot.child("time").getValue().toString(),
                            false,
                            snapshot.child("code").getValue().toString()));
                    adapterRecyclerviewUser.notifyDataSetChanged();
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

        // Gui tin nhan
        btn_Send = (ImageButton) findViewById(R.id.button_send_content_chatAdmin);
        editText_content = findViewById(R.id.editText_content_chatAdmin);
        recyclerView = findViewById(R.id.Recyclerview_chatAdmin);
        adapterRecyclerviewUser= new adapter_chat_recycleview();
        imgview_hintchat= findViewById(R.id.imageview_hintchatAdmin);
        imgview_micro= findViewById(R.id.imageview_microchatAdmin);

        listChat= new ArrayList<>();
        adapterRecyclerviewUser= new adapter_chat_recycleview();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterRecyclerviewUser.setData(listChat);
        recyclerView.setAdapter(adapterRecyclerviewUser);

        // Khởi tạo dialog nhắc chữ trong button hintchat
        builder= new AlertDialog.Builder(this);
        builder.setTitle("select choose");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editText_content.setText(items[which]);
                dialog.cancel();
            }
        });
        dialog= builder.create();

        imgview_micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getByMicro();
            }
        });
        imgview_hintchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        btn_Send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //NEU LA ADMIN = TRUE , USER = FALSE
                // PHẢI CÓ MÃ CODE ĐỂ SAU NÀY SỬA CHO DỄ NHƯNG LÚC NÀY MỚI PUSH LÊN NÊN CHƯA CÓ MÃ CODE, NÊN MÃ CODE SẼ ĐƯỢC SET LẠI TRONG
                // ADMIN CHAT, TẠM THỜI ĐỂ NONE
                if (!editText_content.getText().toString().equals("")) {
                    class_chat data = new class_chat(editText_content.getText().toString(),
                            true,
                            getCurrent_Day_Time.getCurrentTime(),
                            true,
                            "none");

                    FIREBASE.MDATA.child("Message").child(nameUser).push().setValue(data);
                    adapterRecyclerviewUser.notifyDataSetChanged();
                    recyclerView.scrollToPosition(listChat.size() - 1);
                    editText_content.setText("");
                }
            }
        });

    }
    public void getByMicro(){
        Intent voice= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        startActivityForResult(voice,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1  && data!=null){
            ArrayList<String> arrayList= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            editText_content.setText(arrayList.get(0).toString());
        }
    }
}
