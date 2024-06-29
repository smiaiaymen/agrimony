package com.example.agrimony;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class SignUpController implements View.OnClickListener {
    private Activity activity;
    private EditText fullName;
    private EditText signUpEmail;
    private EditText signUpPassword;
    private EditText confirmPassword;
    private AuthenticationManager authManager;


    public SignUpController(Activity activity, EditText fullName, EditText signUpEmail, EditText signUpPassword, EditText confirmPassword) {
        this.activity = activity;
        this.fullName = fullName;
        this.signUpEmail = signUpEmail;
        this.signUpPassword = signUpPassword;
        this.confirmPassword = confirmPassword;
        this.authManager = new AuthenticationManager();
    }

    @Override
    public void onClick(View v) {
        String fName = fullName.getText().toString().trim();
        String email = signUpEmail.getText().toString().trim();
        String pass = signUpPassword.getText().toString().trim();
        String cPass = confirmPassword.getText().toString().trim();

        if (email.isEmpty()) {
            signUpEmail.setError("Email cannot be empty");
            return;
        }
        if (pass.isEmpty()) {
            signUpPassword.setError("Password cannot be empty");
            return;
        }
        if (!cPass.equals(pass)) {
            confirmPassword.setError("Passwords do not match");
            return;
        }

        authManager.signUpUser(activity, fName, email, pass);
    }
}
