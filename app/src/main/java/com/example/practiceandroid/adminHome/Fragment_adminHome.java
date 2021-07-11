package com.example.practiceandroid.adminHome;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.practiceandroid.R;
import com.example.practiceandroid.home.adapter_Information_product;
import com.example.practiceandroid.home.class_Information_Product;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_adminHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_adminHome extends Fragment {
    // slide hiển thị 3 cái bảng thông báo chương trình sắp tới (black friday,...)
    ViewPager viewPager_slide_header ;
    com.example.practiceandroid.home.adapter_slide_header_home adapter_slide_header_home;

    // hiển thị sản phẩm
    GridView gridView;
    List<class_Information_Product> productArrayList;
    adapter_Information_product adapter_information_product;
    NestedScrollView nestedScrollView;

    //Fire base hiển thị sản phẩm
    DatabaseReference mData;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText editText_search;
    ImageView img;
//    Button txtNumberNotification;
//    public static  int number = 0;
    public Fragment_adminHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_adminHome.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_adminHome newInstance(String param1, String param2) {
        Fragment_adminHome fragment = new Fragment_adminHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_home, container, false);
    }
}