package com.example.practiceandroid.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.practiceandroid.R;
import com.example.practiceandroid.function.getNumberCategories;
import com.example.practiceandroid.home.Category.CategoryProduct;
import com.example.practiceandroid.home.Detail_Information_Product;
import com.example.practiceandroid.home.class_Information_Product;
import com.example.practiceandroid.search.adapter_Search_product;
import com.example.practiceandroid.search.class_Search_product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.practiceandroid.Fragment.SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // Anh xa
    EditText editTextSearch;
    ListView listViewSearch;
    adapter_Search_product adapter_search_product;
    ArrayList<class_Information_Product> class_search_productArrayList;
    ArrayList<class_Information_Product> class_search_productArrayList_temp;

    // THÀNH PHẦN TRONG SEARCH
    TextView txt_recent_search_history;
    TextView txt_trending_search;
    TextView txt_cetagories;
    ImageView imgbtn_scan;

    // CÁC BUTTON ĐỂ NHẤN KẾT QUẢ
    Button btn_Converse;
    Button btn_Balenciaga;
    Button btn_wonmanfashion;
    Button btn_manfashion;
    Button btn_Watch;
    Button btn_LaptopAndComputer;
    Button btn_Mobile;
    Button btn_Furniture;
    Button btn_Shoes;
    int temp_before_search=0;

    DatabaseReference mData;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
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
    public static com.example.practiceandroid.Fragment.SearchFragment newInstance(String param1, String param2) {
        com.example.practiceandroid.Fragment.SearchFragment fragment = new com.example.practiceandroid.Fragment.SearchFragment();
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

        mData = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        TextView txt_recent_search_history=view.findViewById(R.id.textView_recentsearchhistory);
        TextView txt_cetagories= view.findViewById(R.id.textView_categories);


        editTextSearch= view.findViewById(R.id.editText_Search);
        listViewSearch= view.findViewById(R.id.listview_Search_product);
        // gan gia tri cac thuoc tinh đẻ hiển thị list view
        class_search_productArrayList= new ArrayList<>();
        class_search_productArrayList_temp= new ArrayList<>();

        adapter_search_product= new adapter_Search_product(container.getContext(),R.layout.layout_search_product,class_search_productArrayList);
        listViewSearch.setAdapter(adapter_search_product);

        // Duyệt FireBase để add dữ liệu vào
        String[] arr_categories ={"Woman","Man","Shoes","Toys","Electronics","Furniture","Phones","Laptop"};
        int i=0;
        for(i=0;i< arr_categories.length;i++){
            // lấy sãn phẩm theo mỗi loại từ data xuống và cho hiển thị
            mData.child("Products").child(arr_categories[i]).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    // add class
                    class_search_productArrayList_temp.add(new class_Information_Product(
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
                    //adapter_search_product.notifyDataSetChanged();
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
        // Lưu trữ độ dài chữ sau mỗi lần gõ sẽ gọi hàm fitter

        // SCAN
        imgbtn_scan= view.findViewById(R.id.imagebutton_scan);
        imgbtn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0 ){
                    // khi đã nhập
                    // text cho hiển htị list view cà ẩn đi các thành phẩn ở dưới

                    //Mỗi lần chữ khi nhập thay đổi thì reset lại hàm tìm kiếm theo tên
                    if(s.length()!= temp_before_search) {
                        class_search_productArrayList.clear();
                        Fitter_By_Name(s.toString());
                        temp_before_search= s.length();
                    }
                    listViewSearch.setVisibility(View.VISIBLE);
                    listViewSearch.setElevation(5f);

                }
                else if (s.length()==0){
                    // chưa nhập text
                    class_search_productArrayList.clear();
                    temp_before_search=0;
                    // cho hiển thị listview và hide cá thành phần ở dưới
                    listViewSearch.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //class_search_productArrayList.clear();

        // ÁNH XẠ , GÁN GIÁ TRJ VÀ SET ONCLICK CHO CÁC BUTTON PHÍA DƯỚI
         btn_Converse= view.findViewById(R.id.buttonConverse_Chuck_70);
         btn_Balenciaga= view.findViewById(R.id.button_Balenciaga);
         btn_wonmanfashion= view.findViewById(R.id.button_search_woman);
         btn_manfashion = view.findViewById(R.id.button_search_man);
         btn_Watch=view.findViewById(R.id.button_search_watch);
         btn_LaptopAndComputer= view.findViewById(R.id.button_search__laptop);
         btn_Mobile= view.findViewById(R.id.button_search_mobile);
         btn_Furniture=view.findViewById(R.id.button_search_furniture);
         btn_Shoes= view.findViewById(R.id.button_search_shoes);

        Intent intent_detail_product_search = new Intent(getContext(), Detail_Information_Product.class );
         btn_Converse.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent_detail_product_search.putExtra("Name_product","Converse Chuck 70");
                 intent_detail_product_search.putExtra("Category_product","Shoes");
                 intent_detail_product_search.putExtra("Id_product","SP0013");
                 intent_detail_product_search.putExtra("Price_product_real","11554");
                 intent_detail_product_search.putExtra("Sum_Ratingbar","5");
                 intent_detail_product_search.putExtra("Sum_Bought",20);
                 intent_detail_product_search.putExtra("description","The best ever chuck 70, in vintage canvas ");
                 intent_detail_product_search.putExtra("detail","Why you should be down\n Crafted in recycled");

                 startActivity(intent_detail_product_search);
             }
         });
         btn_Balenciaga.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent_detail_product_search.putExtra("Name_product","Balenciaga Triple S White Black Pink");
                 intent_detail_product_search.putExtra("Category_product","Shoes");
                 intent_detail_product_search.putExtra("Id_product","SP0008");
                 intent_detail_product_search.putExtra("Price_product_real","5993");
                 intent_detail_product_search.putExtra("Sum_Ratingbar","5");
                 intent_detail_product_search.putExtra("Sum_Bought",20);
                 intent_detail_product_search.putExtra("description","The best ever chuck 70, in vintage canvas ");
                 intent_detail_product_search.putExtra("detail","Why you should be down\n Crafted in recycled");

                 startActivity(intent_detail_product_search);
             }
         });

        Intent intent_search= new Intent(getContext(), CategoryProduct.class);
         btn_wonmanfashion.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent_search.putExtra("nameCategory","Woman");
                 intent_search.putExtra("numberCategory",String.valueOf(getNumberCategories.Get("Watch")));
                 startActivity(intent_search);
             }
         });
         btn_manfashion.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent_search.putExtra("nameCategory","Man");
                 intent_search.putExtra("numberCategory",String.valueOf(getNumberCategories.Get("Man")));
                 startActivity(intent_search);
             }
         });
         btn_Watch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent_search.putExtra("nameCategory","Watch");
                 intent_search.putExtra("numberCategory",String.valueOf(getNumberCategories.Get("Watch")));
                 startActivity(intent_search);
             }
         });
         btn_LaptopAndComputer.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent_search.putExtra("nameCategory","Laptop");
                 intent_search.putExtra("numberCategory",String.valueOf(getNumberCategories.Get("Laptop")));
                 startActivity(intent_search);
             }
         });
         btn_Mobile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent_search.putExtra("nameCategory","Phones");
                 intent_search.putExtra("numberCategory",String.valueOf(getNumberCategories.Get("Phones")));
                 startActivity(intent_search);
             }
         });
         btn_Furniture.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent_search.putExtra("nameCategory","Furniture");
                 intent_search.putExtra("numberCategory",String.valueOf(getNumberCategories.Get("Furniture")));
                 startActivity(intent_search);
             }
         });
         btn_Shoes.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent_search.putExtra("nameCategory","Shoes");
                 intent_search.putExtra("numberCategory",String.valueOf(getNumberCategories.Get("Shoes")));
                 startActivity(intent_search);
             }
         });
        return view;
    }


    // TÌM KIẾM THEO TÊN
    public void Fitter_By_Name(String name){
        // nhập vào bao nhiêu chữ
        int size_name = name.length();
        class_search_productArrayList_temp.size();
        for(int i=0;i< class_search_productArrayList_temp.size();i++){
            if(class_search_productArrayList_temp.get(i).name_product.contains(name))
                class_search_productArrayList.add(class_search_productArrayList_temp.get(i));
        }
        adapter_search_product.notifyDataSetChanged();
    }

}