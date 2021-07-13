package com.example.practiceandroid.adminHome;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.practiceandroid.Contact.Contact_Profile;
import com.example.practiceandroid.DatabaseUserLogin;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.ThongKe;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_adminStatic#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_adminStatic extends Fragment {

    List<ThongKe> DS ;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    Button btnCheck;
    ImageButton logout;
    LineChart lineChart;
    private Spinner Thang;
    DatabaseUserLogin databaseUserLogin;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_adminStatic() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_adminStatic.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_adminStatic newInstance(String param1, String param2) {
        Fragment_adminStatic fragment = new Fragment_adminStatic();
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
        View view =  inflater.inflate(R.layout.fragment_admin_static, container, false);
        databaseUserLogin = new DatabaseUserLogin(getActivity(), "user.sqlite", null, 1);
        DS = new ArrayList<>();
        btnCheck = view.findViewById(R.id.buttonTest);
        lineChart = view.findViewById(R.id.linechart);
        Thang = view.findViewById(R.id.Thang);
        logout = view.findViewById(R.id.btnlogout);
        List<String> list = new ArrayList<>();
        for(int i =0; i<12;i++)
        {
            if(i<9)
            {
                list.add("0"+(i+1));
            }
            else {
                list.add(String.valueOf((i + 1)));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        Thang.setAdapter(adapter);
        mData.child("Statistic").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ThongKe thongKe = snapshot.getValue(ThongKe.class);
                DS.add(thongKe);
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
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseUserLogin.QueryData("Drop table User");
                Intent mh = new Intent(getContext(), Manhinh_Login.class);
                startActivity(mh);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    private void check()
    {
        LineDataSet lineDataSet = new LineDataSet(data(),"Doanh thu");
        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.invalidate();
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getActivity(), "Ngày: "
                                + (h.getX()+1)
                                + ", Doanh thu: "
                                + e.getY()
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
    private ArrayList<Entry> data()
    {
        String thang = Thang.getSelectedItem().toString();
        String ngay = "0";
        int doanhthu;
        ArrayList<Entry> datavals = new ArrayList<>();
        for(int i =0 ;i<31;i++)
        {
            ngay = "0";
            doanhthu = 0;
            if((i+1)<10)
            {
                ngay = ngay + (i+1);
            }
            else
            {
                ngay = String.valueOf((i+1));
            }
            for(int j =0 ;j < DS.size();j++)
            {
                String ngaymua = "/"+thang+"/"+ngay;
                if(DS.get(j).day.contains(ngaymua))
                {
                    doanhthu += Integer.parseInt(DS.get(j).total_price);
                }
            }
            datavals.add(new Entry(i,doanhthu));
        }
        return datavals;
    }
}