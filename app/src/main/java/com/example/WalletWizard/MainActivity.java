package com.example.WalletWizard;

import androidx.appcompat.app.AppCompatActivity;


import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.content.Intent;




public class MainActivity extends AppCompatActivity {

    DBManager dbManager;
    TextView username;
    TextView password;
    TextView signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        signup = (TextView) findViewById(R.id.newsignup);

        Button login = (Button) findViewById(R.id.signinbutton);

        dbManager = new DBManager(this);

        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  public void LoginButtonPressed(View v){
        Cursor cursor = dbManager.query1(username.getText().toString(),password.getText().toString());
        if (cursor == null || cursor.getCount() == 0) {
          Toast.makeText(this, "username or password doesnot exist. Please enter correct credentials", Toast.LENGTH_SHORT).show();
        } else {
            OpenNewPage();
      }
    }

    public  void NewSignUp(View v){
        Intent intent = new Intent(this,SignUpPage.class);
        startActivity(intent);
    }

    public void ForgotPassword(View v){
        Intent intent = new Intent(this,ResetPassword.class);
        startActivity(intent);
    }

    public void DropDatabaseButtonPressed(View v) {
        dbManager.dropDatabase();
        Toast.makeText(this, "Database Dropped!", Toast.LENGTH_SHORT).show();
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


















