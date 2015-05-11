package com.katmandu.katmandu;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.katmandu.katmandu.dataload.DataLoader;
import com.katmandu.katmandu.dataload.EquipmentFormLoader;
import com.katmandu.katmandu.dataload.FoodFormLoader;
import com.katmandu.katmandu.dataload.GeneralFormLoader;
import com.katmandu.katmandu.dataload.HealthFormLoader;
import com.katmandu.katmandu.dataload.OtherFormLoader;
import com.katmandu.katmandu.dataload.SecurityFormLoader;
import com.katmandu.katmandu.dataload.ShelterFormLoader;
import com.katmandu.katmandu.dataload.WaterFormLoader;

/**
 * Created by jonatan on 8/05/15.
 */
public class FragmentLoader {
    private FragmentLoader(){}

    static void load(int position, DataLoader loader, View rootView){
        initUiElements(position,rootView);
        loadData(position,loader,rootView);
    }

    private static void loadData(int position, DataLoader loader, View rootView) {
        switch (position){
            case TabbedFormActivity.TAB_GENERAL:
                new GeneralFormLoader(loader).load(rootView);
                break;
            case TabbedFormActivity.TAB_FOOD:
                new FoodFormLoader(loader).load(rootView);
                break;
            case TabbedFormActivity.TAB_HEALTH:
                new HealthFormLoader(loader).load(rootView);
                break;
            case TabbedFormActivity.TAB_SHELTER:
                new ShelterFormLoader(loader).load(rootView);
                break;
            case TabbedFormActivity.TAB_EQUIPMENT:
                new EquipmentFormLoader(loader).load(rootView);
                break;
            case TabbedFormActivity.TAB_WATER:
                new WaterFormLoader(loader).load(rootView);
                break;
            case TabbedFormActivity.TAB_SECURITY:
                new SecurityFormLoader(loader).load(rootView);
                break;
            case TabbedFormActivity.TAB_OTHERS:
                new OtherFormLoader(loader).load(rootView);
                break;
        }
    }

    private static void initUiElements(int position, View rootView) {
        switch (position){
            case TabbedFormActivity.TAB_GENERAL:
                initSpinner(rootView, R.id.status, R.array.status_array);
                break;
            case TabbedFormActivity.TAB_FOOD:
                initSpinner(rootView, R.id.foodSpinner, R.array.food_array);
                break;
            case TabbedFormActivity.TAB_HEALTH:
                initSpinner(rootView, R.id.moodSpinner, R.array.mood_array);
                break;
        }
    }

    private static void initSpinner(View rootView, int spinnerId, int stringArrayId) {
        final Spinner statusSpinner = (Spinner) rootView.findViewById(spinnerId);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(), stringArrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);
        statusSpinner.setSelection(0);
    }
}
