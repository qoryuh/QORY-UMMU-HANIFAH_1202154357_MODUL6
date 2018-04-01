package com.example.hnf.qoryummuhanifah_1202154357_modul6.Constant;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by HNF on 3/31/2018.
 */

public class Constant {

    //Firebase Database
    public final static DatabaseReference mRefUser = FirebaseDatabase.getInstance().getReference().child("Users");
    public final static DatabaseReference mRefImage = FirebaseDatabase.getInstance().getReference().child("Images");

    //Firebase Auth
    public final static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseUser currentUser;

    //Firebase Storage
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    public static StorageReference storageRef = storage.getReference();

}
