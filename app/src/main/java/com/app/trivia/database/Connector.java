package com.app.trivia.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.app.trivia.model.Model;

import java.util.ArrayList;
import java.util.List;

public class Connector extends SQLiteOpenHelper
{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "trivia_db";

    // Table Name
    private static final String TABLE_NAME = "records";

    // Columns in the Table
    private static final String COLUMN_Name = "name";
    private static final String COLUMN_Cricketer = "cricketer";
    private static final String COLUMN_Colors = "colors";

    // Create table SQL query
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_Name + " TEXT PRIMARY KEY,"
                    + COLUMN_Cricketer + " TEXT,"
                    + COLUMN_Colors + " TEXT"
                    + ")";

    public Connector(
            @Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }


    // code to add the new contact
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addRecord(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Name, model.getName()); // Contact Name
        values.put(COLUMN_Cricketer, model.getCricketer()); // Contact Phone
        values.put(COLUMN_Colors, model.getColors()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_NAME, null, values);

        // Closing database connection
        db.close();
    }

    public List<Model> getAllNotes() {
        List<Model> records = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model record = new Model();
                record.setName(cursor.getString(cursor.getColumnIndex(COLUMN_Name)));
                record.setCricketer(cursor.getString(cursor.getColumnIndex(COLUMN_Cricketer)));
                record.setColors(cursor.getString(cursor.getColumnIndex(COLUMN_Colors)));

                records.add(record);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return records;
    }
}
