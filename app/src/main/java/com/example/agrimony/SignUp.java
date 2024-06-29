package com.example.agrimony;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {
    private EditText fullName;
    private EditText signUpEmail;
    private EditText signUpPassword;
    private EditText confirmPassword;
    private Button signUp;
    private TextView loginRedirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName = findViewById(R.id.fullname);
        signUpEmail = findViewById(R.id.signup_mail);
        signUpPassword = findViewById(R.id.signup_pass);
        confirmPassword = findViewById(R.id.signup_confpass);
        signUp = findViewById(R.id.signup_btn);
        loginRedirection = findViewById(R.id.loginRedirect);

        signUp.setOnClickListener(new SignUpController(this, fullName, signUpEmail, signUpPassword, confirmPassword));
        loginRedirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, LogIn.class));
            }
        });
    }
}
