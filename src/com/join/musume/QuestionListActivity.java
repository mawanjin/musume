package com.join.musume;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.join.musume.adapter.ContentAdatper;
import com.join.musume.adapter.StudyAdatper;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/20/13
 * Time: 9:38 AM
 */
public class QuestionListActivity extends BaseActivity {
    ListView listView;
    StudyAdatper mAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_list);
        listView = (ListView) findViewById(R.id.list);
        mAdatper = new StudyAdatper(this, getImages());
        listView.setAdapter(mAdatper);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(QuestionListActivity.this, QuestionActivity.class);
//                intent.putExtra("which", 0);
//                startActivity(intent);
            }
        });
        initFooterView(0);
    }

    public List<Integer> getImages() {
        List<Integer> images = new ArrayList<Integer>(0);
        images.add(R.drawable.question1);
        images.add(R.drawable.question2);
        images.add(R.drawable.question3);
        return images;
    }
}
