package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.data.InvContract.InvEntry;


public class InvDbHelper extends SQLiteOpenHelper {


    /** Name of the database file */
    private static final String DATABASE_NAME = "inventory.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link InvDbHelper}.
     *
     * @param context of the app
     */
    public InvDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the table
        String SQL_CREATE_INVENTORY_TABLE =  "CREATE TABLE " + InvEntry.TABLE_NAME + " ("
                + InvEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InvEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + InvEntry.COLUMN_PRICE + " INTEGER NOT NULL, "
                + InvEntry.COLUMN_QUANTITY + " INTEGER, "
                + InvEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + InvEntry.COLUMN_SUPPLIER_NR + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}