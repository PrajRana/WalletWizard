package com.example.WalletWizard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPassword extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
    }

    public void SendEmail(View v){
        EditText UserEmail = findViewById(R.id.resetpasswordemail);
        String userEnteredEmail = UserEmail.getText().toString().trim();
        Toast.makeText(this, "User entered email: " + userEnteredEmail, Toast.LENGTH_SHORT).show();
    }
}

