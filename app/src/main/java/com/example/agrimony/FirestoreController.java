package com.example.agrimony;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FirestoreController {
    private FirebaseFirestore db;

    public FirestoreController() {
        db = FirebaseFirestore.getInstance();
    }

    public void fetchRecommendedData(final FirestoreCallback callback) {
        db.collection("recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<card> cardList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String title = document.getString("title");
                                Map<String, String> description = (Map<String, String>) document.get("description");
                                String imageUri = document.getString("imageUri");
                                String documentId = document.getId();
                                String date = document.getString("date");

                                card cardItem = new card(title, description, imageUri, documentId, date);
                                cardList.add(cardItem);
                            }
                            callback.onSuccess(cardList);
                        } else {
                            callback.onFailure(task.getException());
                        }
                    }
                });
    }

    public interface FirestoreCallback {
        void onSuccess(ArrayList<card> cardList);
        void onFailure(Exception e);
    }
}
