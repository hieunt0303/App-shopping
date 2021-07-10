package com.example.practiceandroid.AdminContent.AdminChat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.practiceandroid.Fragment.SpeechFragment;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.function.admin.chat.ExistUserChat;
import com.example.practiceandroid.home.class_Information_Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class adapter_item_adminchat extends BaseAdapter {
    public Context context;
    int layout;
    List<String> arr_User;

    // BIẾN STRING CHỨA MÃ CODE CỦA ĐOẠN CODE CUỐI CÙNG CỦA MỖI USER, ĐỂ CLICK VÀO THÌ CÁI HIỂN THỊ SẼ ĐỔI MÀU ( ĐÃ XEM  )
    String CODE="";

    public adapter_item_adminchat(Context context, int layout,List<String> arr_User) {
        this.context = context;
        this.layout = layout;
        this.arr_User = arr_User;
    }
    @Override
    public int getCount() {
        return arr_User.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView= inflater.inflate(layout,null);

        // Anh xa
        ImageView imgview_avatar = convertView.findViewById(R.id.imageview_adminchat);
        TextView txt_nameUser= convertView.findViewById(R.id.textview_User_adminchat);
        TextView txt_lastContent= convertView.findViewById(R.id.textview_contentLast_adminchat);
        TextView txt_lastTime= convertView.findViewById(R.id.textview_Time_adminchat);
        ImageView imgview_Seen= convertView.findViewById(R.id.imageview_Seen_adminchat);

        // Gan gia tri
        // NÀO LÀM XONG BÊN SETTING VÔ ĐÂY ĐỔI LẠI CÁI ĐƯỜNG DẪN AVATAR CỦA USER
        imgview_avatar.setBackground(ContextCompat.getDrawable(context,R.drawable.avatar));

        txt_nameUser.setText(arr_User.get(position).toString());

        FIREBASE.MDATA.child("Message").child(arr_User.get(position).toString()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.child("seen").getValue().toString().equals("false")){
                    // NẾU ADMIN CHƯA SEEN
                    imgview_Seen.setBackground(ContextCompat.getDrawable(context,R.drawable.icon_notseen_adminchat));
                    txt_lastContent.setTypeface(null, Typeface.BOLD);
                }else{
                    imgview_Seen.setBackground(ContextCompat.getDrawable(context,R.drawable.circlebox));
                    txt_lastContent.setTypeface(null, Typeface.NORMAL);
                }
                txt_lastContent.setText(snapshot.child("content").getValue().toString());
                txt_lastTime.setText(snapshot.child("time").getValue().toString());
                CODE= snapshot.getKey().toString();
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

        // CLICK ITEM CHAT ĐỂ CHAT TỪNG USER
        imgview_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,CODE,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Activity_AdminChat.class);
                intent.putExtra("nameUser",arr_User.get(position).toString());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
