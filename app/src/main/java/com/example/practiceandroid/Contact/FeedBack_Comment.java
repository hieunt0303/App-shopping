package com.example.practiceandroid.Contact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.practiceandroid.R;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBack_Comment extends AppCompatActivity {

    @BindView(R.id.button_Submit_cmt)
    Button bttSubmit;
    @BindView(R.id.editText_comment)
    Button bttComment;

    @BindView(R.id.ivbutton_star1)
    Button ivStar1;
    @BindView(R.id.ivbutton_star2)
    Button ivStar2;
    @BindView(R.id.ivbutton_star3)
    Button ivStar3;
    @BindView(R.id.ivbutton_star4)
    Button ivStar4;
    @BindView(R.id.ivbutton_star5)
    Button ivStar5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back__comment);

        //Su dung BindView thay cho findViewbyID
        ButterKnife.bind(this);

        setPage();

        bttSubmit.setOnClickListener(v -> {
            String comment = bttComment.getText().toString();
            int countStar = starCount();

            //Do something
        });


    }

    private int starCount() {
        AtomicInteger count = new AtomicInteger();
        ivStar1.setOnClickListener(v -> count.set(1));
        ivStar1.setOnClickListener(v -> count.set(2));
        ivStar1.setOnClickListener(v -> count.set(3));
        ivStar1.setOnClickListener(v -> count.set(4));
        ivStar1.setOnClickListener(v -> count.set(5));
        return count.get();
    }

    private void setPage() {
        //Do something
    }
}