package com.example.e_reader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSupport  extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "BOOK_TABLE";
    public static final String COLUMN_BOOK_ID = "BOOK_ID";
    public static final String COLUMN_BOOK_TITLE = "BOOK_TITLE";
    public static final String COLUMN_BOOK_AUTHOR = "BOOK_AUTHOR";
    public static final String COLUMN_BOOK_COMPLETE = "BOOK_COMPLETE";

    public DatabaseSupport(@Nullable Context context) {
        super(context, "books.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String command = "CREATE TABLE "+TABLE_NAME+ " ("
                +COLUMN_BOOK_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_BOOK_TITLE+"TEXT,"
                +COLUMN_BOOK_AUTHOR+"TEXT,"
                +COLUMN_BOOK_COMPLETE+"BOOL)";
        db.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BOOK_TITLE, book.getTitle());
        cv.put(COLUMN_BOOK_AUTHOR, book.getAuthor());
        cv.put(COLUMN_BOOK_COMPLETE, book.getComplete());
        long insert = db.insert(TABLE_NAME, null, cv);
        return insert != -1;
    }
    public boolean deleteBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_BOOK_ID+"="+book.getId();
        Cursor cursor = db.rawQuery(command, null);
        return cursor.moveToFirst();
    }
    public List<Book> getAll(){
        List<Book> bookList = new ArrayList<>();

        String command = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(command, null);
        if (cursor.moveToFirst()){
            do{
                int bookID=cursor.getInt(0);
                String bookTITLE=cursor.getString(1);
                String bookAUTHOR=cursor.getString(2);
                boolean bookCOMPLETE= cursor.getInt(3) == 1;
                Book book = new Book(bookID, bookTITLE, bookAUTHOR, bookCOMPLETE);
                bookList.add(book);
            }while (cursor.moveToNext());}
        cursor.close();
        db.close();
        return bookList;
    }
}
