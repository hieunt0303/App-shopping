package com.example.practiceandroid.home.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practiceandroid.R;

public class Fragment_description  extends Fragment {
//    private  View mRootview;

    String description;
    TextView txt_description;
    public Fragment_description(String  description) {
        this.description = description;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootview= inflater.inflate(R.layout.layout_fragment_description,container,false);

        txt_description= mRootview.findViewById(R.id.textView_description);
        txt_description.setText(description);
        return mRootview;


    }
}