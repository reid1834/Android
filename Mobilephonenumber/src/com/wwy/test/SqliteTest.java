package com.wwy.test;

import junit.framework.Assert;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.wwy.db.UserSqlite;
import com.wwy.service.SqliteService;

public class SqliteTest extends AndroidTestCase {
	private static final String tag = "SqliteTest";
	private SqliteService sqlservice;
	public void sqltest() {
		UserSqlite ul = new UserSqlite(this.getContext());
		SQLiteDatabase sd = ul.getWritableDatabase();
		sd.close();
		Assert.assertEquals(1, 1);
	}

	public void testinsert() {
		 sqlservice=new SqliteService(this.getContext());
		sqlservice.insertsql();
		Log.i(tag, "testinsert success!");
	}

	public void testQuery() {
		sqlservice=new SqliteService(this.getContext());
		sqlservice.querysql();
		Log.i(tag, "testQuery success!");
	}
	public void testQuerylist() {
		sqlservice=new SqliteService(this.getContext());
		sqlservice.querysqlList();
		
		Log.i(tag, "testQuery success!");
	}

	public void testupdate() {
		sqlservice=new SqliteService(this.getContext());
		sqlservice.updatesql();
		Log.i(tag, "testupdate success!");
	}

	public void testdelete() {
		sqlservice=new SqliteService(this.getContext());
		sqlservice.deletesql();
		Log.i(tag, "testdelete success!");
	}

	public void test() {
		Assert.assertEquals(1, 1);
	}
}
