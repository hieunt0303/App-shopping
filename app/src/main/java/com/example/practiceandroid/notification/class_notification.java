package com.example.practiceandroid.notification;

public class class_notification {
    public String id;
    public String text_Notification;
    public String text_Information;
    public String text_Time;

    public class_notification(String id, String text_Notification, String text_Information, String text_Time) {
        this.id = id;
        this.text_Notification = text_Notification;
        this.text_Information = text_Information;
        this.text_Time = text_Time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText_Notification() {
        return text_Notification;
    }

    public void setText_Notification(String text_Notification) {
        this.text_Notification = text_Notification;
    }

    public String getText_Information() {
        return text_Information;
    }

    public void setText_Information(String text_Information) {
        this.text_Information = text_Information;
    }

    public String getText_Time() {
        return text_Time;
    }

    public void setText_Time(String text_Time) {
        this.text_Time = text_Time;
    }
}
