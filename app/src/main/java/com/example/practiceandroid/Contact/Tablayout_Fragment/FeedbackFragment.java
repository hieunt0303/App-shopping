package com.example.practiceandroid.Contact.Tablayout_Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.practiceandroid.Contact.FeedBack_Comment;
import com.example.practiceandroid.Contact.Tablayout_Fragment.adapter.adapter_Feedback;
import com.example.practiceandroid.Contact.class_comment;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.shopping.FeedBacks_Products;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFragment extends Fragment{
    Unbinder unbinder;

    @BindView(R.id.recyclerview_Data_Feedback)
    RecyclerView rvItem;
    List<FeedBacks_Products> products;
    adapter_Feedback adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackFragment newInstance(String param1, String param2) {
        FeedbackFragment fragment = new FeedbackFragment();
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
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        unbinder = ButterKnife.bind(FeedbackFragment.this, view);
        rvItem.setLayoutManager(new LinearLayoutManager(FeedbackFragment.this.getContext()));
        rvItem.setHasFixedSize(true);
        DatabaseReference DATA = FirebaseDatabase.getInstance().getReference().child("Bought_FeedBacks").child(Manhinh_Login.userlogin.getName_user());
        FirebaseRecyclerOptions<FeedBacks_Products> options =
                new FirebaseRecyclerOptions.Builder<FeedBacks_Products>()
                        .setQuery(DATA, FeedBacks_Products.class)
                        .build();
        adapter = new adapter_Feedback(options);
        rvItem.setAdapter(adapter);
        adapter.setOnclickListener((snapshot, position) -> {
            FeedBacks_Products newProduct = snapshot.getValue(FeedBacks_Products.class);
            Intent intent = new Intent(getContext().getApplicationContext(), FeedBack_Comment.class);
            Toast.makeText(getContext(), newProduct.getProductName(), Toast.LENGTH_SHORT).show();
            class_comment.productName = newProduct.getProductName();
            class_comment.Categories = newProduct.getProductCategory();
            class_comment.productID = newProduct.getProductID();
            class_comment.userName = newProduct.getUserName();
            startActivity(intent);
        });
//            FeedBacks_Products item = documentSnapshot.
//            Intent intent = new Intent(FeedbackFragment.this.getContext(), FeedBack_Comment.class);
//            intent.putExtra("prName", item.getProductName());
//            intent.putExtra("prCategories", item.getProductCategory());
//            intent.putExtra("prID", item.getProductID());
//            intent.putExtra("usID", item.getUserName());
//            startActivity(intent);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        unbinder.unbind();
    }


}






//        products.add(new FeedBacks_Products(
//                snapshot.child("productID").getValue().toString(),
//                snapshot.child("productName").getValue().toString(),
//                snapshot.child("productCategory").getValue().toString(),
//                snapshot.child("productPrice").getValue().toString(),
//                snapshot.child("userName").getValue().toString()
//        ));