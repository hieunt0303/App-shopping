package com.example.practiceandroid.chat;

public class class_chat {
    String content;
    Boolean ID;

    public class_chat(String content, Boolean ID) {
        this.content = content;
        this.ID = ID;
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
}
