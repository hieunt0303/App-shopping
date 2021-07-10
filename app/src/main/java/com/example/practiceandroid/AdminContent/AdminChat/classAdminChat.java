package com.example.practiceandroid.AdminContent.AdminChat;

public class classAdminChat {
    String name;
    String lastContent;
    String time;
    Boolean seen;

    public classAdminChat(String name, String lastContent, String time, Boolean seen) {
        this.name = name;
        this.lastContent = lastContent;
        this.time = time;
        this.seen = seen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
}
