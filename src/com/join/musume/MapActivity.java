package com.join.musume;

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
import com.join.musume.listener.MapOnTouchListener;
import com.join.musume.view.DrawView;
import com.join.musume.view.TouchImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/16/13
 * Time: 5:20 PM
 */
public class MapActivity extends BaseActivity {

    String TAG = MapActivity.class.getName();
    FrameLayout container;
    FrameLayout picContainer;
    TouchImageView touchImageView;
    ImageView imgList;
    List<ImageView> images = new ArrayList<ImageView>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageFactory.getInstance(this).refresh();
        setContentView(R.layout.map);
        picContainer = (FrameLayout) findViewById(R.id.picContainer);
        container = (FrameLayout) findViewById(R.id.container);
        touchImageView = new TouchImageView(this);
        container.addView(touchImageView);
        initPic();
        initFooterView(1);

        imgList = (ImageView) findViewById(R.id.imgList);
        imgList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, ContentListActivity.class);
                startActivity(intent);
            }
        });
    }

    int i = 0;

    private void initPic() {
        picContainer.removeAllViews();

        for (Pointer pointer1 : ImageFactory.getInstance(this).getViewItems()) {

            final ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) pointer1.originWidth, (int) pointer1.originHeight);
            imageView.setLayoutParams(params);
            imageView.setImageBitmap(pointer1.bitmap);

            imageView.setId(i);
            imageView.setX(pointer1.leftTopX);
            imageView.setY(pointer1.leftTopY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MapActivity.this, ContentActivity.class);

                    switch (imageView.getId()) {
                        case 0:
                            intent.putExtra(ContentActivity.PARAM_TYPE, ContentActivity.PARAM_TYPE_DRAGON);
                            break;
                        case 1:
                            intent.putExtra(ContentActivity.PARAM_TYPE, ContentActivity.PARAM_TYPE_TREE);
                            break;
                        case 2:
                            intent.putExtra(ContentActivity.PARAM_TYPE, ContentActivity.PARAM_TYPE_GEO);
                            break;
                    }
                    startActivity(intent);
                }
            });
            picContainer.addView(imageView);
            images.add(imageView);
            i++;
        }


    }

    /**
     * 通过缩放级别显示图片
     */
    public void freshPic(Double width) {

        Log.i(TAG, "image width=" + width);
        double scaled = width - 800;
        int i = 0;
        //移动image
        for (ImageView imageView : images) {
            Pointer pointer = ImageFactory.getInstance(MapActivity.this).getViewItems().get(i);
            if (scaled > 0) {
                imageView.setAlpha(255);
                imageView.setClickable(true);
            } else {
                imageView.setClickable(false);
                if (scaled < -255) imageView.setAlpha(0);
                else
                    imageView.setAlpha((int) (255 + scaled));
            }
            slideview(imageView, pointer.leftTopX, pointer.leftTopY);
            i++;
            Log.i(TAG, "scroll to::X=" + pointer.leftTopX + ";Y=" + pointer.leftTopY);
        }
    }


    public void slideview(final View view, final float p1, final float p2) {
        Log.i(TAG, "slideview=" + view.getX() + ";" + view.getY());
        view.setX(p1);
        view.setY(p2);
        view.invalidate();
    }

}
