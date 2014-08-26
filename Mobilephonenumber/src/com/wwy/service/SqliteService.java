package com.wwy.service;

import java.util.ArrayList;
import java.util.List;

import com.wwy.db.UserSqlite;
import com.wwy.po.UserBean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SqliteService {
	private static final String tag = "SqliteService";
	private Context context;

	public SqliteService(Context context) {
		this.context = context;
	}
   //增加
	public void insertsql() {
		String sql = "insert into usertable(username,userpwd) values('lb','345')";
		UserSqlite ul = new UserSqlite(context);
		SQLiteDatabase sd = ul.getWritableDatabase();
		sd.execSQL(sql);
		sd.close();
	}

	public void insertsqllist(UserBean user) {
		String sql = "insert into usertable(username,userpwd) values ('"
				+ user.getUsername() + "','" + user.getUserpwd() + "')";
		UserSqlite ul = new UserSqlite(context);
		SQLiteDatabase sd = ul.getWritableDatabase();
		sd.execSQL(sql);
		sd.close();
	}
//查询
	public void querysql() {
		String sql = "select * from usertable";
		UserSqlite ul = new UserSqlite(context);
		SQLiteDatabase sd = ul.getWritableDatabase();
		Cursor cu = sd.rawQuery(sql, null);
		cu.moveToFirst();
		while (!cu.isAfterLast()) {
			int userid = cu.getInt(0);
			String username = cu.getString(1);
			String userpwd = cu.getString(2);
			cu.moveToNext();
		}

		cu.close();
		sd.close();
	}

	public List<UserBean> querysqlList() {
		ArrayList<UserBean> userlist = new ArrayList<UserBean>();
		String sql = "select * from usertable order by userid desc";
		UserSqlite ul = new UserSqlite(context);
		SQLiteDatabase sd = ul.getWritableDatabase();
		Cursor cu = sd.rawQuery(sql, null);
		cu.moveToFirst();
		while (!cu.isAfterLast()) {
			UserBean user = new UserBean();
			String userid = cu.getString(0);
			String username = cu.getString(1);
			String userpwd = cu.getString(2);
			Log.i(tag, username + userpwd);
			user.setUserid(userid);
			user.setUsername(username);
			user.setUserpwd(userpwd);
			userlist.add(user);
			cu.moveToNext();
		}
		sd.close();
		return userlist;
	}
//修改
	public void updatesql() {
		String sql = "update usertable set username='www' where username='lb'";
		UserSqlite ul = new UserSqlite(context);
		SQLiteDatabase sd = ul.getWritableDatabase();
		sd.execSQL(sql);
		sd.close();
	}

	public void updatesqlsql(UserBean user) {
		String sql = "update usertable set username='" + user.getUsername()
				+ "',userpwd='" + user.getUserpwd() + "' where userid='"
				+ user.getUserid() + "'";
		System.out.println(user.getUserid());

		UserSqlite ul = new UserSqlite(context);
		SQLiteDatabase sd = ul.getWritableDatabase();
		sd.execSQL(sql);
		sd.close();
	}
//删除
	public void deletesql() {
		String sql = "delete from usertable where userpwd='123'";
		UserSqlite ul = new UserSqlite(context);
		SQLiteDatabase sd = ul.getWritableDatabase();
		sd.execSQL(sql);
		sd.close();
	}

	public void deletesqlsql(String userid) {
		String sql = "delete from usertable where userid=" + userid;
		UserSqlite ul = new UserSqlite(context);
		SQLiteDatabase sd = ul.getWritableDatabase();
		sd.execSQL(sql);
		sd.close();
	}
}
