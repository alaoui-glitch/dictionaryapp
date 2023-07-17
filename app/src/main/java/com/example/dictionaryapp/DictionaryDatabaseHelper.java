package com.example.dictionaryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DictionaryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dictionary.db";
    private static final int DATABASE_VERSION = 1;

    // Define the table and column names
    private static final String TABLE_WORDS = "words";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_WORD = "word";
    private static final String COLUMN_DEFINITION = "definition";

    // Create the table SQL statement
    private static final String CREATE_TABLE_WORDS =
            "CREATE TABLE " + TABLE_WORDS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_WORD + " TEXT, " +
                    COLUMN_DEFINITION + " TEXT);";

    public DictionaryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the database tables
        db.execSQL(CREATE_TABLE_WORDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
        // Typically, you would handle schema changes and data migration here
    }

    // Custom methods for inserting, retrieving, and searching data

    public void insertWord(String word, String definition) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD, word);
        values.put(COLUMN_DEFINITION, definition);
        db.insert(TABLE_WORDS, null, values);
    }

    public String getDefinition(String word) {
        SQLiteDatabase db = getReadableDatabase();
        String definition = null;
        String[] projection = {COLUMN_DEFINITION};
        String selection = COLUMN_WORD + " = ?";
        String[] selectionArgs = {word};
        Cursor cursor = db.query(TABLE_WORDS, projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            definition = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEFINITION));
        }
        cursor.close();
        return definition;
    }

    public List<DictionaryItem> searchWords(String query) {
        List<DictionaryItem> searchResults = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {COLUMN_WORD, COLUMN_DEFINITION};
        String selection = COLUMN_WORD + " LIKE ?";
        String[] selectionArgs = {"%" + query + "%"};
        Cursor cursor = db.query(TABLE_WORDS, projection, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            String word = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WORD));
            String definition = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEFINITION));
            DictionaryItem dictionaryItem = new DictionaryItem(word, definition);
            searchResults.add(dictionaryItem);
        }
        cursor.close();
        return searchResults;
    }
}
