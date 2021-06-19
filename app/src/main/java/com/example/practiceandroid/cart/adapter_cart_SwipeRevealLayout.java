package com.example.practiceandroid.cart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.practiceandroid.R;
import com.example.practiceandroid.function.cart_function.getTotalPrice;
import com.example.practiceandroid.function.getCategory_from_NameProduct;
import com.example.practiceandroid.function.getIDproduct_from_NameProduct;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class adapter_cart_SwipeRevealLayout extends RecyclerView.Adapter<adapter_cart_SwipeRevealLayout.UserViewHolder> {

    ArrayList<class_cart> myarr_cart;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    DatabaseReference mData= FirebaseDatabase.getInstance().getReference();
    View view;
    public adapter_cart_SwipeRevealLayout(ArrayList<class_cart> myarr_cart) {
        this.myarr_cart = myarr_cart;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cart,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        class_cart list= myarr_cart.get(position);
        if(list== null)
            return;

        viewBinderHelper.bind(holder.swipeRevealLayout,String.valueOf(list.id));

        // SET GIA TRI
        holder.txt_name_cart_product.setText(myarr_cart.get(position).name_cart_product);
        holder.txt_price_cart_product.setText(myarr_cart.get(position).price_cart_product);
        holder.txt_number_product.setText(myarr_cart.get(position).number_product);

        //SET CHECK  KHI CHỌN SELECT ALL TỪ GIAO GIỆN CART
        String total_before = getTotalPrice.getTotal();
        int number_product =Integer.parseInt( holder.txt_number_product.getText().toString());
//        if(myarr_cart.get(position).getChecked()) {
//            holder.cb_checked.setChecked(true);
//            myarr_cart.get(position).setChecked(true);
//            // Vô hiệu quá 2 cái button -,+
//            holder.btn_add.setClickable(false);
//            holder.btn_minus.setClickable(false);
//            holder.btn_add.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.checkbox_add_disabled));
//            holder.btn_minus.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.checkbox_minus_disable));
//
//            holder.cb_checked.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.checkbox_check));
//            int total=  Integer.parseInt(total_before)+number_product* Integer.parseInt(holder.txt_price_cart_product.getText().toString());
//            String total_after =String.valueOf(total);
//            mData.child("Cart").child("price_total").setValue(total_after);
//        }
//        else {
//            holder.cb_checked.setChecked(false);
//            myarr_cart.get(position).setChecked(false);
//            // Bỏ vô hiệu hóa 2 cái nút
//            holder.btn_add.setClickable(true);
//            holder.btn_minus.setClickable(true);
//            holder.btn_add.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.button_add));
//            holder.btn_minus.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.button_minus));
//
//            holder.cb_checked.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.circlebox));
//            String total_after =String.valueOf( Integer.parseInt(total_before)-number_product* Integer.parseInt(holder.txt_price_cart_product.getText().toString()));
//            mData.child("Cart").child("price_total").setValue(total_after);
//        }


        // Set button DElete
        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number_click==1) {
                    CATEGORY = getCategory_from_NameProduct.getCategory(myarr_cart.get(position).name_cart_product);
                    ID_PRODUCT = getIDproduct_from_NameProduct.getID(myarr_cart.get(position).name_cart_product);
                    mData.child("Products").child(CATEGORY).child(ID_PRODUCT).child("add_to_cart").setValue(false);
                    Toast.makeText(view.getContext(), myarr_cart.get(position).name_cart_product, Toast.LENGTH_SHORT).show();
                    Toast.makeText(view.getContext(), "CATEGORY:" + CATEGORY, Toast.LENGTH_LONG).show();
                    Toast.makeText(view.getContext(), "ID_PRODUCT:" + ID_PRODUCT, Toast.LENGTH_LONG).show();
                    
                    myarr_cart.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    number_click=0;
                }
                else {
                    CATEGORY = getCategory_from_NameProduct.getCategory(myarr_cart.get(position).name_cart_product);
                    ID_PRODUCT = getIDproduct_from_NameProduct.getID(myarr_cart.get(position).name_cart_product);
                    number_click = 1;
                }
                //Toast.makeText(view.getContext(),String.valueOf(myarr_cart.size()),Toast.LENGTH_SHORT).show();
                //Toast.makeText(view.getContext(),myarr_cart.get(myarr_cart.size()-1).name_cart_product,Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        holder.cb_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String total_before = getTotalPrice.getTotal();
                int number_product =Integer.parseInt( holder.txt_number_product.getText().toString());
                if(holder.cb_checked.isChecked()){
                    myarr_cart.get(position).setChecked(true);
                    // Vô hiệu quá 2 cái button -,+
                    holder.btn_add.setClickable(false);
                    holder.btn_minus.setClickable(false);
                    holder.btn_add.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.checkbox_add_disabled));
                    holder.btn_minus.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.checkbox_minus_disable));

                    holder.cb_checked.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.checkbox_check));
                    int total=  Integer.parseInt(total_before)+number_product* Integer.parseInt(holder.txt_price_cart_product.getText().toString());
                    String total_after =String.valueOf(total);
                    mData.child("Cart").child("price_total").setValue(total_after);
                }
                else{
                    myarr_cart.get(position).setChecked(false);
                    // Bỏ vô hiệu hóa 2 cái nút
                    holder.btn_add.setClickable(true);
                    holder.btn_minus.setClickable(true);
                    holder.btn_add.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.button_add));
                    holder.btn_minus.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.button_minus));

                    holder.cb_checked.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.circlebox));
                    String total_after =String.valueOf( Integer.parseInt(total_before)-number_product* Integer.parseInt(holder.txt_price_cart_product.getText().toString()));
                    mData.child("Cart").child("price_total").setValue(total_after);
                }
            }
        });

        // Set click cho 2 button -, +
        holder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txt_number_product.getText().toString().equals("0"))
                    Toast.makeText(view.getContext(),"You can not do it.",Toast.LENGTH_SHORT).show();
                else{
                    int number_next= Integer.parseInt( holder.txt_number_product.getText().toString())-1;
                    holder.txt_number_product.setText(String.valueOf(number_next));
                }
            }
        });

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number_next= Integer.parseInt( holder.txt_number_product.getText().toString())+1;
                holder.txt_number_product.setText(String.valueOf(number_next));
            }
        });

        StorageReference mStorage;
        String name_product= myarr_cart.get(position).name_cart_product;
        mStorage = FirebaseStorage.getInstance().getReference().child(name_product+"/"+ToLowerCase_Trim(name_product)+1+".png");
        try {
            final File localfile= File.createTempFile(ToLowerCase_Trim(name_product)+1,"png");
            mStorage.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(HomeFragment.this,"Đưa ảnh lên thành công !!!",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            holder.img_cart_product.setImageBitmap(bitmap);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    static  String CATEGORY;
    static  String ID_PRODUCT;
    static int number_click=0;
    @Override
    public int getItemCount() {
        if(myarr_cart != null)
            return myarr_cart.size();
        return 0;
    }

    public  class UserViewHolder extends RecyclerView.ViewHolder{

        // khai báo những thành phần view có trong view item
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;

        TextView txt_name_cart_product;
        TextView txt_price_cart_product;
        TextView txt_number_product;
        ImageView img_cart_product;
        Button btn_minus;
        Button btn_add;
        CheckBox cb_checked;
        CheckBox cb_delete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            // Anh xa
            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
            layoutDelete = itemView.findViewById(R.id.layout_delete_cart);

            txt_name_cart_product= itemView.findViewById(R.id.productname_cart);
            txt_price_cart_product= itemView.findViewById(R.id.productprice_cart);
            txt_number_product= itemView.findViewById(R.id.txt_number_cart);
            img_cart_product= itemView.findViewById(R.id.productimage_cart);
            btn_minus= itemView.findViewById(R.id.btn_minus_cart);
            btn_add= itemView.findViewById(R.id.btn_add_cart);
            cb_checked= itemView.findViewById(R.id.box_checked_cart);

        }
    }
    public  static String ToLowerCase_Trim(String data){
        //làm thành chữ thường và bỏ dấu cách
        String [] arrdata= data.split(" ");
        String trim="";
        for(int i=0;i<arrdata.length;i++){
            trim = trim + arrdata[i].toLowerCase();
        }
        return  trim;
    }
}
