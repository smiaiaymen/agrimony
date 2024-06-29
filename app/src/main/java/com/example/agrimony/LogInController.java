package com.example.agrimony;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

public class LogInController implements View.OnClickListener {
    private Activity activity;
    private EditText loginEmail;
    private EditText loginPass;
    private AuthenticationManager authManager;

    public LogInController(LogIn activity, EditText loginEmail, EditText loginPass) {
        this.activity = activity;
        this.loginEmail = loginEmail;
        this.loginPass = loginPass;
        authManager = new AuthenticationManager();
    }

    @Override
    public void onClick(View v) {
        String email = loginEmail.getText().toString();
        String pass = loginPass.getText().toString();
        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!TextUtils.isEmpty(pass)) {
                authManager.loginUser(activity, email, pass);
            } else {
                loginPass.setError("Password cannot be empty");
            }
        } else if (TextUtils.isEmpty(email)) {
            loginEmail.setError("Email cannot be empty");
        } else {
            loginEmail.setError("Please enter a valid email");
        }
    }
}