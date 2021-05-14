package com.example.practiceandroid.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceandroid.R;

import java.util.List;


public class adapter_chat_recycleview  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static int TYPE_USER =1;
    private  static  int TYPE_SERVER =0;

    private List<class_chat> mChat;
    public void setData(List<class_chat> list){
        this.mChat= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(TYPE_USER == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout_user,parent,false);
            return new User_viewholder(view);
        }else if( TYPE_SERVER == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout_admin,parent,false);
            return new Server_viewholder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        class_chat user = mChat.get(position);
        if(user == null)
            return;
        if(TYPE_SERVER== holder.getItemViewType()){
            Server_viewholder serverViewholder = (Server_viewholder) holder;
            serverViewholder.img_admin.setImageResource(R.drawable.avatar_app);
            serverViewholder.txt_Content_server.setText(mChat.get(position).content);

        }else if(TYPE_USER == holder.getItemViewType()){
            User_viewholder userViewholder = (User_viewholder) holder;
            userViewholder.img_user.setImageResource(R.drawable.icon_tick_seen);
            userViewholder.txt_Content_user.setText(mChat.get(position).content);
        }
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    @Override
    public int getItemViewType(int position) {
        class_chat user = mChat.get(position);
        if(user.getID()){
            return TYPE_SERVER;
        }else
            return TYPE_USER;
    }

    public class User_viewholder extends RecyclerView.ViewHolder{

        //TextView txt_Name_user;
        ImageView img_user;
        TextView txt_Content_user;

        public User_viewholder(@NonNull View itemView) {
            super(itemView);
            txt_Content_user =  itemView.findViewById(R.id.content_chat_user);
            img_user= itemView.findViewById(R.id.imageView_seen_chat_user);
        }
    }

    public class Server_viewholder extends RecyclerView.ViewHolder{
        //TextView txt_Name_server;
        ImageView img_admin;
        TextView txt_Content_server;

        public Server_viewholder(@NonNull View itemView) {
            super(itemView);
            txt_Content_server= itemView.findViewById(R.id.content_chat_admin);
            img_admin= itemView.findViewById(R.id.imageView_chat_admin);
        }
    }
}
