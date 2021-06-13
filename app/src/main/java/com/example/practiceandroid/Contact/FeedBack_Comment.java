package com.example.practiceandroid.Contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.practiceandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBack_Comment extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back__comment);

        //Su dung BindView thay cho findViewbyID
        ButterKnife.bind(this);



    }
}