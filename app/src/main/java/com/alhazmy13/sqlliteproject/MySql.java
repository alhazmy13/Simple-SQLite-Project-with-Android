package com.alhazmy13.sqlliteproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alhazmy on 4/1/15.
 */
public class MySql extends SQLiteOpenHelper{

    private static final String DB_NAME="MySqlDatabase";
    private static final int VERSION_NUMBER=1;

    public static final String STUDENT_TABLE="student";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String EMAIL="email";
    public static final String NUMBER="number";

    private static final String DB_CREATE="create table "+ STUDENT_TABLE+" ("+ID+" integer primary key autoincrement,"
            +NAME+" text not null,"
            +EMAIL+" text not null,"
            +NUMBER+" integer not null)";

    public MySql(Context context){
        super(context,DB_NAME,null,VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST"+STUDENT_TABLE);
        onCreate(sqLiteDatabase);
    }
}
