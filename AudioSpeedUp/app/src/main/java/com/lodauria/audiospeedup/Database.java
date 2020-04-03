package com.lodauria.audiospeedup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "mDatabase";
    private static final String COL1 = "id";
    private static final String COL2 = "field";
    private static final String COL3 = "record";

    public Database(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2
                + " TEXT COLLATE NOCASE," + COL3 + " TEXT COLLATE NOCASE)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String arrivo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db1 = this.getReadableDatabase();

        Cursor cursor = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL2 + "='" + item + "' AND " + COL3 + "='" + arrivo
                + "'";
        cursor = db1.rawQuery(sql, null);

        if (cursor.getCount() > 0) {

        } else {
            contentValues.put(COL2, item);
            contentValues.put(COL3, arrivo);
        }

        cursor.close();

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    /**
     * Returns all the data from database
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the field passed in
     */
    public Cursor getItemID(String field) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + field + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the field field
     */
    public void updateName(String newField, int id, String oldField) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newField + "' WHERE " + COL1 + " = '" + id
                + "'" + " AND " + COL2 + " = '" + oldField + "'";
        db.execSQL(query);
    }

    /**
     * Delete from database
     */
    public void deleteName(String field, String record) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + field + "'" + " AND " + COL3
                + " = '" + record + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + record + " from database.");
        db.execSQL(query);
    }

}
