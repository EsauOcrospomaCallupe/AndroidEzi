package com.example.esauocrospoma.appar.Managers;


import com.example.esauocrospoma.appar.Util.FirebaseConstants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by NriKe on 27/06/2017.
 */

public class FirebaseManager {
    private FirebaseDatabase database;
    private DatabaseReference usersReference;

    private static FirebaseManager instance;

    public FirebaseManager() {
        database = FirebaseDatabase.getInstance();
        usersReference = database.getReference(FirebaseConstants.REF_DATA).child(FirebaseConstants.CHILD_USERS);
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public static FirebaseManager getInstance(){
        if (instance == null){
            instance = new FirebaseManager();
        }
        return instance;
    }

    public DatabaseReference getUsersReference() {
        return usersReference;
    }
}
