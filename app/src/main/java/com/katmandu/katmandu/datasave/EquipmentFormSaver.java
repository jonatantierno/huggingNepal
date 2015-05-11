package com.katmandu.katmandu.datasave;

import com.katmandu.katmandu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EquipmentFormSaver {
    private final DataSaver dataSaver;

    public EquipmentFormSaver(DataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void toMap(Map map) {

        dataSaver.saveInt("blankets", R.id.blankets, map);
        dataSaver.saveInt("boxes", R.id.boxes, map);
        dataSaver.saveInt("tarps", R.id.tarps, map);
        dataSaver.saveInt("flashlights", R.id.flashlights, map);
        dataSaver.saveInt("clothing_males", R.id.clothing_males, map);
        dataSaver.saveInt("clothing_females", R.id.clothing_females, map);
        dataSaver.saveInt("clothing_children", R.id.clothing_children, map);
        dataSaver.saveInt("cooking_fuel", R.id.cooking_fuel, map);
    }
}