package com.example.practiceandroid.shopping;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practiceandroid.Contact.FeedBack_Comment;
import com.example.practiceandroid.Fragment.CartFragment;
import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.Purchased_Product.classBought_Product;
import com.example.practiceandroid.Purchased_Product.classInfo_Bill;
import com.example.practiceandroid.R;
import com.example.practiceandroid.dbSQLite.dbHelper;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.function.getCurrent_Day_Time;
import com.example.practiceandroid.function.getShowCartFragment;
import com.example.practiceandroid.function.pushNotification;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_shopping extends AppCompatActivity {

    public  static dbHelper dbhelper;
    ImageView btnBack1;
    EditText editTextName;
    EditText editTextPhonenmber;
    EditText editTextAddress;
    Button btnSubmit;

    String name ;
    String phonenumber ;
    String address ;
    String idProduct;
    String categoryProduct;
    String nameProduct;
    String priceProduct;
    String numberProduct;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        btnBack1 = findViewById(R.id.btn_back1);
        editTextName = findViewById(R.id.edittxt_name);
        editTextPhonenmber = findViewById(R.id.edittxt_phonenumber);
        editTextAddress = findViewById(R.id.edittxt_address);
        btnSubmit= findViewById(R.id.btn_submit);
        dbhelper = CartFragment.dbhelper;

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_shopping.this, Manhinh_Home.class));
            }
        });
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //            final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.order);
        btnSubmit.setOnClickListener(v -> {
            add_Feedback_component();
            name = editTextName.getText().toString();
            phonenumber = editTextPhonenmber.getText().toString();
            address = editTextAddress.getText().toString();
            Bundle bundle = getIntent().getExtras();
            String totalPrice = bundle.getString("total_price");
            Cursor cursor = dbhelper.GetData("SELECT * FROM SANPHAM");
            while (cursor.moveToNext())
            {
                idProduct = cursor.getString(1);
                categoryProduct = cursor.getString(2);
                nameProduct = cursor.getString(3);
                priceProduct = cursor.getString(4);
                numberProduct = cursor.getString(5);
                classBought_Product BOUGHT =  new classBought_Product(
                        "IDuser",
                        idProduct,
                        categoryProduct,
                        nameProduct,
                        priceProduct,
                        numberProduct,
                        name,
                        phonenumber,
                        address,
                        getCurrent_Day_Time.get(),
                        false
                );
                classInfo_Bill infoBill = new classInfo_Bill(
                        name,
                        totalPrice,
                        getCurrent_Day_Time.get()
                );
                databaseReference.child("Bought_Product").child(Manhinh_Login.userlogin.getName_user()).child(getCurrent_Day_Time.get().split(" ")[0].replace("/"," ")).child(getCurrent_Day_Time.get().split(" ")[1]).child(nameProduct).setValue(BOUGHT);
                databaseReference.child("Statistic").push().setValue(infoBill);
                dbhelper.QueryData("DELETE FROM SANPHAM WHERE NameProduct = '" + nameProduct + "' ");
            }

            pushNotification.pushnotification("2", activity_shopping.this);
            Intent intent1 = new Intent(activity_shopping.this, Manhinh_Home.class);
            startActivity(intent1);
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

    private void add_Feedback_component() {
        name = editTextName.getText().toString();
        phonenumber = editTextPhonenmber.getText().toString();
        address = editTextAddress.getText().toString();
        Cursor cursor = dbhelper.GetData("SELECT * FROM SANPHAM");
        while (cursor.moveToNext()) {
            idProduct = cursor.getString(1);
            categoryProduct = cursor.getString(2);
            nameProduct = cursor.getString(3);
            priceProduct = cursor.getString(4);
            numberProduct = cursor.getString(5);
            FeedBacks_Products FBOUGHT = new FeedBacks_Products(
                    idProduct,
                    nameProduct,
                    categoryProduct,
                    priceProduct,
                    name
            );

            FIREBASE.MDATA.child("Bought_FeedBacks").child(Manhinh_Login.userlogin.getName_user()).push().setValue(FBOUGHT);
        }
    }
}