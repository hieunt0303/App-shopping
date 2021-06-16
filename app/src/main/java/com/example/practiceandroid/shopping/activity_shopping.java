package com.example.practiceandroid.shopping;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.practiceandroid.Fragment.CartFragment;
import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.R;
import com.example.practiceandroid.home.Detail_Information_Product;
import com.example.practiceandroid.notification.activity_notification;
import com.example.practiceandroid.notification.class_notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class activity_shopping extends AppCompatActivity {

    ImageView btnBack1;
    EditText editTextName;
    EditText editTextPhonenmber;
    EditText editTextAddress;
    Button btnSubmit;

    String id ;
    String name ;
    String phonenumber ;
    String address ;
    Random random;

    String id1;
    String title;
    String information;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shopping);
        btnBack1 = findViewById(R.id.btn_back1);
        editTextName = findViewById(R.id.edittxt_name);
        editTextPhonenmber = findViewById(R.id.edittxt_phonenumber);
        editTextAddress = findViewById(R.id.edittxt_address);
        btnSubmit= findViewById(R.id.btn_submit);
        random = new Random(1000);

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_shopping.this, Manhinh_Home.class));
            }
        });
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.order);
                    @Override
                    public void onClick(View v) {
                        id = String.valueOf(random) ;
                        name = editTextName.getText().toString();
                        phonenumber = editTextPhonenmber.getText().toString();
                        address = editTextAddress.getText().toString();
                        class_shopping shopping = new class_shopping(id, name, phonenumber, address);
                        databaseReference.child("Shopping").push().setValue(shopping);
                        id1 = "2";
                        title = "Oder";
                        information = "Your order is success";
                        class_notification order = new class_notification(id1, title, information);
                        databaseReference.child("Notification").push().setValue(order);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity_shopping.this, "My Notification");
                        builder.setContentTitle(title);
                        builder.setContentText(information);
                        builder.setLargeIcon(bitmap);
                        builder.setSmallIcon(R.drawable.ic_launcher_background);
                        builder.setAutoCancel(true);

                        Intent intent = new Intent(activity_shopping.this, activity_notification.class);
//                            intent.putExtra("number", number +1);
                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(activity_shopping.this);
                        stackBuilder.addParentStack(activity_notification.class);
                        stackBuilder.addNextIntent(intent);
                        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pendingIntent);

                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(activity_shopping.this);
                        managerCompat.notify(1, builder.build());

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if(manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

    }
}
