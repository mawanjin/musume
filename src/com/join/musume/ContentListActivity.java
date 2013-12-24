package com.join.musume;

import android.os.Bundle;
import android.widget.ListView;
import com.join.musume.adapter.ContentAdatper;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/16/13
 * Time: 3:48 PM
 */
public class ContentListActivity extends BaseActivity {

    ListView listView;
    ContentAdatper mAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list);
        listView = (ListView) findViewById(R.id.list);
        mAdatper = new ContentAdatper(this, getImages());
        listView.setAdapter(mAdatper);
        initFooterView(0);
    }

    public List<Integer> getImages() {
        List<Integer> images = new ArrayList<Integer>(0);
        images.add(R.drawable.list1);
        images.add(R.drawable.list2);
        images.add(R.drawable.list3);
        images.add(R.drawable.list4);
        images.add(R.drawable.list5);
        images.add(R.drawable.list6);
        return images;
    }
}
