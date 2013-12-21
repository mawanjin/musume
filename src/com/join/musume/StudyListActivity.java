package com.join.musume;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.join.musume.adapter.ContentAdatper;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/20/13
 * Time: 9:23 AM
 */
public class StudyListActivity extends BaseActivity {

    ListView listView;
    ContentAdatper mAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_list);
        listView = (ListView) findViewById(R.id.list);
        mAdatper = new ContentAdatper(this, getImages());
        listView.setAdapter(mAdatper);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(StudyListActivity.this, QuestionListActivity.class);
                startActivity(intent);
            }
        });
        initFooterView(0);
    }

    public List<Integer> getImages() {
        List<Integer> images = new ArrayList<Integer>(0);
        images.add(R.drawable.study1);
        images.add(R.drawable.study2);
        images.add(R.drawable.study3);
        images.add(R.drawable.study4);
        images.add(R.drawable.study5);
        images.add(R.drawable.study6);
        return images;
    }
}
