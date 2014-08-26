package com.wwy.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.wwy.R;
import com.wwy.sql.SQLiteDemoActivity;

public class UsersFirstActivity extends Activity {
   private ImageButton mImageButton;
	@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.first);
	mImageButton=(ImageButton) findViewById(R.id.imagebutton);
	mImageButton.setOnClickListener(new ImageButton.OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			intent.setClass(UsersFirstActivity.this, SQLiteDemoActivity.class);
			startActivity(intent);
		}
		
	});
}
	
}
