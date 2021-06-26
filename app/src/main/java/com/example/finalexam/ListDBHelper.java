package com.example.finalexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//源码
public class ListDBHelper extends SQLiteOpenHelper{
    private SQLiteDatabase sqLiteDatabase;
    //创建数据库
    public ListDBHelper(Context context){
        super(context, DBUtils.DATABASE_NAME,null,DBUtils.DATABASE_VERION);
        sqLiteDatabase = this.getWritableDatabase();
    }
    //创建表
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+DBUtils.DATABASE_TABLE+"("+DBUtils.NOTEPAD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+DBUtils.NOTEPAD_CONTENT+" text, "+DBUtils.NOTEPAD_TIME+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
    //添加数据
    public boolean insertData(String userContent,String userTime){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBUtils.NOTEPAD_CONTENT,userContent);
        contentValues.put(DBUtils.NOTEPAD_TIME,userTime);
        return sqLiteDatabase.insert(DBUtils.DATABASE_TABLE,null,contentValues)>0;
    }
    //删除数据
    public boolean deleteData(String id){
        String sql = DBUtils.NOTEPAD_ID+"=?";
        //连接字符串，易错点!
        String[] contentValuesArray = new String[]{String.valueOf(id)};
        return sqLiteDatabase.delete(DBUtils.DATABASE_TABLE,sql,contentValuesArray)>0;
    }
    //修改数据
    public boolean updateData(String id,String content,String userYear){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBUtils.NOTEPAD_CONTENT,content);
        contentValues.put(DBUtils.NOTEPAD_TIME,userYear);
        String sql=DBUtils.NOTEPAD_ID+"=?";
        String[] strings=new String[]{id};
        return sqLiteDatabase.update(DBUtils.DATABASE_TABLE,contentValues,sql,strings)>0;
    }
    //查询数据
    public List<ListBean> query(){
        List<ListBean>list=new ArrayList<ListBean>();
        Cursor cursor = sqLiteDatabase.query(DBUtils.DATABASE_TABLE,null,null,
                null,null,null,DBUtils.NOTEPAD_ID+" desc");
        if (cursor!=null){
            while (cursor.moveToNext()){
                ListBean noteInfo=new ListBean();
                String id = String.valueOf(cursor.getInt(cursor.getColumnIndex(DBUtils.NOTEPAD_ID)));
                String content=cursor.getString(cursor.getColumnIndex(DBUtils.NOTEPAD_CONTENT));
                String time=cursor.getString(cursor.getColumnIndex(DBUtils.NOTEPAD_TIME));
                noteInfo.setId(id);
                noteInfo.setNotepadContent(content);
                noteInfo.setNotepadTime(time);
                list.add(noteInfo);
            }
            cursor.close();
        }
        return list;
    }
}
