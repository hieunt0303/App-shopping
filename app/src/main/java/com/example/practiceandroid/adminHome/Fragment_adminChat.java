package com.example.practiceandroid.adminHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.practiceandroid.AdminContent.AdminChat.adapter_item_adminchat;
import com.example.practiceandroid.R;
import com.example.practiceandroid.function.functionUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_adminChat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_adminChat extends Fragment {
    ListView listView_chatadmin;
    adapter_item_adminchat adapterItemAdminchat;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_adminChat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_adminChat.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_adminChat newInstance(String param1, String param2) {
        Fragment_adminChat fragment = new Fragment_adminChat();
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
        View view= inflater.inflate(R.layout.fragment_admin_chat, container, false);

        // ANh xa
        listView_chatadmin= view.findViewById(R.id.list_ChatAdmin);
        adapterItemAdminchat= new adapter_item_adminchat(container.getContext(),R.layout.layout_item_chat_admin, functionUser.getArrUser());
        listView_chatadmin.setAdapter(adapterItemAdminchat);

        return view;
    }
}