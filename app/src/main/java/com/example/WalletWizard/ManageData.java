package com.example.WalletWizard;

import android.database.Cursor;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ManageData extends AppCompatActivity {

    DBManager dbManager;
    EditText budget;
    EditText housing;
    EditText food;
    EditText education;
    EditText healthcare;
    EditText insurance;
    EditText other;
    Button savebutton ;
    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

        Username = getIntent().getStringExtra("username");
        budget = findViewById(R.id.budgetnumber);
        housing = findViewById(R.id.housingexpense);
        food = findViewById(R.id.foodexpense);
        education = findViewById(R.id.educationexpense);
        healthcare = findViewById(R.id.healthcareexpense);
        insurance = findViewById(R.id.insuranceexpense);
        other = findViewById(R.id.otherexpense);

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
        String hou = housing.getText().toString();
        String foo = food.getText().toString();
        String edu = education.getText().toString();
        String heal = healthcare.getText().toString();
        String ins = insurance.getText().toString();
        String oth = other.getText().toString();

        if(bud.isEmpty() || hou.isEmpty() || foo.isEmpty() || edu.isEmpty() || heal.isEmpty() || ins.isEmpty() || oth.isEmpty()){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Double budget = Double.parseDouble(bud);
        Double housing = Double.parseDouble(hou);
        Double food = Double.parseDouble(foo);
        Double education = Double.parseDouble(edu);
        Double healthcare = Double.parseDouble(heal);
        Double insurance = Double.parseDouble(ins);
        Double other = Double.parseDouble(oth);

        dbManager.updatetable2(this,Username, budget, housing, food, education, healthcare, insurance, other);

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
