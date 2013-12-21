package com.join.musume;

import android.app.Activity;
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

    }

    public void initFooterView(int type) {
        footerContainer = (LinearLayout) findViewById(R.id.footerContainer);
        footerMore = (ImageView) findViewById(R.id.footerMore);
        footerMore.setOnClickListener(this);
        if(type==0)
            pop = new ExhibitionPop(this);
        else if(type==1)
            pop = new MapRoadPop(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.footerMore:
                if (pop.isShowing()){
                    pop.dismiss();
                    footerMore.setImageDrawable(getResources().getDrawable(R.drawable.footer_more_normal));
                }
                else{

                    pop.showAsDropDown(footerMore,0,-(footerContainer.getMeasuredHeight()+footerMore.getMeasuredHeight()));
                    footerMore.setImageDrawable(getResources().getDrawable(R.drawable.footer_more_selcted));
                }

                break;
        }

    }
}
