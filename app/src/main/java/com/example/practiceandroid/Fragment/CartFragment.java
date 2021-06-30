package com.example.practiceandroid.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.Purchased_Product.classBought_Product;
import com.example.practiceandroid.R;
import com.example.practiceandroid.cart.adapter_cart;
import com.example.practiceandroid.cart.adapter_cart_SwipeRevealLayout;
import com.example.practiceandroid.cart.class_cart;
import com.example.practiceandroid.dbSQLite.dbHelper;
import com.example.practiceandroid.function.cart_function.getTotalPrice;
import com.example.practiceandroid.function.cart_function.setADDTOCART_product;
import com.example.practiceandroid.function.getCurrent_Day_Time;
import com.example.practiceandroid.home.class_Information_Product;
import com.example.practiceandroid.shopping.activity_shopping;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.practiceandroid.Fragment.CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    // Những thành phần cần hiển thị trong view
    adapter_cart adapterCart;
    ListView listView;
    ArrayList<class_cart> arr_carts;
    DatabaseReference mData;
    public  static dbHelper dbhelper;

    adapter_cart_SwipeRevealLayout adapter_cart;

    // Những thành phần có trong layout
    Button btn_buynow;
    TextView txt_totalPrice;
    TextView txt_CheckAll;
    CheckBox cb_CheckAll;
    TextView txtEmpty;
    ImageView btn_back;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
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
    public static com.example.practiceandroid.Fragment.CartFragment newInstance(String param1, String param2) {
        com.example.practiceandroid.Fragment.CartFragment fragment = new com.example.practiceandroid.Fragment.CartFragment();
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
        mData.child("Cart").child("price_total").setValue("0");
        dbhelper = new dbHelper(getContext(), "Product.sqlite", null, 1);
        dbhelper.QueryData("CREATE TABLE IF NOT EXISTS SANPHAM(Id INTEGER PRIMARY KEY ,NameProduct VARCHAR(200) , PriceProduct VARCHAR(10), NumberProduct VARCHAR(10))");
//        dbhelper.QueryData("DROP TABLE SANPHAM");
//        dbhelper.QueryData("DESC SANPHAM;");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        arr_carts= new ArrayList<>();
        // ADD data vào arr

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_Cart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter_cart = new adapter_cart_SwipeRevealLayout(arr_carts);
        recyclerView.setAdapter(adapter_cart);
        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        showData();
        CheckAll(false);

        txtEmpty = view.findViewById(R.id.textIsEmpty);

        if(arr_carts.size()!= 0) {
            txtEmpty.setText("");
        }
        else
            txtEmpty.setText("Your cart is empty!!!");

        // Set value hiển thị cho phần TOTAL :
        // Set value hiển thị cho phần TOTAL :
        txt_totalPrice= view.findViewById(R.id.totalcart);
        mData.child("Cart").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals("price_total"))
                {
                    txt_totalPrice.setText(snapshot.getValue().toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals("price_total"))
                {
                    txt_totalPrice.setText(snapshot.getValue().toString());
                }
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

        // CHECKBOX SELECT ALL
//        cb_CheckAll = view.findViewById(R.id.checkbox_checked);
//        cb_CheckAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(cb_CheckAll.isChecked()){
//                    cb_CheckAll.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.checkbox_check));
//                    CheckAll(true);
//                    adapter_cart.notifyDataSetChanged();
//                }
//                else{
//                    cb_CheckAll.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.circlebox));
//                    CheckAll(false);
//                    adapter_cart.notifyDataSetChanged();
//                }
//            }
//        });


        // CLICK BUTTON BUY NOW
        // CÓ  NHIỆM VỤ KHI CLICK BUTTON BUYNOW :
        // 1. TẠO THÔNG BÁO VẬN CHUYỂN
        // 2. REMOVE RA KHỎI LIST, SET LẠI ADAPTER
        // 2. CHUYỂN SẢN PHẨM QUA GIAO DIỆN WAITING ĐỂ XÁC NHẬN 'ĐÃ NHẬN HÀNG' RỒI CHUYỂN QUA GIAO DIỆN FEEDBACK ĐỂ COMMENT
        btn_buynow = view.findViewById(R.id.btnbuynow);
        btn_buynow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // kiểm tra xem mảng có rỗng không
                if(arr_carts==null)
                    Toast.makeText(getContext(),"Please add product in Cart. Cart is empty",Toast.LENGTH_SHORT).show();
                else {
                    for (int i = arr_carts.size()-1; i >=0; i--) {
                        if (arr_carts.get(i).getChecked()) {

                            dbhelper.QueryData("INSERT INTO SANPHAM VALUES(null, '"+ arr_carts.get(i).getName_cart_product().toString() + "','"+ arr_carts.get(i).getPrice_cart_product().toString() + "', '"+ arr_carts.get(i).getNumber_product().toString() + "' )");
                            Cursor cursor = dbhelper.GetData("SELECT * FROM SANPHAM");
                            while (cursor.moveToNext()) {
                                Toast.makeText(getContext(), cursor.getString(1), Toast.LENGTH_LONG).show();
                            }
//                            dbhelper.QueryData("DROP TABLE SANPHAM");
                            Intent intent = new Intent(getContext(), activity_shopping.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putString("name_product",arr_carts.get(i).getName_cart_product().toString());
//                            bundle.putString("price_product",arr_carts.get(i).getPrice_cart_product().toString());
//                            bundle.putString("number_product",arr_carts.get(i).getNumber_product().toString());
//                            intent.putExtras(bundle);
                            startActivity(intent);
                            //Toast.makeText(getContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();
//                            String currentDayTime= getCurrent_Day_Time.get();
//                            // format từ 2021/3/3 về 2021 3 3 để firebase không bị lỗi
//                            String currentDay= currentDayTime.split(" ")[0].replace("/"," ");
//                            String currentTime = currentDayTime.split(" ")[1];
//                            classBought_Product BOUGHT =  new classBought_Product(
//                                    "IDuser",
//                                    arr_carts.get(i).getName_cart_product(),
//                                    arr_carts.get(i).getPrice_cart_product(),
//                                    arr_carts.get(i).getNumber_product(),
//                                    currentDayTime,
//                                    false
//                            );
//                            mData.child("Bought_Product").child("IDuser").child(currentDay).child(currentTime).child(arr_carts.get(i).getName_cart_product()).setValue(BOUGHT);
                            Toast.makeText(getContext(),arr_carts.get(i).getName_cart_product(),Toast.LENGTH_SHORT).show();
                            setADDTOCART_product.set(arr_carts.get(i).getName_cart_product(),
                                    arr_carts.get(i).getCategory_cart_product(),
                                    arr_carts.get(i).getId_cart_product(),
                                    false);
                            arr_carts.remove(i);
                            adapter_cart.notifyDataSetChanged();
                        }
                    }

                    // TRƯỚC KHI SET LẠI =0 THÌ PRICE_TOTAL CHÍNH LÀ TỔNG GIÁ TIỀN ĐÃ MUA
                    mData.child("Cart").child("price_total").setValue("0");
                    if(arr_carts.size()==0)
                        txtEmpty.setText("Your cart is empty!!!");

                    ///////////////////// CODE LIEN ////////////////
                    ///// --> HERE
                }
            }
        });
        btn_back = view.findViewById(R.id.btn_back1);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Manhinh_Home.class);
                startActivity(intent);
            }
        });
        return view;
    }
    static  int i=0;
    public void showData(){
        String[] arr_categories ={"Woman","Man","Shoes","Toys","Electronics","Furniture","Phones","Laptop"};

        for(i=0;i< arr_categories.length;i++){
            // lấy sãn phẩm theo mỗi loại từ data xuống và cho hiển thị
            mData.child("Products").child(arr_categories[i]).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if(snapshot.child("add_to_cart").getValue().toString().equals("true"))
                        arr_carts.add(new class_cart(i,
                                snapshot.child("name_product").getValue().toString(),
                                snapshot.child("categories").getValue().toString(),
                                snapshot.child("id_product").getValue().toString(),
                                snapshot.child("price_product_real").getValue().toString(),
                                "1",false
                        ));
                    adapter_cart.notifyDataSetChanged();
                    i++;
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
    }
    public void CheckAll(boolean checked){
        for(int i=0;i<arr_carts.size();i++){
            arr_carts.get(i).setChecked(checked);
        }
    }
}