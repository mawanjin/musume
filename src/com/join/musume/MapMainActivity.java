package com.join.musume;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/16/13
 * Time: 4:48 PM
 */
public class MapMainActivity extends BaseActivity implements View.OnClickListener {

    ImageView sFloor;
    ImageView imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);
        initFooterView(1);
        imgList = (ImageView) findViewById(R.id.imgList);
        sFloor = (ImageView) findViewById(R.id.sFloor);
        sFloor.setOnClickListener(this);
        imgList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent intent;
        switch (view.getId()) {
            case R.id.sFloor:
//                intent = new Intent(this, MapActivity.class);
                intent = new Intent(this, TouchImageViewActivity.class);
                startActivity(intent);
                break;
            case R.id.imgList:
                intent = new Intent(this, ContentListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
