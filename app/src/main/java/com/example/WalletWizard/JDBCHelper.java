package com.example.WalletWizard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class JDBCHelper extends SQLiteOpenHelper{

    static final String Database_Name = "UserInfo.db";
    static final int Database_Version = 1;

    static final String Database_Table = "SignUP";

    static final String Database_Table2 = "Login";
    static final String Full_Name = "FullName";

    static final String Email = "Email";
    static final String User_name = "UserName";
    static final String User_password = "Password";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + Database_Table + " ("
            + Email + " TEXT NOT NULL UNIQUE, "
            + User_name + " TEXT NOT NULL UNIQUE, "
            + User_password + " TEXT NOT NULL, "
            + Full_Name + " TEXT NOT NULL, "
            + "PRIMARY KEY (" + User_name + "));";


    private static final String CREATE_DB_QUERY2 = "CREATE TABLE " + Database_Table2 + " ("
            + User_name + " TEXT PRIMARY KEY UNIQUE, "
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


