package com.example.android.inventoryapp;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.inventoryapp.data.InvContract.InvEntry;
import com.example.android.inventoryapp.data.InvDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private InvDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new InvDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                InvEntry._ID,
                InvEntry.COLUMN_PRODUCT_NAME,
                InvEntry.COLUMN_PRICE,
                InvEntry.COLUMN_QUANTITY,
                InvEntry.COLUMN_SUPPLIER_NAME,
                InvEntry.COLUMN_SUPPLIER_NR };

        // Perform a query on the table
        Cursor cursor = db.query(
                InvEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_inv);

        try {

            displayView.setText("The inventory table contains " + cursor.getCount() + " products.\n\n");
            displayView.append(InvEntry._ID + " - " +
                    InvEntry.COLUMN_PRODUCT_NAME  + " - " +
                    InvEntry.COLUMN_PRICE  + " - " +
                    InvEntry.COLUMN_QUANTITY  + " - " +
                    InvEntry.COLUMN_SUPPLIER_NAME  + " - " +
                    InvEntry.COLUMN_SUPPLIER_NR + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(InvEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InvEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InvEntry.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InvEntry.COLUMN_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(InvEntry.COLUMN_SUPPLIER_NAME);
            int supplierNrColumnIndex = cursor.getColumnIndex(InvEntry.COLUMN_SUPPLIER_NR);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPrice = cursor.getString(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierNr = cursor.getString(supplierNrColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierNr));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertPet() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(InvEntry.COLUMN_PRODUCT_NAME, "ABND");
        values.put(InvEntry.COLUMN_PRICE, "1200");
        values.put(InvEntry.COLUMN_QUANTITY, "100");
        values.put(InvEntry.COLUMN_SUPPLIER_NAME, "Udacity");
        values.put(InvEntry.COLUMN_SUPPLIER_NR, "+123456");

        long newRowId = db.insert(InvEntry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
