package com.join.musume.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.join.musume.R;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/17/13
 * Time: 2:29 PM
 */
public class DrawView extends View {
    String TAG = DrawView.class.getName();
    protected int lastX;
    protected int lastY;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw called.");
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        Matrix matrix = new Matrix();
        matrix.postScale(2f, 2f);
        Bitmap dstbmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);

        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(dstbmp, 0, 0, null);
    }
}
