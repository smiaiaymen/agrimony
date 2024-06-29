package com.example.agrimony;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PredictionHelper {
    private static final String TAG = "PredictionHelper";
    private static final String URL = "https://crop-prediction-model-1.onrender.com/predict";

    public static void makePredictionRequest(Context context, TextView predicted) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double tValue = dataSnapshot.child("T").getValue(Double.class);
                double hValue = dataSnapshot.child("H").getValue(Double.class);
                double phValue = dataSnapshot.child("PH").getValue(Double.class);
                double nValue = dataSnapshot.child("N").getValue(Double.class);
                double pValue = dataSnapshot.child("P").getValue(Double.class);
                double kValue = dataSnapshot.child("K").getValue(Double.class);

                makePredictionRequest(context, tValue, hValue, phValue, nValue, pValue, kValue, predicted);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to read data from Firebase.", databaseError.toException());
            }
        });
    }

    private static void makePredictionRequest(Context context, double tValue, double hValue, double phValue, double nValue,
                                              double pValue, double kValue, TextView predicted) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Construct the prediction parameters
        Map<String, String> params = new HashMap<>();
        params.put("T", String.valueOf(tValue));
        params.put("H", String.valueOf(hValue));
        params.put("PH", String.valueOf(phValue));
        params.put("N", String.valueOf(nValue));
        params.put("P", String.valueOf(pValue));
        params.put("K", String.valueOf(kValue));

        // Make a prediction request to the Flask app
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String data = jsonObject.getString("culture");
                            displayPredictedCulture(predicted, data);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }

    private static void displayPredictedCulture(TextView predicted, String predictedCulture) {
        switch (predictedCulture) {
            case "0":
                predicted.setText("Ail");
                break;
            case "1":
                predicted.setText("Carotte");
                break;
            case "2":
                predicted.setText("Feve");
                break;
            case "3":
                predicted.setText("Haricot");
                break;
            case "4":
                predicted.setText("MELON");
                break;
            case "5":
                predicted.setText("Mais");
                break;
            case "6":
                predicted.setText("Oignon");
                break;
            case "7":
                predicted.setText("PASTEQUE");
                break;
            case "8":
                predicted.setText("Petit pois");
                break;
            case "9":
                predicted.setText("Pois chiche");
                break;
            case "10":
                predicted.setText("Poivron");
                break;
            case "11":
                predicted.setText("Squash");
                break;
            case "12":
                predicted.setText("Tomate");
                break;
            default:
                Log.e(TAG, "Unknown predicted culture: " + predictedCulture);
        }
    }
}
