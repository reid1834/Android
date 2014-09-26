package com.device.photo;

import android.app.Dialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;

/**
 * Androidʵ�ֻ�ȡ����������ͼƬ
 * @Description: Androidʵ�ֻ�ȡ����������ͼƬ

 * @FileName: MyDevicePhotoActivity.java 

 * @Package com.device.photo 

 * @Author Hanyonglu

 * @Date 2012-5-10 ����04:43:55 

 * @Version V1.0
 */
public class MyDevicePhotoActivity extends FragmentActivity implements LoaderCallbacks<Cursor>{
	private Bitmap bitmap = null;
	private byte[] mContent = null;
	
	private ListView listView = null;
	private SimpleCursorAdapter simpleCursorAdapter = null;
	
	private static final String[] STORE_IMAGES = {
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.LATITUDE,
        MediaStore.Images.Media.LONGITUDE,
        MediaStore.Images.Media._ID
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        listView = (ListView)findViewById(android.R.id.list);
        simpleCursorAdapter = new SimpleCursorAdapter(
        		this, 
        		R.layout.simple_list_item, 
        		null, 
        		STORE_IMAGES, 
        		new int[] { R.id.item_title, R.id.item_value}, 
        		0
        		);
        
        simpleCursorAdapter.setViewBinder(new ImageLocationBinder());
        listView.setAdapter(simpleCursorAdapter);
        // ע��˴���getSupportLoaderManager()��������getLoaderManager()������
        getSupportLoaderManager().initLoader(0, null, this);
        
        // ������ʾͼƬ
        listView.setOnItemClickListener(new ShowItemImageOnClickListener());
    }
    
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
    	// TODO Auto-generated method stub
    	// Ϊ�˲鿴��Ϣ����Ҫ�õ�CursorLoader��
    	CursorLoader cursorLoader = new CursorLoader(
    			this, 
    			MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
    			STORE_IMAGES, 
    			null, 
    			null, 
    			null);
    	return cursorLoader;
    }
    
    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
    	// TODO Auto-generated method stub
    	simpleCursorAdapter.swapCursor(null);
    }
    
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
    	// TODO Auto-generated method stub
    	// ʹ��swapCursor()��������ʹ�ɵ��α겻���رգ�
    	simpleCursorAdapter.swapCursor(cursor);
    	Log.d("liyang", "listView size: " + listView.getCount());
    }
    
    // ��ͼƬ��λ�ð󶨵���ͼ
    private class ImageLocationBinder implements ViewBinder{ 
    	@Override
    	public boolean setViewValue(View view, Cursor cursor, int arg2) {
    		// TODO Auto-generated method stub
    		if (arg2 == 1) {
    			// ͼƬ���Ⱥ�γ��
                double latitude = cursor.getDouble(arg2);
                double longitude = cursor.getDouble(arg2 + 1);
                
                if (latitude == 0.0 && longitude == 0.0) {
                    ((TextView)view).setText("λ�ã�δ֪");
                } else {
                    ((TextView)view).setText("λ�ã�" + latitude + ", " + longitude);
                }
                
                // ��Ҫע�⣺��ʹ��ViewBinder������ʱ�����뷵���棻����SimpleCursorAdapter�������Լ��ķ�ʽ�����ݡ�
                return true;
            } else {
                return false;
            }
    	}
    }
    
    // ��������ʾͼƬ�¼�������
    private class ShowItemImageOnClickListener implements OnItemClickListener{
    	@Override
    	public void onItemClick(AdapterView<?> parent, View view, int position,
    			long id) {
    		// TODO Auto-generated method stub
    		final Dialog dialog = new Dialog(MyDevicePhotoActivity.this);
    		// �ԶԻ�����ʽ��ʾͼƬ
			dialog.setContentView(R.layout.image_show);
			dialog.setTitle("ͼƬ��ʾ");

			ImageView ivImageShow = (ImageView) dialog.findViewById(R.id.ivImageShow);
			Button btnClose = (Button) dialog.findViewById(R.id.btnClose);

			btnClose.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					
					// �ͷ���Դ
					if(bitmap != null){
						bitmap.recycle();
					}
				}
			});
			
			Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().
					  appendPath(Long.toString(id)).build();
			FileUtil file = new FileUtil();
			ContentResolver resolver = getContentResolver();
			
			// ��Uri�ж�ȡͼƬ��Դ
			try {
				mContent = file.readInputStream(resolver.openInputStream(Uri.parse(uri.toString())));
				bitmap = file.getBitmapFromBytes(mContent, null);
				ivImageShow.setImageBitmap(bitmap);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			dialog.show();
    	}
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	
    	if(bitmap != null){
			bitmap.recycle();
		}
    }
}