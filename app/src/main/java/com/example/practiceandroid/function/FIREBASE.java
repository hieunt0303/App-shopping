package com.example.practiceandroid.function;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.PortUnreachableException;

public class FIREBASE {
    public static DatabaseReference MDATA= FirebaseDatabase.getInstance().getReference();
}
