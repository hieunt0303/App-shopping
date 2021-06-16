package com.example.practiceandroid.notification;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practiceandroid.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.practiceandroid.Fragment.HomeFragment;

import java.util.ArrayList;

public class activity_notification extends AppCompatActivity {
    ListView listView;
    adapter_notification adapter;
    ArrayList<class_notification> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);

        ImageView btnBack = findViewById(R.id.btn_back);

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        adapter = new adapter_notification(this, R.layout.layout_notification, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_notification.this, HomeFragment.class);
                Bundle bundle = new Bundle();
                bundle.putInt("number", HomeFragment.number +1);
                startActivity(intent);
            }
        });
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notification");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                String id = snapshot.child("Id").getValue().toString();
                String  title =  snapshot.child("text_Notification").getValue().toString();
                String information = snapshot.child("text_Information").getValue().toString();
                class_notification newNotification = new class_notification(id, title, information);
                arrayList.add(newNotification);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
