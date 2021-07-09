package com.example.practiceandroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceandroid.MainActivity;
import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.R;
import com.example.practiceandroid.chat.adapter_chat_recycleview;
import com.example.practiceandroid.chat.class_chat;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.practiceandroid.Fragment.SpeechFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpeechFragment extends Fragment {

    // chat firebase
    DatabaseReference mData;

    ImageButton btn_Send;
    EditText editText_content ;



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
        mData= FirebaseDatabase.getInstance().getReference();
        //mData.child("hieu").setValue("nguyen trung hieu");

        mData.child("Message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.child("id").getValue().toString().equals("false")){
                    listChat.add(new class_chat(snapshot.child("content").getValue().toString(),false));
                    adapterRecyclerviewUser.notifyDataSetChanged();
                }
                else if(snapshot.child("id").getValue().toString().equals("true")){
                    listChat.add(new class_chat(snapshot.child("content").getValue().toString(),true));
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
         btn_Send = (ImageButton) view.findViewById(R.id.button_send_content_chat);
         editText_content = view.findViewById(R.id.editText_content_chat);
         recyclerView = view.findViewById(R.id.Recyclerview_chat);
         adapterRecyclerviewUser= new adapter_chat_recycleview();

        listChat= new ArrayList<>();
        adapterRecyclerviewUser= new adapter_chat_recycleview();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterRecyclerviewUser.setData(listChat);
        recyclerView.setAdapter(adapterRecyclerviewUser);


        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NEU LA ADMIN = TRUE , USER = FALSE
                if(!editText_content.getText().toString().equals(""))
                {
                    class_chat data= new class_chat(editText_content.getText().toString(),false);
                    mData.child("Message").push().setValue(data);
                    adapterRecyclerviewUser.notifyDataSetChanged();
                    recyclerView.scrollToPosition(listChat.size()-1);
                    editText_content.setText("");
                }
            }
        });

        //luôn hiển thị cuối đoạn chat



        return view;
    }
}