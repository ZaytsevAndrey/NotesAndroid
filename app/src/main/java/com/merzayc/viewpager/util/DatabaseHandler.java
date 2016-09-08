package com.merzayc.viewpager.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notesManager";
    private static final String TABLE_NOTES = "notes";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATA = "data";
    private static final String KEY_DATE = "date";
    private static final String KEY_ICON = "icon";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void DatabaseHandler(){

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "("  +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_DATA + " TEXT," +
                KEY_DATE + " LONG," +
                KEY_ICON + " INTEGER); ";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);

        onCreate(db);
    }


    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.get_title());
        values.put(KEY_DATA, note.get_data());
        values.put(KEY_DATE, note.get_date());
        values.put(KEY_ICON, note.get_icon());
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    /*public Note getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTES, new String[] { KEY_ID,
                        KEY_TITLE, KEY_DATA, KEY_DATE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Note note = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getLong(3));

        return note;
    }*/
    public Note getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(DATABASE_NAME, null, KEY_ID + " = ?", new String[] {String.valueOf(id)}, null, null, null, null);

        mCursor.moveToFirst();
        String title = mCursor.getString(0);
        String data = mCursor.getString(1);
        long date = mCursor.getLong(2);
        int icon = mCursor.getInt(3);



        mCursor.close();
        return new Note(id, title, data, date, icon);
    }

    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<Note>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES + " ORDER BY " + KEY_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getLong(3), cursor.getInt(4));
                note.set_id(Integer.parseInt(cursor.getString(0)));
                note.set_title(cursor.getString(1));
                note.set_data(cursor.getString(2));
                note.set_date(cursor.getLong(3));
                note.set_icon(cursor.getInt(4));
                noteList.add(note);
            } while (cursor.moveToNext());
        }

        return noteList;
    }


    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.get_title());
        values.put(KEY_DATA, note.get_data());
        values.put(KEY_DATE, note.get_date());
        values.put(KEY_ICON, note.get_icon());

        return db.update(TABLE_NOTES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(note.get_id()) });
    }


    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, KEY_ID + " = ?", new String[] { String.valueOf(note.get_id()) });
        db.close();
    }


    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, null, null);
        db.close();
    }


    public int getNoteCount() {
        String countQuery = "SELECT * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;

    }
}