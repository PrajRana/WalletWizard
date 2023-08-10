package com.example.WalletWizard;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;


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

    public void updatetable2(Context context, String username, Double budget, Double housing, Double food,
                             Double education, Double healthcare, Double insurance, Double other){
        ContentValues contentValues = new ContentValues();
        String[] select = {username};
        String where = JDBCHelper.User_name + " = ?";
        contentValues.put(JDBCHelper.Total_Budget, budget);
        contentValues.put(JDBCHelper.Housing_Expenses, housing);
        contentValues.put(JDBCHelper.Food_Expenses, food);
        contentValues.put(JDBCHelper.Education_Expenses, education);
        contentValues.put(JDBCHelper.Healthcare_Expenses, healthcare);
        contentValues.put(JDBCHelper.Insurance_Expenses, insurance);
        contentValues.put(JDBCHelper.Other_Expenses, other);

        Double remaining_balance = budget - (housing + food + education + healthcare + insurance + other);
        if (remaining_balance < 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Error");
            alert.setMessage("Cannot have negative remaining balance");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    
                }
            });
            alert.show();
            return; // Don't proceed with updating the balance
        }
            contentValues.put(JDBCHelper.Remaining_balance,remaining_balance);
            sqLiteDatabase.update(JDBCHelper.Database_Table2, contentValues,where, select);
    }


    public Double getRemainingBalance(String username){
        String[] columns = {JDBCHelper.Remaining_balance};
        String select = JDBCHelper.User_name + " = ?";
        String[] args = {username};

        Cursor cursor = sqLiteDatabase.query(
                JDBCHelper.Database_Table2,columns,select,args,null,null, null
        );
        Double remainingBalance = null;

        if (cursor != null && cursor.moveToFirst()) {
            int col = cursor.getColumnIndex(JDBCHelper.Remaining_balance);
            remainingBalance = cursor.getDouble(col);
            cursor.close();
        }
        return remainingBalance;
    }

   public void dropDatabase() {
        context.deleteDatabase(JDBCHelper.Database_Name);
    }
}















