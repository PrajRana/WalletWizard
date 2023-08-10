package com.example.WalletWizard;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpPage extends AppCompatActivity {
    DBManager dbManager;

    EditText fullname;
    EditText email;
    EditText username;
    EditText userpassword;
    EditText confirmpassword;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        userpassword = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        register = (Button) findViewById(R.id.signupbutton);

        dbManager = new DBManager(this);

        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RegisterButtonPressed(View v) {
        if (!confirmpassword.getText().toString().equals(userpassword.getText().toString())) {
            Toast.makeText(this, "Passwords do not match. Please re-enter.", Toast.LENGTH_SHORT).show();
        } else {
            Cursor cursor = dbManager.query2(username.getText().toString(), email.getText().toString());
            if (cursor != null && cursor.getCount() > 0) {
                Toast.makeText(this, "user already exists", Toast.LENGTH_SHORT).show();
            } else {
                dbManager.insert(fullname.getText().toString(), username.getText().toString(),
                        email.getText().toString(), userpassword.getText().toString());
                dbManager.insertSecond(username.getText().toString());
               // Toast.makeText(this, "Succesfully entered into database", Toast.LENGTH_SHORT).show();
                OpenNewPage();
            }
        }
    }
//    public void ViewButtonPressed(View v) {
//        Cursor cursor = dbManager.query();
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    try {
//                            String username = cursor.getString(cursor.getColumnIndexOrThrow(JDBCHelper.User_name));
//                            String password = cursor.getString(cursor.getColumnIndexOrThrow(JDBCHelper.User_password));
//                            Log.i("DATABASE_TAG","I have username: "+ username + " password : "+ password);
//                    } catch (IllegalArgumentException e) {
//                        e.printStackTrace();
//                            // Handle the exception here if the column does not exist
//                    }
//                } while (cursor.moveToNext());
//            }
//            cursor.close(); // Remember to close the cursor when you're done with it
//        }
//    }

    public void OpenNewPage(){
        Intent intent = new Intent(this,ManageData.class);
        intent.putExtra("username",username.getText().toString());
        startActivity(intent);
    }

}



