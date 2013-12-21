package com.join.musume;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.*;
import oplayer.ui.VideoPlayerActivity;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/15/13
 * Time: 4:00 PM
 */
public class ContentActivity extends BaseActivity implements
        AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory, View.OnClickListener {
    public static String PARAM_TYPE = "type";
    public final static int PARAM_TYPE_DRAGON = 0;
    public final static int PARAM_TYPE_GEO = 1;
    public final static int PARAM_TYPE_TREE = 2;
    private static int type;

    private RelativeLayout imageContainer;
    private LinearLayout locationMark;
    private TextView title;
    private ImageView imgList;
    private ImageView imgTxt;
    private ImageView arrowLeft;
    private ImageView arrowRight;
    private ImageView play;
    private int index = 0;
    private int max = 3;
    private int videoIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        type = getIntent().getIntExtra(PARAM_TYPE, 0);

        imageContainer = (RelativeLayout) findViewById(R.id.imageContainer);
        locationMark = (LinearLayout) findViewById(R.id.location_mark);
        title = (TextView) findViewById(R.id.title);
        play = (ImageView) findViewById(R.id.play);
        imgList = (ImageView) findViewById(R.id.imgList);
        imgTxt = (ImageView) findViewById(R.id.imgTxt);
        arrowLeft = (ImageView) findViewById(R.id.arrowLeft);
        arrowRight = (ImageView) findViewById(R.id.arrowRight);
        mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
        init();
        mSwitcher.setFactory(this);
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));
//        mSwitcher.setImageResource(mImageIds[index]);
        mSwitcher.setImageResource(getId(index));
        arrowLeft.setOnClickListener(this);
        arrowRight.setOnClickListener(this);
        //生成位置标识
        initMarkView(index, max);
        initFooterView(0);
        play.setOnClickListener(this);
        imgList.setOnClickListener(this);
    }

    /**
     * @param index 当前第几个
     * @param max   总共多少个
     */
    private void initMarkView(int index, int max) {
        locationMark.removeAllViews();
        for (int i = 0; i < max; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setPadding(10, 0, 0, 0);
            if (i == index) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.square_solid));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.square_blank));
            }
            locationMark.addView(imageView);
        }

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        mSwitcher.setImageResource(getId(position));
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public View makeView() {
        ImageView i = new ImageView(this);
        i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_XY);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        return i;
    }

    private ImageSwitcher mSwitcher;

    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent intent = null;
        switch (view.getId()) {
            case R.id.arrowLeft:
                change(0);
                break;
            case R.id.arrowRight:
                change(1);
                break;
            case R.id.play:
                intent = new Intent(this, VideoPlayerActivity.class);
                startActivity(intent);
                break;
            case R.id.imgList:
                intent = new Intent(this, ContentListActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * @param type 0 左 1右
     */
    private void change(int type) {

        if (type == 0) {
            index--;
        } else
            index++;
        if (index < 0) index = 0;
        if (index > max - 1) index = max - 1;
        mSwitcher.setImageResource(getId(index));
        initMarkView(index, max);

        if (index == 0) {
            arrowLeft.setImageDrawable(getResources().getDrawable(R.drawable.arrow_blank));
            if (max > 0) arrowRight.setImageDrawable(getResources().getDrawable(R.drawable.arrow_solid));

        } else if (index == max - 1) {
            arrowRight.setImageDrawable(getResources().getDrawable(R.drawable.arrow_blank_right));
            arrowLeft.setImageDrawable(getResources().getDrawable(R.drawable.arrow_solid_left));
        } else {
            arrowLeft.setImageDrawable(getResources().getDrawable(R.drawable.arrow_solid_left));
            arrowRight.setImageDrawable(getResources().getDrawable(R.drawable.arrow_solid));
        }

        if (videoIndex == index) play.setVisibility(View.VISIBLE);
        else play.setVisibility(View.INVISIBLE);

    }

    public void init() {
        switch (type) {
            case PARAM_TYPE_DRAGON:
                max = mImageIds.length;
                videoIndex = 1;
                break;
            case PARAM_TYPE_GEO:
                max = mImageIdsGeo.length;
                videoIndex = 1;
                imgTxt.setImageDrawable(getResources().getDrawable(R.drawable.geo_txt));
                title.setText(getString(R.string.geo));
                break;
            case PARAM_TYPE_TREE:
                max = mImageIdsTree.length;
                videoIndex = 1;
                imgTxt.setImageDrawable(getResources().getDrawable(R.drawable.tree_txt));
                title.setText(getString(R.string.tree));
                break;
            default:
                max = mImageIds.length;
                videoIndex = 1;
                break;
        }
    }

    private int getId(int i) {
        switch (type) {
            case PARAM_TYPE_DRAGON:
                return mImageIds[i];
            case PARAM_TYPE_GEO:
                return mImageIdsGeo[i];
            case PARAM_TYPE_TREE:
                return mImageIdsTree[i];
            default:
                return mImageIds[i];
        }
    }

    //恐龙
    private Integer[] mImageIds = {
            R.drawable.dragon_pic, R.drawable.dragon_pic_video, R.drawable.dragon_pic1};
    //地理
    private Integer[] mImageIdsGeo = {
            R.drawable.pic2_1, R.drawable.pic2_2, R.drawable.pic2_3};
    //树
    private Integer[] mImageIdsTree = {
            R.drawable.tree_1, R.drawable.tree_2, R.drawable.tree3};


}
