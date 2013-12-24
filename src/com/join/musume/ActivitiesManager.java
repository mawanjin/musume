package com.join.musume;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import java.util.HashMap;
import java.util.Map;


public class ActivitiesManager {
    private static ActivitiesManager instance;
    private Map<String, Activity> activities = new HashMap<String, Activity>();

    public static ActivitiesManager getInstance() {
        if (instance == null)
            instance = new ActivitiesManager();
        return instance;
    }

    private ActivitiesManager() {
    }

    public void finishAll() {
        for (Map.Entry<String, Activity> entry : activities.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isFinishing()) {
                entry.getValue().finish();
            }
        }
    }

    public Activity getActivity(String name) {
        return activities.get(name);
    }

    public void registerActivity(String name, Activity a) {
        a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止翻转
        activities.put(name, a);
    }

    public void unRegisterActivity(String name, Activity a) {
        activities.remove(name);
    }
}
