package com.example.agrimony;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrimony.databinding.ActivityCropsCycleBinding;

import java.util.ArrayList;

public class CropsCycle extends BaseActivity {
    private ActivityCropsCycleBinding activityCropsCycleBinding;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<card> cardList;
    private FirestoreController firestoreController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCropsCycleBinding = ActivityCropsCycleBinding.inflate(getLayoutInflater());
        setContentView(activityCropsCycleBinding.getRoot());
        setTitle("Crop Cycle");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cardList = new ArrayList<>();
        adapter = new MyAdapter(cardList, this);
        recyclerView.setAdapter(adapter);

        firestoreController = new FirestoreController();

        // Fetch data from Firestore
        fetchRecommendedData();

        // Register context menu for RecyclerView items
        registerForContextMenu(recyclerView);
    }

    private void fetchRecommendedData() {
        firestoreController.fetchRecommendedData(new FirestoreController.FirestoreCallback() {
            @Override
            public void onSuccess(ArrayList<card> cardList) {
                CropsCycle.this.cardList.addAll(cardList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                // Log or handle the error
            }
        });
    }
}
