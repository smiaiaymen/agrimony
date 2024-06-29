package com.example.agrimony;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivityController {
    private MainActivity view;
    private SensorDataModel model;

    public MainActivityController(MainActivity view) {
        this.view = view;
        this.model = new SensorDataModel();
    }

    public void startSensorDataListener() {
        // Read temperature data from Firebase
        DatabaseReference temperatureRef = FirebaseDatabase.getInstance().getReference().child("T");
        temperatureRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer temperatureValue = dataSnapshot.getValue(Integer.class);
                if (temperatureValue != null) {
                    view.updateTemperature(temperatureValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

        // Read humidity data from Firebase
        DatabaseReference humidityRef = FirebaseDatabase.getInstance().getReference().child("H");
        humidityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer humidityValue = dataSnapshot.getValue(Integer.class);
                if (humidityValue != null) {
                    view.updateHumidity(humidityValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

        // Read nitrogen data from Firebase
        DatabaseReference nitrogenRef = FirebaseDatabase.getInstance().getReference().child("N");
        nitrogenRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer nitrogenValue = dataSnapshot.getValue(Integer.class);
                if (nitrogenValue != null) {
                    view.updateNitrogen(nitrogenValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

        // Read phosphorus data from Firebase
        DatabaseReference phosphorusRef = FirebaseDatabase.getInstance().getReference().child("P");
        phosphorusRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer phosphorusValue = dataSnapshot.getValue(Integer.class);
                if (phosphorusValue != null) {
                    view.updatePhosphorus(phosphorusValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

        // Read potassium data from Firebase
        DatabaseReference potassiumRef = FirebaseDatabase.getInstance().getReference().child("K");
        potassiumRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer potassiumValue = dataSnapshot.getValue(Integer.class);
                if (potassiumValue != null) {
                    view.updatePotassium(potassiumValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
        DatabaseReference waterLevelRef = FirebaseDatabase.getInstance().getReference().child("waterLevel");
        waterLevelRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer waterLevelValue = dataSnapshot.getValue(Integer.class);
                if (waterLevelValue != null) {
                    view.updateWaterLevel(waterLevelValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
        DatabaseReference phRef = FirebaseDatabase.getInstance().getReference().child("PH");
        phRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer phValue = dataSnapshot.getValue(Integer.class);
                if (phValue != null) {
                    view.updatePhLevel(phValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });


    }


}