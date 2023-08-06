package com.example.WalletWizard;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.math.BigDecimal;
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

    public void insertSecond(String username){
        ContentValues contentValues = new ContentValues();
        contentValues.put(JDBCHelper.User_name,username);
        sqLiteDatabase.insert(JDBCHelper.Database_Table2, null, contentValues);
    }

    public Cursor query() {
        String[] columns = {JDBCHelper.User_name, JDBCHelper.User_password}; // Define the columns you want to retrieve
        return sqLiteDatabase.query(JDBCHelper.Database_Table, columns, null, null, null, null, null);
    }

    //checks if both username and password is valid during login
    public Cursor query1(String username, String password) {
        String[] columns2 = {JDBCHelper.User_name, JDBCHelper.User_password};
        String choose = "UserName=? AND Password=?";
        String[] select = new String[]{username, password};
        return sqLiteDatabase.query(JDBCHelper.Database_Table, columns2, choose, select, null, null, null);
    }

    //checks if username or email already exists in db
    public Cursor query2(String username, String email) {
        String[] columns2 = {JDBCHelper.Email, JDBCHelper.User_name};
        String choose = "Email=? OR UserName=?";
        String[] select = new String[]{email, username};
        return sqLiteDatabase.query(JDBCHelper.Database_Table, columns2, choose, select, null, null, null);
    }

    public void updatetable2(String username, Double budget){
        ContentValues contentValues = new ContentValues();
        String[] select = {username};
        String where = JDBCHelper.User_name + " = ?";
        contentValues.put(JDBCHelper.Total_Budget, budget);
        //substract expenditure here
        contentValues.put(JDBCHelper.Remaining_balance,budget);
        sqLiteDatabase.update(JDBCHelper.Database_Table2, contentValues,where, select);
    }

    public Double getRemainingBalance(String username){
        String[] columns = {JDBCHelper.Remaining_balance};
        String selection = JDBCHelper.User_name + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = sqLiteDatabase.query(
                JDBCHelper.Database_Table2,     // Table name
                columns,                        // Columns to retrieve
                selection,                      // WHERE clause
                selectionArgs,                  // Values for WHERE clause
                null,                           // GROUP BY
                null,                           // HAVING
                null                            // ORDER BY
        );
        Double remainingBalance = null;

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(JDBCHelper.Remaining_balance);
            remainingBalance = cursor.getDouble(columnIndex);
            cursor.close();
        }

        return remainingBalance;
    }



   public void dropDatabase() {
        context.deleteDatabase(JDBCHelper.Database_Name);
    }
}















