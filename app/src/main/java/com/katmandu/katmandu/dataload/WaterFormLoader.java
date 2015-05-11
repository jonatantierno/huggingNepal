package com.katmandu.katmandu.dataload;

import android.view.View;

import com.katmandu.katmandu.R;

import java.util.List;

public class WaterFormLoader implements FormLoader{
    private final DataLoader dataLoader;

    public WaterFormLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void load(View v) {
        dataLoader.loadCheckBox(v, "drinking_water", R.id.drinking_water);

        dataLoader.loadString(v, "water_source", R.id.water_source);

        dataLoader.loadCheckBox(v, "def_open", R.id.def_open);
        dataLoader.loadCheckBox(v, "soap", R.id.soap);
        dataLoader.loadCheckBox(v, "waste", R.id.waste);
    }
}