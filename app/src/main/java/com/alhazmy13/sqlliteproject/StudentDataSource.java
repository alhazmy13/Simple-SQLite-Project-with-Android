package com.alhazmy13.sqlliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alhazmy on 4/1/15.
 */
public class StudentDataSource {

    MySql mySql;
    SQLiteDatabase sqLiteDatabase;
    String[] allColumes=new String[]{MySql.ID,MySql.NAME,MySql.EMAIL,MySql.NUMBER};

    public StudentDataSource(Context context){
        mySql=new MySql(context);

    }

    public void open(){
        try {
            sqLiteDatabase=mySql.getWritableDatabase();
        }catch (Exception e){
            Log.d("ERROR",e.getMessage());
        }
    }

    public void close(){
        sqLiteDatabase.close();
    }


    public void createStudent(String name,String email,int number){
        ContentValues list=new ContentValues();
        list.put(MySql.NAME,name);
        list.put(MySql.EMAIL,email);
        list.put(MySql.NUMBER,number);
        sqLiteDatabase.insert(MySql.STUDENT_TABLE,null,list);

    }

    public Student getStudent(int id){
        Student student=new Student();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+MySql.STUDENT_TABLE+" WHERE "+MySql.ID+" = ?",new String[]{id+""});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            student.setId(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setEmail(cursor.getString(2));
            student.setNumber(cursor.getInt(3));
            cursor.close();
        }

        return student;
    }
    public void updateStudent(int id,String name,String email,int number){
        ContentValues list=new ContentValues();
        list.put(MySql.NAME,name);
        list.put(MySql.EMAIL,email);
        list.put(MySql.NUMBER,number);
        sqLiteDatabase.update(MySql.STUDENT_TABLE,list,MySql.ID+" = "+id,null);

    }

    public void deletStudent(int id){
        sqLiteDatabase.delete(MySql.STUDENT_TABLE,MySql.ID+" = "+id,null);
    }

    public List<Student> getAllStudents(){
        List<Student> studentList=new ArrayList<Student>();

        Cursor cursor=sqLiteDatabase.query(MySql.STUDENT_TABLE,allColumes,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Student student=new Student();
            student.setId(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setEmail(cursor.getString(2));
            student.setNumber(cursor.getInt(3));
            studentList.add(student);
            cursor.moveToNext();

        }
        cursor.close();
        return studentList;

    }



}
