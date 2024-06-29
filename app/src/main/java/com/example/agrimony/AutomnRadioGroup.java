package com.example.agrimony;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class AutomnRadioGroup extends AppCompatActivity {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automn_radio_group);

        radioGroup = findViewById(R.id.radio);

        // Reference to Firestore document
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("crops").document("automne");

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Map<String, Object> data = documentSnapshot.getData();

                    // Iterate through the fields and create a RadioButton for each field name
                    for (String fieldName : data.keySet()) {
                        RadioButton radioButton = new RadioButton(AutomnRadioGroup.this);
                        radioButton.setText(fieldName);

                        // Add the RadioButton to the RadioGroup
                        radioGroup.addView(radioButton);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Firestore", "Error getting document", e);
            }
        });

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                if (checkedId != -1) {
                    RadioButton checkedRadioButton = findViewById(checkedId);
                    String recommendation = checkedRadioButton.getText().toString();
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("recommendation", recommendation);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    // Handle case where no RadioButton is checked
                    Toast.makeText(AutomnRadioGroup.this, "Please select a recommendation", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
