package com.example.WalletWizard;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.sql.SQLDataException;
import java.sql.SQLException;

public class DBManager {
    private JDBCHelper jdbcHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        jdbcHelper = new JDBCHelper(context);
        sqLiteDatabase = jdbcHelper.getWritableDatabase(); // Use getWritableDatabase() instead of getReadableDatabase()
        return this;
    }

    public void close() {
        jdbcHelper.close();
    }

    public void insert(String fullName, String username,String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JDBCHelper.Full_Name, fullName);
        contentValues.put(JDBCHelper.User_name, username);
        contentValues.put(JDBCHelper.Email, email);
        contentValues.put(JDBCHelper.User_password, password);
        sqLiteDatabase.insert(JDBCHelper.Database_Table, null, contentValues);
    }

    public Cursor query() {
        String[] columns = {JDBCHelper.User_name, JDBCHelper.User_password}; // Define the columns you want to retrieve
        return sqLiteDatabase.query(JDBCHelper.Database_Table, columns, null, null, null, null, null);
    }

    public Cursor query1(String username, String password) {
        String[] columns2 = {JDBCHelper.User_name, JDBCHelper.User_password};
        String choose = "UserName=? AND Password=?";
        String[] select = new String[]{username, password};
        return sqLiteDatabase.query(JDBCHelper.Database_Table, columns2, choose, select, null, null, null);
    }

    public Cursor query2(String username, String email) {
        String[] columns2 = {JDBCHelper.Email, JDBCHelper.User_name};
        String choose = "Email=? OR UserName=?";
        String[] select = new String[]{email, username};
        return sqLiteDatabase.query(JDBCHelper.Database_Table, columns2, choose, select, null, null, null);
    }



   public void dropDatabase() {
        context.deleteDatabase(JDBCHelper.Database_Name);
    }
}















