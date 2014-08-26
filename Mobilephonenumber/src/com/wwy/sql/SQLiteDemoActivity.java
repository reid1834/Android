package com.wwy.sql;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wwy.R;
import com.wwy.po.UserBean;
import com.wwy.service.SqliteService;

public class SQLiteDemoActivity extends Activity implements OnClickListener {
	SqliteService sqlservice = new SqliteService(SQLiteDemoActivity.this);
	private ListView mListView;
	private Button mbadd, mbupdate, mbquery;
	private EditText username, userpwd;
	private TextView yincang, listtext, deletes;
	private UserBean user;
	private String userid;
	private ArrayList<UserBean> userlist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mbadd = (Button) findViewById(R.id.b_add);
		mbupdate = (Button) findViewById(R.id.b_update);
		mbquery = (Button) findViewById(R.id.b_query);

		mbadd.setOnClickListener(this);
		mbupdate.setOnClickListener(this);
		mbquery.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//---------------- 增加----------------
		if (v.getId() == R.id.b_add) {
			user = new UserBean();
			username = (EditText) findViewById(R.id.username);
			userpwd = (EditText) findViewById(R.id.userpwd);
			if (username.length()==0) {
				Toast.makeText(getApplicationContext(), "姓名不能为空!",
						Toast.LENGTH_SHORT).show();
			} else if (userpwd.length()!=11) {
				Toast.makeText(getApplicationContext(), "手机号码个数不正确!",
						Toast.LENGTH_SHORT).show();
			} else if (username != null && username.length()!=0
					&& userpwd != null && userpwd.length()==11) {
				user.setUsername(username.getText().toString());
				user.setUserpwd(userpwd.getText().toString());
				sqlservice.insertsqllist(user);
				username.setText("");
				userpwd.setText("");
				// --------------更新显示-----------------
				mListView = (ListView) findViewById(R.id.listview);
				userlist = (ArrayList<UserBean>) sqlservice.querysqlList();
				mListView.setAdapter(new UserListAdapter(
						SQLiteDemoActivity.this, userlist));

			}

		} else if (v.getId() == R.id.b_update) {
			// -------------修改--------------
			yincang = (TextView) findViewById(R.id.textview0);
			username = (EditText) findViewById(R.id.username);
			userpwd = (EditText) findViewById(R.id.userpwd);
			user = new UserBean();
			user.setUserid(yincang.getText().toString());
			user.setUsername(username.getText().toString());
			user.setUserpwd(userpwd.getText().toString());
			sqlservice.updatesqlsql(user);
			mListView = (ListView) findViewById(R.id.listview);
			mListView.setAdapter(new UserListAdapter(SQLiteDemoActivity.this,
					(ArrayList<UserBean>) sqlservice.querysqlList()));

		} else if (v.getId() == R.id.b_query) {
			// ------------查询-----------
			mListView = (ListView) findViewById(R.id.listview);
			mListView.setAdapter(new UserListAdapter(SQLiteDemoActivity.this,
					(ArrayList<UserBean>) sqlservice.querysqlList()));
		}

	}

	private class UserListAdapter extends BaseAdapter {
		private Context context;
		private ArrayList<UserBean> userlist;

		public UserListAdapter(Context context, ArrayList<UserBean> userlist) {
			super();
			this.context = context;
			this.userlist = userlist;
		}

		@Override
		public int getCount() {
			return userlist.size();
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// ---------给list加适配器-----------------
			convertView = getLayoutInflater().inflate(R.layout.listview, null);
			listtext = (TextView) convertView.findViewById(R.id.tv);
			listtext.setText("用户名：" + userlist.get(position).getUsername()
					+ "    密码:" + userlist.get(position).getUserpwd());
			// ------------删除------------
			deletes = (TextView) convertView.findViewById(R.id.delete);
			deletes.setOnClickListener(new TextView.OnClickListener() {

				@Override
				public void onClick(View v) {
					userid = userlist.get(position).getUserid();
					sqlservice.deletesqlsql(userid);
					mListView = (ListView) findViewById(R.id.listview);
					mListView.setAdapter(new UserListAdapter(
							SQLiteDemoActivity.this,
							(ArrayList<UserBean>) sqlservice.querysqlList()));
				}
			});
			// -------------点击listview 把值返回给用户名和密码------------
			listtext.setOnClickListener(new TextView.OnClickListener() {

				@Override
				public void onClick(View v) {

					yincang = (TextView) findViewById(R.id.textview0);
					username = (EditText) findViewById(R.id.username);
					userpwd = (EditText) findViewById(R.id.userpwd);

					yincang.setText(userlist.get(position).getUserid());
					username.setText(userlist.get(position).getUsername());
					userpwd.setText(userlist.get(position).getUserpwd());

				}
			});
			return convertView;
		}

	}

}