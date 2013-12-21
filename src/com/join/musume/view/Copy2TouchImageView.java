package com.join.musume.view;

import android.graphics.*;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;
import com.join.musume.R;
import com.join.musume.TouchImageViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/17/13
 * Time: 5:13 PM
 */
public class Copy2TouchImageView extends ImageView {

    String TAG = Copy2TouchImageView.class.getName();

    Paint mPaint;
    private Path mPath;
    private Canvas  mCanvas;
    Pointer pointer = new Pointer();
    Canvas _canvas;
    float x_down = 0;
    float y_down = 0;
    PointF mid = new PointF();
    float oldDist = 1f;
    float oldRotation = 0;
    Matrix matrix = new Matrix();
    Matrix matrix1 = new Matrix();
    Matrix savedMatrix = new Matrix();

    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    int mode = NONE;

    boolean matrixCheck = false;
    boolean drawOthers = false;

    int widthScreen;
    int heightScreen;

    Bitmap originalMap;
    Bitmap gintama;
    Bitmap testBitMap;

    float postScale1;
    float postScale2;
    float postScale3;
    float postScale4;
    //动态生成的可点击的控件
    List<Pointer> viewItems;

    public Copy2TouchImageView(TouchImageViewActivity activity) {
        super(activity);
        gintama = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        Log.i(TAG, "width=" + gintama.getWidth() + ";height=" + gintama.getHeight());

        testBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.exhib_intro);

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        widthScreen = dm.widthPixels;
        heightScreen = dm.heightPixels;
        matrix = new Matrix();

        originalMap = initMap();
        viewItems = new ArrayList<Pointer>(0);
        Pointer p = new Pointer();
        p.originX = 100;
        p.originY = 100;
        p.width = 100;
        p.height = 50;

        viewItems.add(p);
        mCanvas = new Canvas(originalMap);

