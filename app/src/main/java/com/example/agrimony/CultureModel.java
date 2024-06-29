package com.example.agrimony;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class CultureModel {
    private FirebaseFirestore db;

    public CultureModel() {
        db = FirebaseFirestore.getInstance();
    }

    public interface OnCultureDataListener {
        void onCultureDataFetched(Map<String, Object> cultureData);
        void onCultureDataFetchError(Exception e);
    }

    public interface OnSaveCultureListener {
        void onSaveSuccess();
        void onSaveFailure(Exception e);
    }

    public void fetchRecommendedCulture(String documentPath, String culture, OnCultureDataListener listener) {
        DocumentReference docRef = db.collection("crops").document(documentPath);

        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                Map<String, Object> cultureData = (Map<String, Object>) documentSnapshot.get(culture);
                if (cultureData != null) {
                    listener.onCultureDataFetched(cultureData);
                } else {
                    listener.onCultureDataFetchError(new Exception("Culture data not found"));
                }
            } else {
                listener.onCultureDataFetchError(new Exception("Document does not exist"));
            }
        }).addOnFailureListener(listener::onCultureDataFetchError);
    }

    public void saveRecommendedCulture(String culture, Map<String, Object> cultureData, OnSaveCultureListener listener) {
        // Append timestamp to description field
        long clickTimestamp = System.currentTimeMillis(); // Get current timestamp
        String formattedTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(clickTimestamp));
        cultureData.put("date", formattedTimestamp);

        db.collection("recommended").document(culture).set(cultureData)
                .addOnSuccessListener(aVoid -> listener.onSaveSuccess())
                .addOnFailureListener(listener::onSaveFailure);
    }
}
