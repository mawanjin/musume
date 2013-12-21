package com.join.musume.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.join.musume.QuestionActivity;
import com.join.musume.QuestionListActivity;
import com.join.musume.R;

import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/16/13
 * Time: 3:49 PM
 */
public class StudyAdatper extends BaseAdapter {
    Context context;
    List<Integer> imgs;

    public StudyAdatper(Context context, List<Integer> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        if (imgs == null) return 0;
        return imgs.size();
    }

    @Override
    public Object getItem(int i) {
        return imgs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.list_study, viewGroup, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        imageView.setImageDrawable(context.getResources().getDrawable(imgs.get(i)));
        ImageView pen = (ImageView) view.findViewById(R.id.pen);
        pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("which", 0);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