        mPaint = new Paint();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
        mPath = new Path();
    }

    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.drawBitmap(originalMap, matrix, mPaint);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        resetOnclick();
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mode = DRAG;
                x_down = event.getX();
                y_down = event.getY();
                Log.i(TAG, "x_down=" + x_down + ";y=" + y_down + ";leftTopX=" + pointer.leftTopX + ";leftTopY" + pointer.leftTopY);
                savedMatrix.set(matrix);
                Onclick(x_down, y_down);
                mPath.reset();
                mPath.moveTo(x_down, y_down);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = ZOOM;
                oldDist = spacing(event);
                oldRotation = rotation(event);
                savedMatrix.set(matrix);
                midPoint(mid, event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == ZOOM) {
                    matrix1.set(savedMatrix);
                    float rotation = rotation(event) - oldRotation;
                    float newDist = spacing(event);
                    float scale = newDist / oldDist;
                    postScale1 = scale;
                    postScale2 = scale;
                    postScale3 = mid.x;
                    postScale4 = mid.y;

                    matrix1.postScale(scale, scale, mid.x, mid.y);// 縮放

                    //test begin
                    mCanvas.drawCircle(mid.x, mid.y, 1, mPaint);


                            //test end
//                    matrix1.postRotate(rotation, mid.x, mid.y);// 旋轉
                            matrixCheck = matrixCheck();
                    if (matrixCheck == false) {
                        matrix.set(matrix1);
                        invalidate();
                    } else
                        drawOthers = true;
                } else if (mode == DRAG) {
                    matrix1.set(savedMatrix);
                    matrix1.postTranslate(event.getX() - x_down, event.getY()
                            - y_down);// 平移
                    matrixCheck = matrixCheck();
                    matrixCheck = matrixCheck();
                    if (matrixCheck == false) {
                        matrix.set(matrix1);
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
        }
        return true;
    }

    /**
     * 为图标加上点击事件
     */
    private void resetOnclick() {
        Log.i(TAG, "resetOnclick called.");
        if (viewItems == null) return;
        for (Pointer pointer1 : viewItems) {
            pointer1.leftTopX = pointer1.originX + pointer.leftTopX;
            pointer1.leftTopY = pointer1.originY + pointer.leftTopY;
        }
    }

    private void Onclick(float x, float y) {
        for (Pointer pointer1 : viewItems) {
            if (pointer1.checkRange(x, y)) {
                Toast.makeText(getContext(), "good", 1000).show();
                break;
            }
        }
    }

    private boolean matrixCheck() {
        float[] f = new float[9];
        matrix1.getValues(f);

        // 图片4个顶点的坐标
        calculateCoordinate();
        float x1 = f[0] * 0 + f[1] * 0 + f[2];
        float y1 = f[3] * 0 + f[4] * 0 + f[5];
        float x2 = f[0] * originalMap.getWidth() + f[1] * 0 + f[2];
        float y2 = f[3] * originalMap.getWidth() + f[4] * 0 + f[5];
        float x3 = f[0] * 0 + f[1] * originalMap.getHeight() + f[2];
        float y3 = f[3] * 0 + f[4] * originalMap.getHeight() + f[5];
        float x4 = f[0] * originalMap.getWidth() + f[1] * originalMap.getHeight() + f[2];
        float y4 = f[3] * originalMap.getWidth() + f[4] * originalMap.getHeight() + f[5];
        // 图片现宽度
        double width = Math.sqrt((pointer.leftTopX - x2) * (pointer.leftTopX - x2) + (y1 - y2) * (y1 - y2));
        // 缩放比率判断
        if (width < widthScreen / 3 || width > widthScreen * 3) {
            return true;
        }
        // 出界判断
        if ((x1 < widthScreen / 3 && x2 < widthScreen / 3
                && x3 < widthScreen / 3 && x4 < widthScreen / 3)
                || (x1 > widthScreen * 2 / 3 && x2 > widthScreen * 2 / 3
                && x3 > widthScreen * 2 / 3 && x4 > widthScreen * 2 / 3)
                || (y1 < heightScreen / 3 && y2 < heightScreen / 3
                && y3 < heightScreen / 3 && y4 < heightScreen / 3)
                || (y1 > heightScreen * 2 / 3 && y2 > heightScreen * 2 / 3
                && y3 > heightScreen * 2 / 3 && y4 > heightScreen * 2 / 3)) {
            return true;
        }
        return false;
    }

    private void calculateCoordinate() {
        float[] f = new float[9];
        matrix1.getValues(f);
        float x1 = f[0] * 0 + f[1] * 0 + f[2];
        float y1 = f[3] * 0 + f[4] * 0 + f[5];
        pointer.leftTopX = x1;
        pointer.leftTopY = y1;
        float x3 = f[0] * 0 + f[1] * originalMap.getHeight() + f[2];
        float y3 = f[3] * 0 + f[4] * originalMap.getHeight() + f[5];
        pointer.leftBottomX = x3;
        pointer.leftBottomY = y3;
    }

    // 触碰两点间距离
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    // 取手势中心点
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    // 取旋转角度
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    // 将移动，缩放以及旋转后的图层保存为新图片
    // 本例中沒有用到該方法，需要保存圖片的可以參考
    public Bitmap CreatNewPhoto() {
        Bitmap bitmap = Bitmap.createBitmap(widthScreen, heightScreen,
                Bitmap.Config.ARGB_8888); // 背景图片
        Canvas canvas = new Canvas(bitmap); // 新建画布
        canvas.drawBitmap(gintama, matrix, null); // 画图片
        canvas.save(Canvas.ALL_SAVE_FLAG); // 保存画布
        canvas.restore();
        return bitmap;
    }

    public Bitmap initMap() {
        Bitmap bitmap = Bitmap.createBitmap(widthScreen, heightScreen,
                Bitmap.Config.ARGB_8888); // 背景图片
        Canvas canvas = new Canvas(bitmap); // 新建画布
        canvas.drawBitmap(gintama, matrix, null); // 画图片
        canvas.save(Canvas.ALL_SAVE_FLAG); // 保存画布
        canvas.restore();

        canvas.drawBitmap(testBitMap, 100f, 100f, null); // 画图片
        canvas.save(Canvas.ALL_SAVE_FLAG); // 保存画布
        canvas.restore();

        return bitmap;
    }

    private class Pointer {

        public int originX;
        public int originY;

        public int width;
        public int height;
        //有效点击范围x起始坐标
        public float rangeXS;
        //        //有效点击范围x结束坐标
//        public float arrangeXE;
        //有效点击范围y起始坐标
        public float rangeYS;
//        //有效点击范围x结束坐标
//        public float arrangeYE;

        public float leftTopX;
        public float leftTopY;
        public float leftBottomX;
        public float leftBottomY;

        public boolean checkRange(float x, float y) {
            Log.i(TAG, "checkRange called.x=" + x + "y=" + y + ";leftTopX=" + leftTopX + ";leftTopY=" + leftTopY + ";rangeXS=" + (leftTopX + width) + ";rangeYS=" + (leftTopY + height));
            if (x >= leftTopX && y >= leftTopY && x <= leftTopX + width && y <= leftTopY + height)
                return true;
            return false;
        }
    }

}
