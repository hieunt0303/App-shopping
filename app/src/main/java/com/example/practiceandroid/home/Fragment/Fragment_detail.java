package com.example.practiceandroid.home.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practiceandroid.R;

public class Fragment_detail extends Fragment {
    String detail;
    TextView txt_detail;
    public Fragment_detail(String detail) {
        this.detail = detail;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootview= inflater.inflate(R.layout.layout_fragment_detail,container,false);
        txt_detail= mRootview.findViewById(R.id.textView_detail);
        txt_detail.setText(detail);
        return mRootview;
    }
}