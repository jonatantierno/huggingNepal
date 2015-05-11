package com.katmandu.katmandu.datasave;

import com.katmandu.katmandu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShelterFormSaver {
    private final DataSaver dataSaver;

    public ShelterFormSaver(DataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void toMap(Map map) {

        dataSaver.saveInt("shelter_camps", R.id.shelter_camps, map);
        dataSaver.saveInt("shelter_undamaged", R.id.shelter_undamaged, map);

        dataSaver.saveCheckBox("health_worker", R.id.rebuilding, map);

        dataSaver.saveInt("shelter_none", R.id.shelter_none, map);

        List<String> type = new ArrayList<>();
        dataSaver.saveCheckBoxToArray(type, "plastic", R.id.plastic, map);
        dataSaver.saveCheckBoxToArray(type, "tents", R.id.tents, map);
        dataSaver.saveCheckBoxToArray(type, "tan_roof", R.id.tan_roof, map);
        dataSaver.saveCheckBoxToArray(type, "houses", R.id.houses, map);
        dataSaver.saveCheckBoxToArray(type, "other", R.id.type_other, map);

        map.put("shelter_type", type);

        List<String> materials = new ArrayList<>();
        dataSaver.saveCheckBoxToArray(materials, "plastic", R.id.requested_plastic, map);
        dataSaver.saveCheckBoxToArray(materials, "cardboard", R.id.requested_cardboard, map);
        dataSaver.saveCheckBoxToArray(materials, "rope", R.id.requested_rope, map);
        dataSaver.saveCheckBoxToArray(materials, "bamboo", R.id.requested_bamboo, map);
        dataSaver.saveCheckBoxToArray(materials, "other", R.id.requested_other, map);
    }
}