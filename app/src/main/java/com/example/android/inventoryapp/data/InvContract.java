package com.example.android.inventoryapp.data;
import android.provider.BaseColumns;


public final class InvContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InvContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class InvEntry implements BaseColumns {

        /** Name of database table for pets */
        public final static String TABLE_NAME = "inventory";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME ="name";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_QUANTITY = "quantity";
        public final static String COLUMN_SUPPLIER_NAME = "supplier";
        public final static String COLUMN_SUPPLIER_NR = "suppliernr";

    }

}

