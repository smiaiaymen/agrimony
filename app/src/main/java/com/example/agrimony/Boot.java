package com.example.agrimony;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.cardview.widget.CardView;

import com.example.agrimony.databinding.ActivityBootBinding;


public class Boot extends BaseActivity {

    ActivityBootBinding activityBootBinding;
    CardView cardEte;
    CardView cardAutomne;
    CardView cardHiver;
    CardView cardPrintemps;
    private static final String TAG = "BootActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBootBinding = ActivityBootBinding.inflate(getLayoutInflater());
        setContentView(activityBootBinding.getRoot());
        setTitle("Assistance");

        // Initialize card views
        cardAutomne = findViewById(R.id.automne);
        cardEte = findViewById(R.id.ete);
        cardHiver = findViewById(R.id.hiver);
        cardPrintemps = findViewById(R.id.printemps);

        cardPrintemps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent printemps = new Intent(Boot.this,Printemps.class);
                startActivity(printemps);
            }


        });

        cardAutomne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent automne = new Intent(Boot.this, Automne.class);
                startActivity(automne);
            }
        });
        cardHiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hiver = new Intent(Boot.this, Hiver.class);
                startActivity(hiver);
            }
        });
        cardEte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ete = new Intent(Boot.this, Ete.class);
                startActivity(ete);
            }
        });
    }
}
