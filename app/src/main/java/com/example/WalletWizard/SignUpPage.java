package com.example.WalletWizard;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpPage extends AppCompatActivity {
    DBManager dbManager;

    TextView Full_Name;
    TextView Email;
    TextView User_name;
    TextView User_password;
    TextView confirm_password;
    Button  register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Full_Name = (TextView) findViewById(R.id.fullname) ;
        Email = (TextView) findViewById(R.id.email);
        User_name = (TextView) findViewById(R.id.username);
        User_password = (TextView) findViewById(R.id.password);
        confirm_password = (TextView) findViewById(R.id.confirmpassword);


        register = (Button) findViewById(R.id.signupbutton);

        dbManager = new DBManager(this);

        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void RegisterButtonPressed(View v){

        if(!confirm_password.getText().toString().equals(User_password.getText().toString())){
            Toast.makeText(this, "Passwords do not match. Please re-enter.", Toast.LENGTH_SHORT).show();
        }
        else{
            dbManager.insert(Full_Name.getText().toString(),User_name.getText().toString(),
                    Email.getText().toString(),User_password.getText().toString());
            Toast.makeText(this, "Succesfully entered into database", Toast.LENGTH_SHORT).show();

            //OpenNewPage();

        }





    }


}



