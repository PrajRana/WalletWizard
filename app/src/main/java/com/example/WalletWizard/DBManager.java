package com.example.WalletWizard;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public void insert(String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JDBCHelper.User_name, username); // Use JDBCHelper.User_name instead of jdbcHelper.User_name
        contentValues.put(JDBCHelper.User_password, password); // Use JDBCHelper.User_password instead of jdbcHelper.User_password
        sqLiteDatabase.insert(JDBCHelper.Database_Table, null, contentValues); // Use JDBCHelper.Database_Table instead of jdbcHelper.Database_Table
    }

    public Cursor query() {
        String[] columns = {JDBCHelper.User_name, JDBCHelper.User_password}; // Define the columns you want to retrieve
        return sqLiteDatabase.query(JDBCHelper.Database_Table, columns, null, null, null, null, null);
    }

//   public void dropDatabase() {
//        context.deleteDatabase(JDBCHelper.Database_Name);
//    }
}















