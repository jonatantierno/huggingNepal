package com.katmandu.katmandu.dataload;

import android.view.View;

import com.katmandu.katmandu.R;

import java.util.List;

public class ShelterFormLoader implements FormLoader{
    private final DataLoader dataLoader;

    public ShelterFormLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void load(View v) {

        dataLoader.loadString(v, "shelter_camps", R.id.shelter_camps);
        dataLoader.loadString(v, "shelter_undamaged", R.id.shelter_undamaged);
        dataLoader.loadCheckBox(v, "rebuilding", R.id.rebuilding);
        dataLoader.loadString(v, "shelter_none", R.id.shelter_none);

        List<String> type = dataLoader.getStringList("shelter_type");
        dataLoader.loadCheckBoxFromArray(v, type, "plastic", R.id.plastic);
        dataLoader.loadCheckBoxFromArray(v, type, "tents", R.id.tents);
        dataLoader.loadCheckBoxFromArray(v, type, "tan_roof", R.id.tan_roof);
        dataLoader.loadCheckBoxFromArray(v, type, "houses", R.id.houses);
        dataLoader.loadCheckBoxFromArray(v, type, "other", R.id.type_other);

        List<String> materials = dataLoader.getStringList("materials_requested");
        dataLoader.loadCheckBoxFromArray(v, materials, "plastic", R.id.requested_plastic);
        dataLoader.loadCheckBoxFromArray(v, materials, "cardboard", R.id.requested_cardboard);
        dataLoader.loadCheckBoxFromArray(v, materials, "rope", R.id.requested_rope);
        dataLoader.loadCheckBoxFromArray(v, materials, "bamboo", R.id.requested_bamboo);
        dataLoader.loadCheckBoxFromArray(v, materials, "other", R.id.requested_other);
    }
}