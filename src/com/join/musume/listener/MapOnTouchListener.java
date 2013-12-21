package com.join.musume.listener;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/17/13
 * Time: 5:00 PM
 */
public class MapOnTouchListener implements View.OnTouchListener {
    String TAG = MapOnTouchListener.class.getName();
    int lastX, lastY;

    /**
     * 1. event.getX : 是以 widget（控件） 的左上角 为 原点的 X坐标
     * event.getRawX() : 是以 屏幕左上角 为原点的 X坐标
     * 2. View.layout(left, top, right, bottom);
     * left   ：   控件左端以   父  控件的 左上角为原点的X坐标
     * top    ：   控件顶端以   父  控件的 左上角为原点的Y坐标
     * right  ：   控件右端以   父  控件的 左上角为原点的X坐标
     * bottom ：  控件底端以   父  控件的 左上角为原点的Y坐标
     */

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i(TAG, "onTouch called." + event.getAction());
        /**
         * 这个地方的逻辑是：
         * 在 down 的时候记录一下距离屏幕左上角的距离
         * 然后move的时候来再来计算一下距离
         * 2着的差值就是分别 x轴和y轴移动的距离
         */
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "ACTION_DOWN called.");
                //   按下的时候距离屏幕左上角的距离
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "ACTION_MOVE called.");
                //   移动的时候距离屏幕左上角的距离
                int nowX = (int) event.getRawX();
                int nowY = (int) event.getRawY();
                // X轴和Y轴移动的距离
                int moveX = nowX - lastX;
                int moveY = nowY - lastY;
                // 分别计算距离
                int top = v.getTop() + moveY;
                int bottom = v.getBottom() + moveY;
                int left = v.getLeft() + moveX;
                int right = v.getRight() + moveX;

                v.layout(left, top, right, bottom);

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();

                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "action_up called.");
                break;

        }
        return true;
    }
}
