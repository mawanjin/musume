package com.join.musume.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.join.musume.R;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/15/13
 * Time: 7:53 PM
 */
public class ExhibitionPop extends PopupWindow {

    Context context;

    public ExhibitionPop(Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_exhibition, null);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(-00000);
        setBackgroundDrawable(dw);
    }

}
