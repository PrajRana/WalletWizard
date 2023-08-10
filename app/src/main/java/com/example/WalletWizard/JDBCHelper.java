package com.example.WalletWizard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

public class JDBCHelper extends SQLiteOpenHelper{

    static final String Database_Name = "UserInfo.db";
    static final int Database_Version = 1;

    static final String Database_Table = "SignUP";

    static final String Database_Table2 = "Login";
    static final String Full_Name = "FullName";

    static final String Email = "Email";
    static final String User_name = "UserName";
    static final String User_password = "Password";

    static final String Total_Budget = "TotalBudget";

    static final String Remaining_balance = "RemainingBalance";

    static final String Housing_Expenses = "Housing";
    static final String Food_Expenses = "Food";
    static final String Healthcare_Expenses = "Healthcare";
    static final String Education_Expenses = "Education";
    static final String Insurance_Expenses = "Insurance";
    static final String Other_Expenses = "Other";



    private static final String CREATE_DB_QUERY = "CREATE TABLE " + Database_Table + " ("
            + Email + " TEXT NOT NULL UNIQUE, "
            + User_name + " TEXT NOT NULL UNIQUE, "
            + User_password + " TEXT NOT NULL, "
            + Full_Name + " TEXT NOT NULL, "
            + "PRIMARY KEY (" + User_name + "));";


    private static final String CREATE_DB_QUERY2 = "CREATE TABLE " + Database_Table2 + " ("
            + User_name + " TEXT PRIMARY KEY UNIQUE, "
            + Total_Budget + " DOUBLE, "
            + Remaining_balance + " DOUBLE, "
            + Housing_Expenses + " DOUBLE, "
            + Food_Expenses + " DOUBLE, "
            + Education_Expenses + " DOUBLE, "
            + Healthcare_Expenses + " DOUBLE, "
            + Insurance_Expenses + " DOUBLE, "
            + Other_Expenses + " DOUBLE, "
            + "FOREIGN KEY(" + User_name + ") REFERENCES " + Database_Table + "(" + User_name + "));";

    public JDBCHelper(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

   @Override
    public void onCreate(SQLiteDatabase db) {
       Log.d("JDBCHelper", "onCreate called");
        db.execSQL(CREATE_DB_QUERY);
        db.execSQL(CREATE_DB_QUERY2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS "+ Database_Table);
            db.execSQL("DROP TABLE IF EXISTS "+ Database_Table2);
            onCreate(db);
    }

}


