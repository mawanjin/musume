package com.join.musume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.join.musume.popwindow.ExhibitionPop;
import com.join.musume.popwindow.MapRoadPop;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/15/13
 * Time: 7:47 PM
 */
public class BaseActivity extends Activity implements View.OnClickListener {

    PopupWindow pop;
    ImageView footerMore;
    LinearLayout footerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitiesManager.getInstance().registerActivity(this.getClass().getName(),this);
    }

    public void initFooterView(int type) {
        footerContainer = (LinearLayout) findViewById(R.id.footerContainer);
        footerMore = (ImageView) findViewById(R.id.footerMore);
        footerMore.setOnClickListener(this);
        if (type == 0)
            pop = new ExhibitionPop(this);
        else if (type == 1)
            pop = new MapRoadPop(this);

        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(this);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.footerMore:
                showMore();

                break;
            case R.id.home:
                ActivitiesManager.getInstance().finishAll();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.back:
                finish();
                break;
        }

    }

    public void showMore() {

        if (pop.isShowing()) {
            pop.dismiss();
            footerMore.setImageDrawable(getResources().getDrawable(R.drawable.footer_more_normal));
        } else {
            pop.showAsDropDown(footerMore, 0, 20 - (footerContainer.getMeasuredHeight() + footerMore.getMeasuredHeight() * 2));
            footerMore.setImageDrawable(getResources().getDrawable(R.drawable.footer_more_selcted));
        }
    }
}
