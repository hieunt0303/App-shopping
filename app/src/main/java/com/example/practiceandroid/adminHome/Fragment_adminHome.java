package com.example.practiceandroid.adminHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.admin_Home;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_adminHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_adminHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        if(Manhinh_Login.userlogin.name_user.equals("admin"))
        {
            Toast.makeText(getActivity(),  "Tài khoản la admin",

                    Toast.LENGTH_LONG).show();
        }
        return inflater.inflate(R.layout.fragment_admin_home, container, false);
    }
}