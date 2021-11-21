package com.example.whatsizeee;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ENTRY";
    public static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_SQL = String.format("CREATE TABLE %s IF NOT EXISTS (%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)", SizeEntryContract.TABLE_NAME, SizeEntryContract.IMAGE_COL, SizeEntryContract.NAME_COL, SizeEntryContract.BOTTOM_COL, SizeEntryContract.RING_COL, SizeEntryContract.SHOE_COL, SizeEntryContract.TOP_COL);
    private static final String DROP_TABLE_SQL = String.format("DROP TABLE %s", DATABASE_NAME);

    //initialize db
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE_SQL);
        onCreate(db);
    }

    public void addEntry(SizeEntry entry){
        SQLiteDatabase dbWriter = super.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SizeEntryContract.ID_COL, entry.getId());
        values.put(SizeEntryContract.NAME_COL, entry.getName());
        values.put(SizeEntryContract.IMAGE_COL, entry.getImageURIString());
        values.put(SizeEntryContract.TOP_COL, entry.getTopSize());
        values.put(SizeEntryContract.BOTTOM_COL, entry.getBottomSize());
        values.put(SizeEntryContract.SHOE_COL, entry.getShoeSize());
        values.put(SizeEntryContract.RING_COL, entry.getRingSize());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = dbWriter.insert(SizeEntryContract.TABLE_NAME, null, values);
    }

    public String[] getEntries(){
        SQLiteDatabase dbReader = super.getReadableDatabase();

        // Define a projection that specifies which columns from the database you will actually use after this query.
        String[] entries = {
                BaseColumns._ID,
                SizeEntryContract.ID_COL,
                SizeEntryContract.NAME_COL,
                SizeEntryContract.IMAGE_COL,
                SizeEntryContract.TOP_COL,
                SizeEntryContract.BOTTOM_COL,
                SizeEntryContract.SHOE_COL,
                SizeEntryContract.RING_COL
        };

//        String sortOrder = BaseColumns._ID + " ASC";

        Cursor cursor = dbReader.query(SizeEntryContract.TABLE_NAME, entries, null, null, null, null, null);

        return entries;
    }

}
