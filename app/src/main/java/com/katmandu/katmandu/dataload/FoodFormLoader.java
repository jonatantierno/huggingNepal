package com.katmandu.katmandu.dataload;

import android.view.View;

import com.katmandu.katmandu.R;

import java.util.List;

public class FoodFormLoader implements FormLoader{
    private final DataLoader dataLoader;

    public FoodFormLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void load(View v) {

        dataLoader.loadSpinner(v, "food_last", R.id.foodSpinner);
        dataLoader.loadCheckBox(v, "food_locally", R.id.food_locally);
        dataLoader.loadCheckBox(v, "main_road", R.id.main_road);
        dataLoader.loadCheckBox(v, "cooking_stoves", R.id.cooking_stoves);
    }
}