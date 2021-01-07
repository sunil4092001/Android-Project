package com.example.settask.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.settask.Model.todoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public  static  final String NAME = "toDoDatabase";
    public  static  final String TODO_TABLE = "todo";
    public  static  final String ID ="id";
    public  static  final String TASK = "task";
    public  static  final String STATUS  = "status";
    public  static  final String CREATE_TODO_TABLE = "CREATE TABLE "+TODO_TABLE+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +TASK+
            "TEXT " +STATUS+ "INTEGER)";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context){
        super(context, NAME, null,VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //  Drop the table if exist
        db.execSQL("DROP TABLE IF EXISTS "+ TODO_TABLE);
        //  CREATE TABLE AGAIN
        onCreate(db);
    }
    public void openDatabase(){
        db = this.getWritableDatabase();

    }
    public  void insertTask(todoModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS,0);
        db.insert(TODO_TABLE, null,cv);
    }
    public List<todoModel> getAllTask(){
        List<todoModel> tasklist = new ArrayList<>();
        Cursor cur =null;
        db.beginTransaction();
        try {

            cur = db.query(TODO_TABLE, null, null, null, null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    do {
                        todoModel task = new todoModel();
                        task.setId(cur.getInt(cur.getColumnIndex(ID)));
                        task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        tasklist.add(task);
                    } while (cur.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
            cur.close();
        }

        return tasklist;
    }
    public void updateStatus( int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv,ID+"=?", new String[] {String.valueOf(id)});
    }
    public void updateTask( int id, String task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv,ID +"=?", new String[] {String.valueOf(id)});
    }
    public void deleteTask( int id){
        db.delete(TODO_TABLE,ID+"=?", new String[] {String.valueOf(id)});
    }

}
