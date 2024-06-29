// MyAdapter.java
package com.example.agrimony;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<card> itemList;
    private Context context;
    private FirebaseFirestore db;

    public MyAdapter(ArrayList<card> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView , delete , edit ;
        public TextView textViewTitle;
        public TextView textViewDate;


        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewDate = view.findViewById(R.id.textViewDate);
            delete = view.findViewById(R.id.delete);
            edit = view.findViewById(R.id.modify);



        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        card item = itemList.get(position);
        Glide.with(holder.imageView.getContext())
                .load(item.getImageUri())
                .into(holder.imageView);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewDate.setText(item.getDate());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("recommended").document(item.getDocumentId()).delete().addOnSuccessListener(aVoid -> {
                    // Remove the item from the local list
                    itemList.remove(position);
                    // Notify the adapter of the change
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, itemList.size());
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(context, "Error deleting item", Toast.LENGTH_SHORT).show();
                });

            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDescriptionDialog(item);

            }
        });

    }
    private void showDescriptionDialog(card item) {
        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_show_description, null);
        builder.setView(dialogView);

        // Get the map of descriptions
        Map<String, String> descriptionMap = item.getDescription();

        // Get the TextViews from the dialog layout
        TextView textViewDescriptionValue1 = dialogView.findViewById(R.id.N);
        TextView textViewDescriptionValue2 = dialogView.findViewById(R.id.P);
        TextView textViewDescriptionValue3 = dialogView.findViewById(R.id.K);
        TextView textViewDescriptionValue4 = dialogView.findViewById(R.id.T);
        TextView textViewDescriptionValue5 = dialogView.findViewById(R.id.Date);

        // Set the description values to the TextViews
        textViewDescriptionValue1.setText(descriptionMap.get("N"));
        textViewDescriptionValue2.setText(descriptionMap.get("P"));
        textViewDescriptionValue3.setText(descriptionMap.get("K"));
        textViewDescriptionValue4.setText(descriptionMap.get("T"));
        textViewDescriptionValue5.setText(descriptionMap.get("date"));

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
