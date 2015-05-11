package com.katmandu.katmandu.datasave;

import com.katmandu.katmandu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodFormSaver {
    private final DataSaver dataSaver;

    public FoodFormSaver(DataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void toMap(Map map) {
        dataSaver.saveSpinner("food_last", R.id.foodSpinner, map);
        dataSaver.saveCheckBox("food_locally",R.id.food_locally, map);
        dataSaver.saveCheckBox("main_road",R.id.main_road, map);
        dataSaver.saveCheckBox("cooking_stoves",R.id.cooking_stoves, map);
    }
}