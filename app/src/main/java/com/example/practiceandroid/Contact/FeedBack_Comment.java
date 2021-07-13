package com.example.practiceandroid.Contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
    EditText edtComment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back__comment);
        //Su dung BindView thay cho findViewbyID
        ButterKnife.bind(this);
        SetData();
        class_comment.Star = 5 +".0";
        class_comment.Comment = "";



        ImageFromStorage.setImage(class_comment.productName, 1, ivComment);
        bttSubmit.setOnClickListener(v -> {
            if (!class_comment.Star.equals("0")){
                class_comment.Comment = (edtComment.getText().toString());
                UpploadData();
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

    private void SetData() {
        star_up_1.setOnClickListener(v -> starCount(1));
        star_up_2.setOnClickListener(v -> starCount(2));
        star_up_3.setOnClickListener(v -> starCount(3));
        star_up_4.setOnClickListener(v -> starCount(4));
        star_up_5.setOnClickListener(v -> starCount(5));
    }

    //Uppload data to database
    private void UpploadData() {
        FIREBASE.MDATA.child("Comment")
                .child(class_comment.Categories)
                .child(class_comment.productID)
                .push()
                .setValue(new Class_dataComent(class_comment.Categories,
                                                class_comment.Comment,
                                                class_comment.userName,
                                                class_comment.productID,
                                                class_comment.productName,
                                                class_comment.Star));
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
        class_comment.Star = (position + ".0");
        tvRatingStar.setText(class_comment.Star);
    }

}