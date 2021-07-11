package com.example.practiceandroid.function;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.example.practiceandroid.Fragment.HomeFragment;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.notification.activity_notification;
import com.example.practiceandroid.notification.class_notification;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


@RequiresApi(api = Build.VERSION_CODES.O)
public class pushNotification {
    public  static DatabaseReference mData= FirebaseDatabase.getInstance().getReference();
    public static Random random;
    private Resources res;
    public  static  void pushnotification(String id, Context context)
    {
        String currentDayTime= getCurrent_Day_Time.get();
        String currentDay= currentDayTime.split(" ")[0].replace("/"," ");
        String currentTime = currentDayTime.split(" ")[1];
        HomeFragment.number +=1;
        getNumberNotification.setNumber(String.valueOf(HomeFragment.number));
        random = new Random();
        switch (id)
        {
            case "1":
                String id1 = "1";
                String  title1 = "Promos";
                String information1 = "Discount 30% iPhone 12 128GB";
                String time1 = currentDay + " " + currentTime;
                class_notification order1 = new class_notification(id1, title1, information1, time1);
                mData.child("Notification").child(Manhinh_Login.userlogin.getName_user()).push().setValue(order1);
                Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.order);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
                builder.setContentTitle(title1);
                builder.setContentText(information1);
                builder.setLargeIcon(bitmap1);
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);
                Intent intent = new Intent(context, activity_notification.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addParentStack(activity_notification.class);
                stackBuilder.addNextIntent(intent);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                managerCompat.notify(random.nextInt(1000), builder.build());
                break;
            case "2":
                String id2 = "2";
                String  title2 = "Order";
                String information2 = "Your order #" + random.nextInt(1000000) + " is success";
                String time2 = currentDay + " " + currentTime;
                class_notification order2 = new class_notification(id2, title2, information2, time2);
                mData.child("Notification").child(Manhinh_Login.userlogin.getName_user()).push().setValue(order2);
                Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.order);
                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context, "My Notification");
                builder1.setContentTitle(title2);
                builder1.setContentText(information2);
                builder1.setLargeIcon(bitmap2);
                builder1.setSmallIcon(R.drawable.ic_launcher_background);
                builder1.setAutoCancel(true);

                Intent intent1 = new Intent(context, activity_notification.class);
//                            intent.putExtra("number", number +1);
                TaskStackBuilder stackBuilder1 = TaskStackBuilder.create(context);
                stackBuilder1.addParentStack(activity_notification.class);
                stackBuilder1.addNextIntent(intent1);
                PendingIntent pendingIntent1 = stackBuilder1.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder1.setContentIntent(pendingIntent1);

                NotificationManagerCompat managerCompat1 = NotificationManagerCompat.from(context);
                managerCompat1.notify(random.nextInt(1000), builder1.build());
                break;
            case "3":
                String id3 = "3";
                String  title3 = "Order";
                String information3 = "Your order is success";
                String time3 = currentDay + " " + currentTime;
                class_notification order3 = new class_notification(id3, title3, information3, time3);
                mData.child("Notification").child(Manhinh_Login.userlogin.getName_user()).push().setValue(order3);
                Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.delivery);
                NotificationCompat.Builder builder3 = new NotificationCompat.Builder(context, "My Notification");
                builder3.setContentTitle(title3);
                builder3.setContentText(information3);
                builder3.setLargeIcon(bitmap3);
                builder3.setSmallIcon(R.drawable.ic_launcher_background);
                builder3.setAutoCancel(true);

                Intent intent3 = new Intent(context, activity_notification.class);
//                            intent.putExtra("number", number +1);
                TaskStackBuilder stackBuilder3 = TaskStackBuilder.create(context);
                stackBuilder3.addParentStack(activity_notification.class);
                stackBuilder3.addNextIntent(intent3);
                PendingIntent pendingIntent3 = stackBuilder3.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder3.setContentIntent(pendingIntent3);

                NotificationManagerCompat managerCompat3 = NotificationManagerCompat.from(context);
                managerCompat3.notify(random.nextInt(1000), builder3.build());
                break;
            case "4":
                String id4 = "4";
                String  title4 = "Order";
                String information4 = "Your order is success";
                String time4 = currentDay + " " + currentTime;
                class_notification order4 = new class_notification(id4, title4, information4, time4);
                mData.child("Notification").child(Manhinh_Login.userlogin.getName_user()).push().setValue(order4);
                Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.account);
                NotificationCompat.Builder builder4 = new NotificationCompat.Builder(context, "My Notification");
                builder4.setContentTitle(title4);
                builder4.setContentText(information4);
                builder4.setLargeIcon(bitmap4);
                builder4.setSmallIcon(R.drawable.ic_launcher_background);
                builder4.setAutoCancel(true);

                Intent intent4 = new Intent(context, activity_notification.class);
//                            intent.putExtra("number", number +1);
                TaskStackBuilder stackBuilder4 = TaskStackBuilder.create(context);
                stackBuilder4.addParentStack(activity_notification.class);
                stackBuilder4.addNextIntent(intent4);
                PendingIntent pendingIntent4 = stackBuilder4.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder4.setContentIntent(pendingIntent4);

                NotificationManagerCompat managerCompat4= NotificationManagerCompat.from(context);
                managerCompat4.notify(random.nextInt(1000), builder4.build());
                break;
               }
    }
}
