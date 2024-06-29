// AutomneController.java
package com.example.agrimony;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class AutomneController {
    private Activity activity;
    private CultureModel model;
    private TextView recommendationTextView;
    private Button confirmButton, otherButton;

    private static final int REQUEST_RADIO_GROUP = 1;

    public AutomneController(Activity activity) {
        this.activity = activity;
        this.model = new CultureModel();

        confirmButton = activity.findViewById(R.id.confirm);
        otherButton = activity.findViewById(R.id.other);
        recommendationTextView = activity.findViewById(R.id.recommendation);

        PredictionHelper.makePredictionRequest(activity, recommendationTextView);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onConfirmButtonClicked();
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOtherButtonClicked();
            }
        });
    }

    private void onConfirmButtonClicked() {
        final String recommendedCulture = recommendationTextView.getText().toString();

        model.fetchRecommendedCulture("automne", recommendedCulture, new CultureModel.OnCultureDataListener() {
            @Override
            public void onCultureDataFetched(Map<String, Object> cultureData) {
                // Append timestamp to description field


                model.saveRecommendedCulture(recommendedCulture, cultureData, new CultureModel.OnSaveCultureListener() {
                    @Override
                    public void onSaveSuccess() {
                        Intent cropCycleIntent = new Intent(activity, CropsCycle.class);
                        cropCycleIntent.putExtra("RECOMMENDED_CULTURE", recommendedCulture);
                        activity.startActivity(cropCycleIntent);
                    }

                    @Override
                    public void onSaveFailure(Exception e) {
                        // Handle the error
                    }
                });
            }

            @Override
            public void onCultureDataFetchError(Exception e) {
                // Handle the error
            }
        });
    }

    private void onOtherButtonClicked() {
        Intent dialogIntent = new Intent(activity, AutomnRadioGroup.class);
        activity.startActivityForResult(dialogIntent, REQUEST_RADIO_GROUP);
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RADIO_GROUP && resultCode == Activity.RESULT_OK && data != null) {
            String selectedValue = data.getStringExtra("recommendation");
            recommendationTextView.setText(selectedValue);
        }
    }
}
