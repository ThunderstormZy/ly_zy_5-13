package com.example.msii;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final  String CREATE_TABLE = "create table Book(" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";
    private Context mContext;
    /**
     * 构造函数
     * @param context---上下文
     * @param name--数据库的名称
     * @param factory--类型
     * @param version--版本--用于更新数据
     */
    public MyDatabaseHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库
        //execute执行SQL语句
        db.execSQL(CREATE_TABLE);
        Toast.makeText(mContext,"create database successed!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
