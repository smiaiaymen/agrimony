package com.example.agrimony;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LogIn extends AppCompatActivity {
    private EditText loginEmail;
    private EditText loginPass;
    private Button loginButton;
    private TextView signUpRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loginEmail = findViewById(R.id.login_mail);
        loginPass = findViewById(R.id.login_pass);
        loginButton = findViewById(R.id.login_btn);
        signUpRedirect = findViewById(R.id.signUpRedirect);

        loginButton.setOnClickListener(new LogInController(this, loginEmail, loginPass));
        signUpRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, SignUp.class));
            }
        });
    }
}