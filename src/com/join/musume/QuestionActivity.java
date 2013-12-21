package com.join.musume;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/20/13
 * Time: 10:06 AM
 */
public class QuestionActivity extends BaseActivity implements View.OnClickListener {
    List<Drawable> imageViews = new ArrayList<Drawable>(0);

    LinearLayout option1;
    LinearLayout option2;
    ImageView a;
    ImageView b;
    ImageView c;
    ImageView check;
    ImageView wrong;
    ImageView question;
    ImageView next;

    boolean checkSelected;
    boolean wrongSelected;
    int which;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        which = getIntent().getIntExtra("which", 0);

        option1 = (LinearLayout) findViewById(R.id.option1);
        option2 = (LinearLayout) findViewById(R.id.option2);
        a = (ImageView) findViewById(R.id.a);
        b = (ImageView) findViewById(R.id.b);
        c = (ImageView) findViewById(R.id.c);
        next = (ImageView) findViewById(R.id.next);
        question = (ImageView) findViewById(R.id.question);
        check = (ImageView) findViewById(R.id.check);
        wrong = (ImageView) findViewById(R.id.wrong);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        next.setOnClickListener(this);
        check.setOnClickListener(this);
        wrong.setOnClickListener(this);

        imageViews.add(getResources().getDrawable(R.drawable.s_question1));
        imageViews.add(getResources().getDrawable(R.drawable.s_question2));
        imageViews.add(getResources().getDrawable(R.drawable.s_question3));

        question.setImageDrawable(imageViews.get(which));

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.check:
                if (!checkSelected) {
                    check.setImageDrawable(getResources().getDrawable(R.drawable.check_selected));
                    wrong.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                    wrongSelected = false;
                    checkSelected = true;
                }
                break;
            case R.id.wrong:
                if (!wrongSelected) {
                    wrong.setImageDrawable(getResources().getDrawable(R.drawable.wrong_selected));
                    check.setImageDrawable(getResources().getDrawable(R.drawable.check));
                    wrongSelected = true;
                    checkSelected = false;
                }
                break;
            case R.id.next:
                which++;
                if (which < imageViews.size())
                    reloadView(which);
                break;
            case R.id.a:
                a.setImageDrawable(getResources().getDrawable(R.drawable.a_selected));
                b.setImageDrawable(getResources().getDrawable(R.drawable.b));
                c.setImageDrawable(getResources().getDrawable(R.drawable.c));
                break;
            case R.id.b:
                b.setImageDrawable(getResources().getDrawable(R.drawable.b_selected));
                a.setImageDrawable(getResources().getDrawable(R.drawable.a));
                c.setImageDrawable(getResources().getDrawable(R.drawable.c));
                break;
            case R.id.c:
                c.setImageDrawable(getResources().getDrawable(R.drawable.c_selected));
                b.setImageDrawable(getResources().getDrawable(R.drawable.b));
                a.setImageDrawable(getResources().getDrawable(R.drawable.a));
                break;
        }
    }

    private void reloadView(int i) {
        question.setImageDrawable(imageViews.get(which));
        check.setImageDrawable(getResources().getDrawable(R.drawable.check));
        wrong.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
        switch (i) {
            case 0:
                option1.setVisibility(View.VISIBLE);
                option2.setVisibility(View.GONE);
                break;
            case 1:
                option2.setVisibility(View.VISIBLE);
                option1.setVisibility(View.GONE);
                break;
            default:
                option1.setVisibility(View.GONE);
                option2.setVisibility(View.GONE);
                break;
        }
    }


}
