package com.join.musume;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.join.musume.data.ImageFactory;
import com.join.musume.data.Pointer;
import com.join.musume.view.TouchImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/17/13
 * Time: 5:13 PM
 */
public class TouchImageViewActivity extends Activity {
    String TAG = TouchImageViewActivity.class.getName();

    TextView aa;
    FrameLayout container;
    FrameLayout picContainer;
    TouchImageView touchImageView;
    List<ImageView> images = new ArrayList<ImageView>(0);

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        picContainer = (FrameLayout) findViewById(R.id.picContainer);
        container = (FrameLayout) findViewById(R.id.container);
        touchImageView = new TouchImageView(this);
        container.addView(touchImageView);
//        initPic();
    }


    /**
     * 通过缩放级别显示图片
     */
    public void freshPic(Double width) {
        Log.i(TAG, "image width=" + width);
        int i = 0;
        //移动image
        for (ImageView imageView : images) {
            Pointer pointer = ImageFactory.getInstance(TouchImageViewActivity.this).getViewItems().get(i);
            slideview(imageView, pointer.leftTopX, pointer.leftTopY);

            i++;
            Log.i(TAG, "scroll to::X=" + pointer.leftTopX + ";Y=" + pointer.leftTopY);
//            imageView.invalidate();
        }
    }


    public void slideview(final View view, final float p1, final float p2) {
        Log.i(TAG, "slideview=" + view.getX() + ";" + view.getY());
        view.setX(p1);
        view.setY(p2);
        view.invalidate();
    }
}
