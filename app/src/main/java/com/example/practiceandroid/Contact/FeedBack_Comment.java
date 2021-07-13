package com.example.practiceandroid.Contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practiceandroid.R;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.function.ImageFromStorage;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBack_Comment extends AppCompatActivity {

    @BindView(R.id.button_Submit_cmt)
    Button bttSubmit;
    @BindView(R.id.editText_comment)
    Button bttComment;
    @BindView(R.id.imageview_Back)
    ImageView imBack;


    @BindView(R.id.ivbutton_star1)
    ImageView star_up_1;
    @BindView(R.id.ivbutton_star2)
    ImageView star_up_2;
    @BindView(R.id.ivbutton_star3)
    ImageView star_up_3;
    @BindView(R.id.ivbutton_star4)
    ImageView star_up_4;
    @BindView(R.id.ivbutton_star5)
    ImageView star_up_5;

    @BindView(R.id.imageview_Comment)
    ImageView ivComment;

    @BindView(R.id.textView_rating_comment)
    TextView tvRatingStar;

    class_comment newComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back__comment);
        //Su dung BindView thay cho findViewbyID
        ButterKnife.bind(this);
        GetData();
        SetData();

        bttSubmit.setOnClickListener(v -> {
            if (!newComment.getStar().equals("0")){
                newComment.setComment(bttComment.getText().toString());
                UpploadData(newComment);
                Toast.makeText(FeedBack_Comment.this, "Thanks for purchase us", Toast.LENGTH_SHORT).show();
                FeedBack_Comment.this.finish();
            }
            else
            {
                Toast.makeText(FeedBack_Comment.this, "Please rates our product", Toast.LENGTH_SHORT).show();
            }

        });
        imBack.setOnClickListener(v -> FeedBack_Comment.this.finish());


    }
// Get data from firebase
    private void GetData() {
        newComment.setProductName(getIntent().getStringExtra("prName"));
        newComment.setCategories(getIntent().getStringExtra("prCategories"));
        newComment.setProductID(getIntent().getStringExtra("prID"));
        newComment.setUserName(getIntent().getStringExtra("usID"));
        ImageFromStorage.setImage(newComment.getProductName(), 1, ivComment);
    }

    private void SetData() {
        star_up_1.setOnClickListener(v -> starCount(1));
        star_up_2.setOnClickListener(v -> starCount(2));
        star_up_3.setOnClickListener(v -> starCount(3));
        star_up_4.setOnClickListener(v -> starCount(4));
        star_up_5.setOnClickListener(v -> starCount(5));
    }

    //Uppload data to database
    private void UpploadData(class_comment comment) {
        FIREBASE.MDATA.child("Comment")
                .child(newComment.Categories)
                .child(newComment.productID)
                .push()
                .setValue(newComment);
    }


    public void starCount(int position){
        switch (position){
            case 0:
                star_up_1.setImageResource(R.drawable.icon_star_none);
                star_up_2.setImageResource(R.drawable.icon_star_none);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);
                break;

            case 1:
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star_none);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);
                break;
            case 2:
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                break;
            case 3:
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);
                break;
            case 4:
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star);
                star_up_5.setImageResource(R.drawable.icon_star_none);
                break;
            case 5:
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star);
                star_up_5.setImageResource(R.drawable.icon_star);


                break;

        }
        tvRatingStar.setText(position);
        newComment.setStar(String.valueOf(position));
    }

}