package com.example.practiceandroid.chat;

public class class_chat {
    String content;
    Boolean ID;
    String time;
    Boolean seen;
    String code;

    public class_chat(String content, Boolean ID, String time, Boolean seen, String code) {
        this.content = content;
        this.ID = ID;
        this.time = time;
        this.seen = seen;
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getID() {
        return ID;
    }

    public void setID(Boolean ID) {
        this.ID = ID;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
