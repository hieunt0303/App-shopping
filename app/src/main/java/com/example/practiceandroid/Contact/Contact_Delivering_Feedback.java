package com.example.practiceandroid.Contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.practiceandroid.Contact.Tablayout_Fragment.adapter.adapter_tablayout_contact;
import com.example.practiceandroid.Fragment.ContactFragment;
import com.example.practiceandroid.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Contact_Delivering_Feedback extends AppCompatActivity {

    @BindView(R.id.viewpapger_tablayout_contact)
    ViewPager vpContact;
    @BindView(R.id.tabLayout_contact)
    TabLayout tlContact;

    @BindView(R.id.textview_Title)
    TextView tvTitle;

    @BindView(R.id.imageview_Back)
    ImageButton ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__shipping__dellivering);
        //Su dung BindView thay cho findViewbyID
        ButterKnife.bind(this);
        //Set adapter, initial view
        initView();

        ivBack.setOnClickListener(v -> finish());


    }


    private void initView(){
        adapter_tablayout_contact adapter_tablelayout_contact = new adapter_tablayout_contact(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpContact.setAdapter(adapter_tablelayout_contact);
        tlContact.setupWithViewPager(vpContact);

        //move to specific page
        vpContact.setCurrentItem(ContactFragment.Postion);
        onPageSetText(ContactFragment.Postion);


        vpContact.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onPageSetText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void onPageSetText(int position){
        switch (position){
            default:
                tvTitle.setText("Delivered");
                break;
        }
    }

}