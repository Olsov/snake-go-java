package com.tito.snake_go;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conn extends SQLiteOpenHelper {
	final static String DB_NAME="SGbd.s3db";
	Context mContext;
	public Conn(Context context,int dbver){
		super(context,DB_NAME,null,dbver);
		mContext=context;
		
	}
	@Override 
	public void onCreate (SQLiteDatabase db){
		db.execSQL("CREATE TABLE if not exists 'Test' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'Result' text,'time' text);");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS 'Test'");
		onCreate(db);
		db.close();
		
		
	}
	public void fillData(SQLiteDatabase db,String Result, String time )
	{
	
		db.execSQL("INSERT INTO 'Test' ('Result', 'time') VALUES ("+"'"+Result+"','"+time+"'"+"); ");
	}
	public void readData(SQLiteDatabase bd){
		
	}

}
