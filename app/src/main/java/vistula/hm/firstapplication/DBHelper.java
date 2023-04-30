package vistula.hm.firstapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    public DBHelper(Context context){
        super(context,"Login.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key,password TEXT,title TEXT,description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        onCreate(sqLiteDatabase);
    }
    public boolean insertUser(String username,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("title",TITLE);
        contentValues.put("description",DESCRIPTION);
        long result = myDB.insert("users",null,contentValues);
        if(result!=-1)return true;
        else return false;
    }
    public boolean updateNotes(String username,String title,String description){
        SQLiteDatabase updateDb = this.getWritableDatabase();
        ContentValues contentNotes = new ContentValues();
        contentNotes.put("title",title);
        contentNotes.put("description",description);
        int result =updateDb.update("users",contentNotes,"username =?",new String[]{title,description});
        if (result>0){
            updateDb.close();
            return true;
        }
        else return false;

    }
    public boolean userNameExist(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where username =?",new String[]{username});
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else return false;
    }
    public boolean userPasswordExist(String username,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where username =? and password =?",new String[]{username,password});
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {cursor.close();return false;}
    }
    public List<Note> recallNotes(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursorNote = sqLiteDatabase.rawQuery("Select * from users",null);
        List<Note> noteArrayList= new ArrayList<Note>();
        if (cursorNote.moveToFirst()){
            do {
                noteArrayList.add(new Note(cursorNote.getString(2),cursorNote.getString(3), cursorNote.getString(0)));
            }while (cursorNote.moveToNext());

        }
        cursorNote.close();
        return noteArrayList;
    }

}
