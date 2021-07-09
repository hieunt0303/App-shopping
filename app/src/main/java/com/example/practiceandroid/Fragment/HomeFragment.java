package com.example.practiceandroid.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.admin_Home;
import com.example.practiceandroid.data_app.class_user;
import com.example.practiceandroid.home.adapter_Information_product;
import com.example.practiceandroid.home.adapter_slide_header_home;
import com.example.practiceandroid.home.class_Information_Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.practiceandroid.Fragment.HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

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
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static com.example.practiceandroid.Fragment.HomeFragment newInstance(String param1, String param2) {
        com.example.practiceandroid.Fragment.HomeFragment fragment = new com.example.practiceandroid.Fragment.HomeFragment();
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

        // Hiển thị sản phẩm với firebase
        mData= FirebaseDatabase.getInstance().getReference();

        //mData.child("Products").child("Electronics").push().setValue(listProduct);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // set adapter view pager
        // set adapter cho slide 3 cái ở đầu
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        adapter_slide_header_home= new adapter_slide_header_home(container.getContext());
        viewPager_slide_header= view.findViewById(R.id.viewpager_slideheader);
        viewPager_slide_header.setAdapter(adapter_slide_header_home);
        // Inflate the layout for this fragment


        // set adapter cho các sản phẩm
        gridView = view.findViewById(R.id.Gridview_product);
        nestedScrollView= view.findViewById(R.id.scrollviewProduct);
        img= view.findViewById(R.id.imageView11);
        productArrayList= new ArrayList<>();

        //Sắp xếp tăng dần
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(productArrayList, new Comparator<class_Information_Product>() {
                    @Override
                    public int compare(class_Information_Product o1, class_Information_Product o2) {
                        editText_search.setText(String.valueOf(productArrayList.size()));
                        return o1.getPrice_product_real().compareTo(o2.getPrice_product_real());
                    }
                });
                adapter_information_product.notifyDataSetChanged();
            }
        });
        adapter_information_product= new adapter_Information_product(container.getContext(),R.layout.layout_sanpham,productArrayList);
        gridView.setAdapter(adapter_information_product);

        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                nestedScrollView.requestDisallowInterceptTouchEvent(true);
                int action= event.getActionMasked();
                switch (action){
                    case MotionEvent.ACTION_UP:
                        nestedScrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });

        String[] arr_categories ={"Woman","Man","Shoes","Toys","Electronics","Furniture","Phones","Laptop"};
        int i=0;
        for(i=0;i< arr_categories.length;i++){
            // lấy sãn phẩm theo mỗi loại từ data xuống và cho hiển thị
            mData.child("Products").child(arr_categories[i]).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    productArrayList.add(new class_Information_Product(
                            snapshot.child("id_product").getValue().toString(),
                            snapshot.child("categories").getValue().toString(),
                            snapshot.child("name_product").getValue().toString(),
                            snapshot.child("price_product_real").getValue().toString(),
                            snapshot.child("discount").getValue().toString(),
                            snapshot.child("price_product_fake").getValue().toString(),
                            snapshot.child("Sum_Ratingbar").getValue().toString(),
                            Integer.valueOf(snapshot.child("in_stock").getValue().toString()),
                            Integer.valueOf(snapshot.child("Sum_Bought").getValue().toString()),
                            Boolean.valueOf(snapshot.child("favourite").getValue().toString()),
                            Boolean.valueOf(snapshot.child("add_to_cart").getValue().toString()),
                            snapshot.child("description").getValue().toString(),
                            snapshot.child("detail").getValue().toString(),
                            Integer.valueOf(snapshot.child("color1").getValue().toString()),
                            Integer.valueOf(snapshot.child("color2").getValue().toString()),
                            R.drawable.border_product
                    ));
                    adapter_information_product.notifyDataSetChanged();
                    //Toast.makeText(container.getContext(),"hieu",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        editText_search= view.findViewById(R.id.editTextTextPersonName);
        Toast.makeText(getActivity(),  Manhinh_Login.userlogin.name_user,

                Toast.LENGTH_LONG).show();


        return view ;
    }
}