package com.example.hellogallery;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class HelloGalleryActivity extends Activity {
	
	private ImageView imageOne;
	private ImageView imageAll;
	private Gallery gallery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello_gallery);
		
		imageOne = (ImageView)findViewById(R.id.imageView);
		gallery = (Gallery)findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapter(this));
	}
	
	class ImageAdapter extends BaseAdapter implements ImageResource {
		
		private int galleryItemBackground;
		private Context context;

		public ImageAdapter(Context context) {
			super();
			this.context = context;
			
			TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
			galleryItemBackground = a.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 0);
			a.recycle();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dImageID.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			imageOne.setScaleType(ImageView.ScaleType.FIT_CENTER);
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			imageAll = new ImageView(context);
			imageAll.setImageResource(dImageID[position]);
			imageAll.setLayoutParams(new Gallery.LayoutParams(120, 120));
			imageAll.setScaleType(ImageView.ScaleType.FIT_XY);
			imageAll.setBackgroundResource(galleryItemBackground);
			return imageAll;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hello_gallery, menu);
		return true;
	}

}
