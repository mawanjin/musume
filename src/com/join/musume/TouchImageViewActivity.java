package com.join.musume;

import android.app.Activity;
import android.content.Intent;
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
public class TouchImageViewActivity extends BaseActivity {
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
        initFooterView(1);
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

    public void onClick(float x, float y) {
        //右上角
        int width = 61;
        int height = 51;
        if (x > 550 && x < width + 520 && y > 22 && y < height + 22) {
            Intent intent = new Intent(this, ContentListActivity.class);
            startActivity(intent);
        }
        //返回
        int widthBack = 52;
        int heightBack = 38;
        if (x > 80 && x < widthBack + 80 && y > 950 && y < heightBack + 950) {
            finish();
        }
        //HOME
        int widthHome = 52;
        int heightHome = 38;
        if (x > 282 && x < widthHome + 282 && y > 950 && y < heightHome + 950) {
            ActivitiesManager.getInstance().finishAll();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
//            finish();
        }
        //pop
        if (x > 470 && x < widthHome + 470 && y > 950 && y < heightHome + 950) {
            showMore();
        }
    }
}
