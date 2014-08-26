package com.wwy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserSqlite extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME="kk";
	private static final String USER_TABLE_NAME = "usertable";
	private static final String USER_TABLE_CREATE = "CREATE TABLE "
			+ USER_TABLE_NAME
			+ " (userid Integer primary key autoincrement,username VARCHAR,userpwd VARCHAR);";

	public UserSqlite(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(USER_TABLE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	  
	}


}
