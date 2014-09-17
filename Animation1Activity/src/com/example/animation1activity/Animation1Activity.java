package com.example.animation1activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
/**
 * @description android�е���֡����.
 * ��֡������ԭ��ܼ򵥣�����Ӱ�Ĳ���һ����һ�������Ƶ�ͼƬ��ͣ���л������л��ٶȴﵽһ��ֵʱ��
 * ���ǵ��Ӿ��ͻ���ֲ�Ӱ����Ӱ�ĳ��ֱ�֤���Ӿ��ϱ仯�������ԣ���ʱ��ͼƬ���л������������о͸���ʵ��һ���ˡ�
 * ��ʹ����֡������
 * ��һ������Ҫ��res/drawable�ļ������½�һ��xml�ļ������ļ���ϸ�����˶�������ʱ���õ�ͼƬ���л�ÿ��ͼƬ
 *        ���õ�ʱ�䡢�Ƿ�Ϊ�������ŵȵȡ�(��Щ����˵����res/anim�ļ����·��ø��ļ�����ʵ֤���������Ŷ)
 * �ڶ������ڴ����У����ö��������ļ�����ֵ���ض���ͼƬչʾ�ؼ����籾�����е�ImageView��
 * ��������ͨ��imageView.getBackGround()��ȡ��Ӧ��AnimationDrawable����Ȼ��ͨ���ö���ķ������п��ƶ���
 * @author chenzheng_java
 *
 */
public class Animation1Activity extends Activity {
	ImageView imageView ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation1);
        
        imageView = (ImageView) findViewById(R.id.imageView_animation1);
        imageView.setBackgroundResource(R.drawable.animation1_drawable);
        
    }
    
    
    public void myClickHandler(View targetButton){
    	// ��ȡAnimationDrawable����
    	AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getBackground();
    	
    	// �����Ƿ���������
    	if(animationDrawable.isRunning()){
    		//ֹͣ��������
    		animationDrawable.stop();
    	}
    	else{
    		//��ʼ���߼�����������
    		animationDrawable.start();
    	}
    	
    	
    }
}