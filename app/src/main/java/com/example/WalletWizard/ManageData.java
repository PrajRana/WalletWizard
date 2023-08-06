package com.example.WalletWizard;

import android.database.Cursor;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ManageData extends AppCompatActivity {

    DBManager dbManager;
    TextView budget;
    Button savebutton ;
    String Username;

    private BigDecimal bigDecimal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

       // List<AddCategory> categoryList = new ArrayList<AddCategory>();
        Username = getIntent().getStringExtra("username");
        budget = (TextView)findViewById(R.id.budgetnumber);
        //TextView remaining = (TextView)findViewById(R.id.viewremainingbalance)
        savebutton = (Button) findViewById(R.id.savebutton);

        Toast.makeText(this, "Inside managedata class, " + Username, Toast.LENGTH_SHORT).show();

        dbManager = new DBManager(this);

        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Double remainingbalance = dbManager.getRemainingBalance(Username);
        TextView dataTextView = findViewById(R.id.viewremainingbalance);

        if(remainingbalance== null){
            dataTextView.setText("No balance yet");
        }
        else{
            dataTextView.setText(String.valueOf(remainingbalance));
        }
    }
    public void SaveButtonPressed(View v){
        String bud = budget.getText().toString();
        Double budget = Double.parseDouble(bud);

        dbManager.updatetable2(Username, budget);
        Double remainingbalance = dbManager.getRemainingBalance(Username);

        TextView dataTextView = findViewById(R.id.viewremainingbalance);

        if(remainingbalance== null){
            dataTextView.setText("No balance yet");
        }
        else{
            dataTextView.setText(String.valueOf(remainingbalance));
        }
    }

}
