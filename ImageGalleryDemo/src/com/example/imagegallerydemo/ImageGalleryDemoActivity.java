package com.example.imagegallerydemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ImageGalleryDemoActivity extends Activity {
	
	private static int RESULT_LOAD_IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_gallery_demo);
		
		Button buttonLoadImage = (Button)findViewById(R.id.buttonLoadPicture);
		buttonLoadImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = {MediaStore.Images.Media.DATA};
			
			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();
			
			int columnIdex = cursor.getColumnIndex(filePathColumn[0]);
			String picturesPath = cursor.getString(columnIdex);
			cursor.close();
			
			ImageView imageView = (ImageView)findViewById(R.id.imgView);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturesPath));
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_gallery_demo, menu);
		return true;
	}

}
