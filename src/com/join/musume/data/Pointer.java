package com.join.musume.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import com.join.musume.R;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/19/13
 * Time: 8:59 PM
 */
public class Pointer {
    private Context context;
    String TAG = Pointer.class.getName();

    public Bitmap bitmap;
    public int id;

    public float immutableX;
    public float immutableY;

    public float originX;
    public float originY;

    public float originWidth;
    public float originHeight;
    public float width;
    public float height;
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
    public float scaledX;
    public float scaledY;

    public Pointer(Context context){
        this.context = context;
    }

    public Bitmap getBitmap(float scale){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = (int)(bitmap.getWidth()*scale);
        options.outHeight = (int)(bitmap.getHeight()*scale);

//        options.inSampleSize = ((int)width/options.outWidth);
        Matrix matrix = new  Matrix();
        matrix.postScale(scale,scale);
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0 , 0 ,bitmap.getWidth(),
                bitmap.getHeight(),matrix,true );
//        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), id,options);
//        Bitmap dstbmp = Bitmap.createBitmap(bmp,0,0,(int)(bmp.getWidth()*scale),(int)(bmp.getHeight()*scale));
//        Bitmap dstbmp=Bitmap.createBitmap(bmp,0,0,bmp.getWidth(), bmp.getHeight(),null,true);
        return resizeBmp;
    }

    public boolean checkRange(float x, float y) {
        Log.i(TAG, "checkRange called.x=" + x + "y=" + y + ";leftTopX=" + leftTopX + ";leftTopY=" + leftTopY + ";rangeXS=" + (leftTopX + width) + ";rangeYS=" + (leftTopY + height));
//        if (x >= leftTopX && y >= leftTopY && x <= leftTopX + width && y <= leftTopY + height)
            if (x >= immutableX && y >= immutableY && x <= immutableX + width && y <= immutableY + height)
            return true;
        return false;
    }

}
