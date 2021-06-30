package com.example.practiceandroid.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practiceandroid.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class adapter_notification extends BaseAdapter {
    public Context context;
    int layout;
    ArrayList<class_notification> notifications;

    public adapter_notification(Context context, int layout, ArrayList<class_notification> notifications) {
        this.context = context;
        this.layout = layout;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {

        if(notifications == null)
        {
            return  0;
        }
        return notifications.size() ;
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
        convertView = inflater.inflate(layout,null);

        ImageView img_notification = convertView.findViewById(R.id.img_notification);
        TextView txt_title = convertView.findViewById(R.id.txt_title);
        TextView txt_time = convertView.findViewById(R.id.txt_time);
        TextView txt_info = convertView.findViewById(R.id.txt_notification);

        txt_title.setText(notifications.get(position).text_Notification);
        txt_info.setText(notifications.get(position).text_Information);
        txt_time.setText(notifications.get(position).text_Time);

        switch (notifications.get(position).id)
        {
            case "1":
                img_notification.setImageResource(R.drawable.promos);
                break;
            case "2":
                img_notification.setImageResource(R.drawable.order);
                break;
            case "3":
                img_notification.setImageResource(R.drawable.delivery);
                break;
            case  "4":
                img_notification.setImageResource(R.drawable.account);
                break;
        }
        return convertView;
    }
}
