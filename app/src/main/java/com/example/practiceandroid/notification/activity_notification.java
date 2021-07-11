package com.example.practiceandroid.notification;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.Manhinh_Login;
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
        setContentView(R.layout.activity_notification);

        ImageView btnBack = findViewById(R.id.btn_back);
        ImageButton btn_promos = findViewById(R.id.btn_promos);
        ImageButton btn_order = findViewById(R.id.btn_order);
        ImageButton btn_delivery = findViewById(R.id.btn_delivery);
        ImageButton btn_account = findViewById(R.id.btn_account);
        TextView txt_promos = findViewById(R.id.txt_promos);
        TextView txt_order = findViewById(R.id.txt_order);
        TextView txt_delivery = findViewById(R.id.txt_delivery);
        TextView txt_account = findViewById(R.id.txt_account);
        TextView txt_all = findViewById(R.id.txt_allnotification);

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        adapter = new adapter_notification(activity_notification.this, R.layout.layout_notification, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_notification.this, Manhinh_Home.class);
                HomeFragment.number = 0;
                startActivity(intent);
            }
        });
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Notification").child(Manhinh_Login.userlogin.getName_user()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                String id = snapshot.child("id").getValue().toString();
                String  title =  snapshot.child("text_Notification").getValue().toString();
                String information = snapshot.child("text_Information").getValue().toString();
                String currenttime = snapshot.child("text_Time").getValue().toString();
                class_notification newNotification = new class_notification(id, title, information, currenttime);
                arrayList.add(0,newNotification);
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
        btn_promos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<class_notification> arrayListPromos;
                adapter_notification adapterPromos;
                arrayListPromos = new ArrayList<>();
                adapterPromos = new adapter_notification(activity_notification.this, R.layout.layout_notification, arrayListPromos);
                listView.setAdapter(adapterPromos);
                adapterPromos.notifyDataSetChanged();
                for(int i=0; i < arrayList.size(); i++)
                {
                    if(arrayList.get(i).getId().equals("1"))
                    {
                        arrayListPromos.add(arrayList.get(i));
                        adapterPromos.notifyDataSetChanged();
                    }
                }
            }
        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<class_notification> arrayListOrder;
                adapter_notification adapterOrder;
                arrayListOrder = new ArrayList<>();
                adapterOrder = new adapter_notification(activity_notification.this, R.layout.layout_notification, arrayListOrder);
                listView.setAdapter(adapterOrder);
                adapterOrder.notifyDataSetChanged();
                for(int i=0; i < arrayList.size(); i++)
                {
                    if(arrayList.get(i).getId().equals("2"))
                    {
                        arrayListOrder.add(arrayList.get(i));
                        adapterOrder.notifyDataSetChanged();
                    }
                }
            }
        });
        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<class_notification> arrayListDelivery;
                adapter_notification adapterDelivery;
                arrayListDelivery = new ArrayList<>();
                adapterDelivery = new adapter_notification(activity_notification.this, R.layout.layout_notification, arrayListDelivery);
                listView.setAdapter(adapterDelivery);
                adapterDelivery.notifyDataSetChanged();
                for(int i=0; i < arrayList.size(); i++)
                {
                    if(arrayList.get(i).getId().equals("3"))
                    {
                        arrayListDelivery.add(arrayList.get(i));
                        adapterDelivery.notifyDataSetChanged();
                    }
                }
            }
        });
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<class_notification> arrayListAccount;
                adapter_notification adapterAccount;
                arrayListAccount = new ArrayList<>();
                adapterAccount = new adapter_notification(activity_notification.this, R.layout.layout_notification, arrayListAccount);
                listView.setAdapter(adapterAccount);
                adapterAccount.notifyDataSetChanged();
                for(int i=0; i < arrayList.size(); i++)
                {
                    if(arrayList.get(i).getId().equals("4"))
                    {
                        arrayListAccount.add(arrayList.get(i));
                        adapterAccount.notifyDataSetChanged();
                    }
                }
            }
        });
        txt_promos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<class_notification> arrayListPromos;
                adapter_notification adapterPromos;
                arrayListPromos = new ArrayList<>();
                adapterPromos = new adapter_notification(activity_notification.this, R.layout.layout_notification, arrayListPromos);
                listView.setAdapter(adapterPromos);
                adapterPromos.notifyDataSetChanged();
                for(int i=0; i < arrayList.size(); i++)
                {
                    if(arrayList.get(i).getId().equals("1"))
                    {
                        arrayListPromos.add(arrayList.get(i));
                        adapterPromos.notifyDataSetChanged();
                    }
                }
            }
        });
        txt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<class_notification> arrayListOrder;
                adapter_notification adapterOrder;
                arrayListOrder = new ArrayList<>();
                adapterOrder = new adapter_notification(activity_notification.this, R.layout.layout_notification, arrayListOrder);
                listView.setAdapter(adapterOrder);
                adapterOrder.notifyDataSetChanged();
                for(int i=0; i < arrayList.size(); i++)
                {
                    if(arrayList.get(i).getId().equals("2"))
                    {
                        arrayListOrder.add(arrayList.get(i));
                        adapterOrder.notifyDataSetChanged();
                    }
                }
            }
        });
        txt_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<class_notification> arrayListDelivery;
                adapter_notification adapterDelivery;
                arrayListDelivery = new ArrayList<>();
                adapterDelivery = new adapter_notification(activity_notification.this, R.layout.layout_notification, arrayListDelivery);
                listView.setAdapter(adapterDelivery);
                adapterDelivery.notifyDataSetChanged();
                for(int i=0; i < arrayList.size(); i++)
                {
                    if(arrayList.get(i).getId().equals("3"))
                    {
                        arrayListDelivery.add(arrayList.get(i));
                        adapterDelivery.notifyDataSetChanged();
                    }
                }
            }
        });
        txt_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<class_notification> arrayListAccount;
                adapter_notification adapterAccount;
                arrayListAccount = new ArrayList<>();
                adapterAccount = new adapter_notification(activity_notification.this, R.layout.layout_notification, arrayListAccount);
                listView.setAdapter(adapterAccount);
                adapterAccount.notifyDataSetChanged();
                for(int i=0; i < arrayList.size(); i++)
                {
                    if(arrayList.get(i).getId().equals("4"))
                    {
                        arrayListAccount.add(arrayList.get(i));
                        adapterAccount.notifyDataSetChanged();
                    }
                }
            }
        });
        txt_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
