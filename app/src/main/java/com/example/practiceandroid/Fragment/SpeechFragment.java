package com.example.practiceandroid.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceandroid.MainActivity;
import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.chat.adapter_chat_recycleview;
import com.example.practiceandroid.chat.class_chat;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.function.getCurrent_Day_Time;
import com.example.practiceandroid.home.Category.CategoryProduct;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.practiceandroid.Fragment.SpeechFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpeechFragment extends Fragment {

    // CÁC THÀNH PHẦN TRONG LAYOUT
    ImageButton btn_Send;
    EditText editText_content ;
    ImageView imgview_micro;
    ImageView imgview_hintchat;

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


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SpeechFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static com.example.practiceandroid.Fragment.SpeechFragment newInstance(String param1, String param2) {
        com.example.practiceandroid.Fragment.SpeechFragment fragment = new com.example.practiceandroid.Fragment.SpeechFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //Anh xa view

        // Chat FireBase

        //mData.child("hieu").setValue("nguyen trung hieu");

        FIREBASE.MDATA.child("Message").child(Manhinh_Login.userlogin.getName_user()).addChildEventListener(new ChildEventListener() {
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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_speech, container, false);

        // Anh xa
         btn_Send = (ImageButton) view.findViewById(R.id.button_send_content_chat);
         editText_content = view.findViewById(R.id.editText_content_chat);
         recyclerView = view.findViewById(R.id.Recyclerview_chat);
         adapterRecyclerviewUser= new adapter_chat_recycleview();
         imgview_hintchat= view.findViewById(R.id.imageview_hintchat);
         imgview_micro= view.findViewById(R.id.imageview_microchat);

        listChat= new ArrayList<>();
        adapterRecyclerviewUser= new adapter_chat_recycleview();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterRecyclerviewUser.setData(listChat);
        recyclerView.setAdapter(adapterRecyclerviewUser);

        // Khởi tạo dialog nhắc chữ trong button hintchat
        builder= new AlertDialog.Builder(getContext());
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
                if(Manhinh_Login.userlogin.getName_user().equals("admin") ){
                    if (!editText_content.getText().toString().equals("")) {
                        class_chat data = new class_chat(editText_content.getText().toString(),
                                true,
                                getCurrent_Day_Time.getCurrentTime(),
                                false,
                                "none");

                        FIREBASE.MDATA.child("Message").child(Manhinh_Login.userlogin.getName_user()).push().setValue(data);
                        adapterRecyclerviewUser.notifyDataSetChanged();
                        recyclerView.scrollToPosition(listChat.size() - 1);
                        editText_content.setText("");
                    }
                }
                else {
                    if (!editText_content.getText().toString().equals("")) {
                        class_chat data = new class_chat(editText_content.getText().toString(),
                                false,
                                getCurrent_Day_Time.getCurrentTime(),
                                false,
                                "none");

                        FIREBASE.MDATA.child("Message").child(Manhinh_Login.userlogin.getName_user()).push().setValue(data);
                        adapterRecyclerviewUser.notifyDataSetChanged();
                        recyclerView.scrollToPosition(listChat.size() - 1);
                        editText_content.setText("");
                    }
                }
            }
        });

        //luôn hiển thị cuối đoạn chat



        return view;
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