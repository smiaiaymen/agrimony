package com.example.agrimony;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationManager {
    private FirebaseAuth auth;

    public AuthenticationManager() {
        auth = FirebaseAuth.getInstance();
    }

    public void loginUser(Activity activity, String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null && user.isEmailVerified()) {
                            Toast.makeText(activity, "Login successful", Toast.LENGTH_SHORT).show();
                            activity.startActivity(new Intent(activity, MainActivity.class));
                        } else {
                            Toast.makeText(activity, "Please verify your email", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void signUpUser(Activity activity, String fullName, String email, String password) {
        Log.d("TAG","onClick"+fullName.toString());
        Log.d("TAG","onClick"+ email.toString());
        Log.d("TAG1", "onClick"+password.toString());


        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {
                                user.sendEmailVerification()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(activity, "Verification email sent. Please verify your email", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(activity, "Failed to send verification email: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                            Toast.makeText(activity, "SignUp successful", Toast.LENGTH_SHORT).show();
                            activity.startActivity(new Intent(activity, LogIn.class));
                        } else {
                            Toast.makeText(activity, "SignUp failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
        });
    }

}
