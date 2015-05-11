package com.katmandu.katmandu.dataload;

import android.view.View;

import com.katmandu.katmandu.R;

import java.util.List;

public class SecurityFormLoader implements FormLoader{
    private final DataLoader dataLoader;

    public SecurityFormLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void load(View v) {

        dataLoader.loadCheckBox(v, "cash", R.id.cash);
        dataLoader.loadCheckBox(v, "sharing", R.id.sharing);
        dataLoader.loadCheckBox(v, "conflicts", R.id.conflicts);
        dataLoader.loadCheckBox(v, "other_help_before", R.id.other_help_before);
        dataLoader.loadCheckBox(v, "volunteers_aggression", R.id.volunteers_aggression);
        dataLoader.loadCheckBox(v, "other_help_expected", R.id.other_help_expected);
    }
}