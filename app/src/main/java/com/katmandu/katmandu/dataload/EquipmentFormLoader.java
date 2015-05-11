package com.katmandu.katmandu.dataload;

import android.view.View;

import com.katmandu.katmandu.R;

import java.util.List;

public class EquipmentFormLoader implements FormLoader{
    private final DataLoader dataLoader;

    public EquipmentFormLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void load(View v) {

        dataLoader.loadString(v, "blankets", R.id.blankets);
        dataLoader.loadString(v, "boxes", R.id.boxes);
        dataLoader.loadString(v, "tarps", R.id.tarps);
        dataLoader.loadString(v, "flashlights", R.id.flashlights);
        dataLoader.loadString(v, "clothing_males", R.id.clothing_males);
        dataLoader.loadString(v, "clothing_females", R.id.clothing_females);
        dataLoader.loadString(v, "clothing_children", R.id.clothing_children);
        dataLoader.loadString(v, "cooking_fuel", R.id.cooking_fuel);
    }
}