package com.katmandu.katmandu.datasave;

import com.katmandu.katmandu.R;

import java.util.Map;

public class WaterFormSaver {
    private final DataSaver dataSaver;

    public WaterFormSaver(DataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void toMap(Map map) {

        dataSaver.saveCheckBox("drinking_water", R.id.drinking_water, map);

        dataSaver.saveString("water_source", R.id.water_source, map);

        dataSaver.saveCheckBox("def_open", R.id.def_open, map);
        dataSaver.saveCheckBox("soap", R.id.soap, map);
        dataSaver.saveCheckBox("waste", R.id.waste, map);
    }
}