package com.example.practiceandroid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.practiceandroid.Contact.Contact_Profile;
import com.example.practiceandroid.Contact.Contact_Waiting_Shipping_Dellivering;
import com.example.practiceandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.practiceandroid.Fragment.ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    Button bttEditProfile;

    ImageView ivWaiting, ivDelivering, ivFeedBack;
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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        //Intend to Contact_Profile
        bttEditProfile = view.findViewById(R.id.edit_profile);
        bttEditProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), Contact_Profile.class)));

        //Intend to Tablayout_Waiting_Delivering_FeedBack
        ivWaiting = view.findViewById(R.id.waiting);
        ivDelivering = view.findViewById(R.id.delivering);
        ivFeedBack = view.findViewById(R.id.feedback);

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




        return view;
    }
}