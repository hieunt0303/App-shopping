package com.example.practiceandroid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.example.practiceandroid.Contact.Contact_Profile;
import com.example.practiceandroid.Contact.Contact_Waiting_Shipping_Dellivering;
import com.example.practiceandroid.R;
import com.example.practiceandroid.home.adapter_Information_product;
import com.example.practiceandroid.home.class_Information_Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.practiceandroid.Fragment.ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.edit_profile)
    Button bttEditProfile;

    @BindView(R.id.waiting)
    ImageView ivWaiting;
    @BindView(R.id.delivering)
    ImageView ivDelivering;
    @BindView(R.id.feedback)
    ImageView ivFeedBack;

    // hiển thị sản phẩm
    GridView gridView;
    List<class_Information_Product> productArrayList;
    adapter_Information_product adapter_information_product;
    NestedScrollView nestedScrollView;
    ImageView img;
    EditText editText_search;


    //Fire base hiển thị sản phẩm
    DatabaseReference mData;

    public static int Postion = 0;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public ContactFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static com.example.practiceandroid.Fragment.ContactFragment newInstance(String param1, String param2) {
        com.example.practiceandroid.Fragment.ContactFragment fragment = new com.example.practiceandroid.Fragment.ContactFragment();
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
        mData= FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        //Use ButterKnife instead of findViewByID
        unbinder = ButterKnife.bind(ContactFragment.this, view);

        //Intend to Contact_Profile
        bttEditProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), Contact_Profile.class)));


        ivWaiting.setOnClickListener(v ->{
            Postion = 0;
            startActivity(new Intent(getActivity(), Contact_Waiting_Shipping_Dellivering.class));
        });

        ivDelivering.setOnClickListener(v ->{
            Postion = 1;
            startActivity(new Intent(getActivity(), Contact_Waiting_Shipping_Dellivering.class));
        });

        ivFeedBack.setOnClickListener(v ->{
            Postion = 2;
            startActivity(new Intent(getActivity(), Contact_Waiting_Shipping_Dellivering.class));
        });

        // set adapter cho các sản phẩm
        gridView = view.findViewById(R.id.grid_Contact);
        nestedScrollView= view.findViewById(R.id.scrollview_contact);
        productArrayList= new ArrayList<>();


        adapter_information_product= new adapter_Information_product(container.getContext(),R.layout.layout_sanpham,productArrayList);
        gridView.setAdapter(adapter_information_product);

        gridView.setOnTouchListener((v, event) -> {
            nestedScrollView.requestDisallowInterceptTouchEvent(true);
            int action= event.getActionMasked();
            switch (action){
                case MotionEvent.ACTION_UP:
                    nestedScrollView.requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return false;
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




        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        unbinder.unbind();
    }
}