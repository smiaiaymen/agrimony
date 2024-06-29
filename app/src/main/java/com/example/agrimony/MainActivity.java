package com.example.agrimony;
import android.os.Bundle;
import android.view.Gravity;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;


import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.agrimony.databinding.ActivityMainBinding;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ProgressBar temperatureProgressBar, humidityProgressBar, nitrogenProgressBar, phosphorusProgressBar, potassiumProgressBar,waterLevelProgressBar;
    private TextView temperatureTextView, humidityTextView, nitrogenTextView, phosphorusTextView, potassiumTextView,waterlevelTextview;
    private MainActivityController controller;
    private CircleProgress waterLevelProgress;
    private ArcProgress phArc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        setTitle("Dashboard");

        // Initialize Views
        phArc = findViewById(R.id.phARC);
        waterLevelProgress = findViewById(R.id.watercpb);
        temperatureProgressBar = findViewById(R.id.temperature);
        temperatureTextView = findViewById(R.id.temp);
        humidityProgressBar = findViewById(R.id.humidity);
        humidityTextView = findViewById(R.id.hum);
        nitrogenProgressBar = findViewById(R.id.nitrogen);
        nitrogenTextView = findViewById(R.id.N);
        phosphorusProgressBar = findViewById(R.id.phosphorus);
        phosphorusTextView = findViewById(R.id.P);
        potassiumProgressBar = findViewById(R.id.potassium);
        potassiumTextView = findViewById(R.id.K);



        // Initialize Controller
        controller = new MainActivityController(this);

        // Setup Chart


        // Start listening for sensor data
        controller.startSensorDataListener();
    }





    public void updateTemperature(int temperatureValue) {
        temperatureProgressBar.setProgress(temperatureValue);
        temperatureTextView.setText(temperatureValue + "°C");

        // Save temperature change to history
    }
    public void updateHumidity(int humidityValue) {
        humidityProgressBar.setProgress(humidityValue);
        humidityTextView.setText(humidityValue + "%");
    }
    public void updateNitrogen(int nitrogenValue) {
        nitrogenProgressBar.setProgress(nitrogenValue);
        nitrogenTextView.setText(nitrogenValue + " ppm");
    }
    public void updatePhosphorus(int phosphorusValue) {
        phosphorusProgressBar.setProgress(phosphorusValue);
        phosphorusTextView.setText(phosphorusValue + " ppm");
    }
    public void updatePotassium(int potassiumValue) {
        potassiumProgressBar.setProgress(potassiumValue);
        potassiumTextView.setText(potassiumValue + " ppm");
    }
    public void updateWaterLevel(int waterLevelValue) {
        waterLevelProgress.setProgress(waterLevelValue);

    }
    public void updatePhLevel(int phValue) {
        phArc.setProgress(phValue);

    }


    // Implement other update methods for humidity, nitrogen, phosphorus, and potassium

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
