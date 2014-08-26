package com.example.sqlitedatabasedemo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteDatabaseDemo extends Activity implements AdapterView.OnItemClickListener{
	
	private BooksDB mBooksDB;
	private Cursor mCursor;
	private EditText BookName;
	private EditText BookAuthor;
	private ListView BooksList;
	
	private int BOOK_ID = 0;
	protected final static int MENU_ADD = Menu.FIRST;
	protected final static int MENU_DELETE = Menu.FIRST + 1;
	protected final static int MENU_UPDATE = Menu.FIRST + 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_database_demo);
		setUpViews();
	}
	
	public void setUpViews() {
		mBooksDB = new BooksDB(this);
		mCursor = mBooksDB.select();
		
		BookName = (EditText)findViewById(R.id.bookname);
		BookAuthor = (EditText)findViewById(R.id.author);
		BooksList = (ListView)findViewById(R.id.bookslist);
		
		BooksList.setAdapter(new BooksListAdapter(this, mCursor));
		BooksList.setOnItemClickListener(this);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_ADD:
			add();
			break;
		case MENU_DELETE:
			delete();
			break;
		case MENU_UPDATE:
			update();
			break;			
		}
		return true;
	}
	
	public void add() {
		String bookname = BookName.getText().toString();
		String author = BookAuthor.getText().toString();
		
		if (bookname.equals("") || author.equals("")) {
			return;
		}
		
		mBooksDB.insert(bookname, author);
		mCursor.requery();
		BooksList.invalidateViews();
		BookName.setText("");
		BookAuthor.setText("");
		Toast.makeText(this, "Add Successed!", Toast.LENGTH_SHORT).show();
	}

	public void delete() {
		if (BOOK_ID == 0) {
			return;
		}
		
		mBooksDB.delete(BOOK_ID);
		mCursor.requery();
		BooksList.invalidateViews();
		BookName.setText("");
		BookAuthor.setText("");
		Toast.makeText(this, "Delete Successed!", Toast.LENGTH_SHORT).show();
	}
	
	public void update() {
		String bookname = BookName.getText().toString();
		String author = BookAuthor.getText().toString();
		
		if (bookname.equals("") || author.equals("")) {
			return;
		}
		
		mBooksDB.update(BOOK_ID, bookname, author);
		mCursor.requery();
		BooksList.invalidateViews();
		BookName.setText("");
		BookAuthor.setText("");
		Toast.makeText(this, "Update Successed!", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite_database_demo, menu);
		
		menu.add(Menu.NONE, MENU_ADD, 0, "ADD");
		menu.add(Menu.NONE, MENU_DELETE, 0, "DELETE");
		menu.add(Menu.NONE, MENU_UPDATE, 0, "UPDATE");
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		mCursor.moveToPosition(position);
		BOOK_ID = mCursor.getInt(0);
		BookName.setText(mCursor.getString(1));
		BookAuthor.setText(mCursor.getString(2));
	}
	
	public class BooksListAdapter extends BaseAdapter {
		
		private Context mContext;
		private Cursor mCursor;

		public BooksListAdapter(Context mContext, Cursor mCursor) {
			super();
			this.mContext = mContext;
			this.mCursor = mCursor;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mCursor.getCount();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView mTextView = new TextView(mContext);
			mCursor.moveToPosition(position);
			mTextView.setText(mCursor.getString(1) + "____" + mCursor.getString(2));
			return mTextView;
		}
		
	}

}
