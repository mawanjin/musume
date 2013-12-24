package com.join.musume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import oplayer.ui.VideoPlayerActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout subExhibitionContainer;
    LinearLayout subSettingContainer;
    ImageView reservation;
    ImageView intro;
    ImageView study;
    ImageView inquiry;
    ImageView setting;
    ImageView exhibMap;
    ImageView exhibition;
    ImageView nevigate;
    private boolean selected;
    private boolean settingSelected;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        subExhibitionContainer = (LinearLayout) findViewById(R.id.subExhibitionContainer);
        subSettingContainer = (LinearLayout) findViewById(R.id.subSettingContainer);
        reservation = (ImageView) findViewById(R.id.reservation);
        study = (ImageView) findViewById(R.id.study);
        intro = (ImageView) findViewById(R.id.intro);
        inquiry = (ImageView) findViewById(R.id.inquiry);
        setting = (ImageView) findViewById(R.id.setting);
        exhibMap = (ImageView) findViewById(R.id.exhibMap);
        exhibition = (ImageView) findViewById(R.id.exhibition);
        nevigate = (ImageView) findViewById(R.id.nevigate);
        exhibition.setOnClickListener(this);
        study.setOnClickListener(this);
//        reservation.setOnClickListener(this);
//        intro.setOnClickListener(this);
//        inquiry.setOnClickListener(this);
//        nevigate.setOnClickListener(this);
        exhibMap.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.exhibition:
                if (selected) {
                    exhibition.setImageDrawable(getResources().getDrawable(R.drawable.exhib_preview));
                    subExhibitionContainer.setVisibility(View.GONE);
                } else {
                    exhibition.setImageDrawable(getResources().getDrawable(R.drawable.exhib_preview_selected));
                    subExhibitionContainer.setVisibility(View.VISIBLE);

                    if (settingSelected) {
                        setting.setImageDrawable(getResources().getDrawable(R.drawable.setting));
                        subSettingContainer.setVisibility(View.GONE);
                    }
                }
                selected = !selected;
                break;
            case R.id.nevigate:
                intent.setClass(this, VideoPlayerActivity.class);
                startActivity(intent);
                break;
            case R.id.exhibMap:
                intent.setClass(this, MapMainActivity.class);
                startActivity(intent);
                break;
            case R.id.intro:
                intent.setClass(this, ContentActivity.class);
                startActivity(intent);
                break;
            case R.id.inquiry:
                intent.setClass(this, ContentActivity.class);
                intent.putExtra(ContentActivity.PARAM_TYPE, ContentActivity.PARAM_TYPE_TREE);
                startActivity(intent);
                break;
            case R.id.reservation:
                intent.setClass(this, ContentActivity.class);
                intent.putExtra(ContentActivity.PARAM_TYPE, ContentActivity.PARAM_TYPE_GEO);
                startActivity(intent);
                break;
            case R.id.study:
                intent.setClass(this, StudyListActivity.class);
                startActivity(intent);
                break;
            case R.id.setting:
                if (settingSelected) {
                    setting.setImageDrawable(getResources().getDrawable(R.drawable.setting));
                    subSettingContainer.setVisibility(View.GONE);
                } else {
                    setting.setImageDrawable(getResources().getDrawable(R.drawable.setting_selected));
                    subSettingContainer.setVisibility(View.VISIBLE);
                    if (selected) {
                        exhibition.setImageDrawable(getResources().getDrawable(R.drawable.exhib_preview));
                        subExhibitionContainer.setVisibility(View.GONE);
                    }
                }
                settingSelected = !settingSelected;
                break;
        }
    }
}
