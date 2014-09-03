package com.zhuozhuo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {

	private static final int WIDTH = 40;
	
	private Rect rect = new Rect(0, 0, WIDTH, WIDTH);//���ƾ��ε�����
	private int deltaX,deltaY;//���λ�ú�ͼ�α߽��ƫ����
	private static Paint paint = new Paint();//����
	
	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setColor(Color.RED);//����ɫ
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Log.d("liyang", "onDraw");
		canvas.drawRect(rect, paint);//������

	}
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d("liyang", "action_down: x=" + x + ", y=" + y);
			if(!rect.contains(x, y)) {
				return false;//û���ھ����ϵ��������������Ϣ
			}
			deltaX = x - rect.left;
			deltaY = y - rect.top;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d("liyang", "action_move");
		case MotionEvent.ACTION_UP:
			Log.d("liyang", "action_up: x=" + x + ", y=" + y);
			Rect old = new Rect(rect);
			//���¾��ε�λ��
			rect.left = x - deltaX;
			rect.top = y - deltaY;
			rect.right = rect.left + WIDTH;
			rect.bottom = rect.top + WIDTH;
			old.union(rect);//Ҫˢ�µ��������¾���������ɾ�������Ĳ���
			invalidate(old);//����Ч�ʿ��ǣ��趨������ֻ���оֲ�ˢ�£�����ˢ������view
			break;
		}
		return true;//�����˴�����Ϣ����Ϣ���ٴ���
	}

}
