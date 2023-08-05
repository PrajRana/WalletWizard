package com.example.WalletWizard;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ManageData extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<AddCategory> categoryList = new ArrayList<AddCategory>();

        Toast.makeText(this, "Inside managedata class", Toast.LENGTH_SHORT).show();



    }




}
