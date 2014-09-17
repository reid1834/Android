package com.example.animation1activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
/**
 * @description android中的逐帧动画.
 * 逐帧动画的原理很简单，跟电影的播放一样，一张张类似的图片不停的切换，当切换速度达到一定值时，
 * 我们的视觉就会出现残影，残影的出现保证了视觉上变化的连续性，这时候图片的切换看在我们眼中就跟真实的一样了。
 * 想使用逐帧动画：
 * 第一步：需要在res/drawable文件夹下新建一个xml文件，该文件详细定义了动画播放时所用的图片、切换每张图片
 *        所用的时间、是否为连续播放等等。(有些文章说，在res/anim文件夹下放置该文件，事实证明，会出错哦)
 * 第二步：在代码中，将该动画布局文件，赋值给特定的图片展示控件，如本例子中的ImageView。
 * 第三步：通过imageView.getBackGround()获取相应的AnimationDrawable对象，然后通过该对象的方法进行控制动画
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
    	// 获取AnimationDrawable对象
    	AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getBackground();
    	
    	// 动画是否正在运行
    	if(animationDrawable.isRunning()){
    		//停止动画播放
    		animationDrawable.stop();
    	}
    	else{
    		//开始或者继续动画播放
    		animationDrawable.start();
    	}
    	
    	
    }
}