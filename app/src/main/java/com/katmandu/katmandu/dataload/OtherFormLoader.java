package com.katmandu.katmandu.dataload;

import android.view.View;

import com.katmandu.katmandu.R;

public class OtherFormLoader implements FormLoader{
    private final DataLoader dataLoader;

    public OtherFormLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void load(View v) {

        dataLoader.loadCheckBox(v, "school", R.id.school);
        dataLoader.loadCheckBox(v, "school_damaged", R.id.school_damaged);
        dataLoader.loadString(v, "comments", R.id.comments);
    }
}