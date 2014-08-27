package com.example.handlertest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HandlerTestActivity extends Activity {
	private final int MSG_HELLO = 0;
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_test);
		
		new CustomThread().start();
		
		Button btn = (Button)findViewById(R.id.button);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str = "hello";
				Log.d("liyang", "MainThread is ready to send msg: " + str);
				mHandler.obtainMessage(MSG_HELLO, str).sendToTarget();
			}
		});
	}
	
	class CustomThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Looper.prepare();
			mHandler = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					switch (msg.what) {
					case MSG_HELLO:
						Log.d("liyang", "CustomThread receive msg: " + (String)msg.obj);
						break;

					default:
						break;
					}
				}
				
			};
			Looper.loop();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.handler_test, menu);
		return true;
	}

}
