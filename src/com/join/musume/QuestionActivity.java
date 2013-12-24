package com.join.musume;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/20/13
 * Time: 10:06 AM
 */
public class QuestionActivity extends BaseActivity implements View.OnClickListener {
    List<Drawable> imageViews = new ArrayList<Drawable>(0);

    LinearLayout keyborderContainer;
    LinearLayout option1;
    LinearLayout option2;
    ImageView a;
    ImageView b;
    ImageView c;
    ImageView check;
    ImageView wrong;
    ImageView question;
    ImageView next;
    EditText keyborder;

    boolean checkSelected;
    boolean wrongSelected;
    int which;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        which = getIntent().getIntExtra("which", 0);

        keyborder = (EditText) findViewById(R.id.keyborder);
        keyborderContainer = (LinearLayout) findViewById(R.id.keyborderContainer);
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
        initFooterView(0);

//        hideSoftkeyBorder();

    }

    private void hideSoftkeyBorder() {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(keyborder.getWindowToken(), 0);
        if (android.os.Build.VERSION.SDK_INT <= 10) {

            keyborder.setInputType(InputType.TYPE_NULL);
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(keyborder, true);
                keyborder.setSelected(true);
                keyborder.setCursorVisible(true);
            } catch (Exception e) {

            }
        }

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
                if (which > 2) {
                    Intent intent = new Intent(this, QuestionListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(intent);
                } else {

                }

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
                keyborderContainer.setVisibility(View.GONE);
                option1.setVisibility(View.VISIBLE);
                option2.setVisibility(View.GONE);
                break;
            case 1:
                keyborderContainer.setVisibility(View.GONE);
                option2.setVisibility(View.VISIBLE);
                option1.setVisibility(View.GONE);
                break;
            default:
                keyborderContainer.setVisibility(View.VISIBLE);
                option1.setVisibility(View.GONE);
                option2.setVisibility(View.GONE);
//                hideSoftkeyBorder();
                keyborder.setCursorVisible(true);
                keyborder.setFocusable(true);
                keyborder.performClick();
                next.setImageDrawable(getResources().getDrawable(R.drawable.submit));
                break;
        }
    }


}
