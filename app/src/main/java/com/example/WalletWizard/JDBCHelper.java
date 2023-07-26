package com.example.WalletWizard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class JDBCHelper extends SQLiteOpenHelper{

    static final String Database_Name = "UserInfo.db";
    static final int Database_Version = 1;

    static final String Database_Table = "Users";
//    static final String User_Id = "UserId";
    static final String User_name = "UserName";
    static final String User_password = "Password";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + Database_Table +" ("
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + User_name + " TEXT NOT NULL, " + User_password + " TEXT NOT NULL);";


    public JDBCHelper( Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ Database_Table);
        onCreate(db);
    }
}

