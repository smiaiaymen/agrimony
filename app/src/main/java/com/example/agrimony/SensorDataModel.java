package com.example.agrimony;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SensorDataModel {
    private DatabaseReference mDatabase;
    public SensorDataModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    public void readTemperature(final ValueEventListener listener) {
        mDatabase.child("T").addValueEventListener(listener);
    }

    public void readHumidity(final ValueEventListener listener) {
        mDatabase.child("H").addValueEventListener(listener);
    }

    public void readNitrogen(final ValueEventListener listener) {
        mDatabase.child("N").addValueEventListener(listener);
    }

    public void readPhosphorus(final ValueEventListener listener) {
        mDatabase.child("P").addValueEventListener(listener);
    }

    public void readPotassium(final ValueEventListener listener) {
        mDatabase.child("K").addValueEventListener(listener);
    }
    public void readWater(final ValueEventListener listener) {
        mDatabase.child("waterLevel").addValueEventListener(listener);
    }
    public void readPh(final ValueEventListener listener) {
        mDatabase.child("PH").addValueEventListener(listener);
    }


}
