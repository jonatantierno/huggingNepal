package com.katmandu.katmandu.datasave;

import android.app.Activity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.katmandu.katmandu.TabbedFormActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonatan on 6/05/15.
 */
public class DataSaver {
    private final Activity activity;

    public DataSaver(Activity activity) {
        this.activity = activity;
    }

    public void toMap(Map map, int position) {
        switch(position){
            case TabbedFormActivity.TAB_GENERAL:
                new GeneralFormSaver(this).toMap(map);
                break;
            case TabbedFormActivity.TAB_FOOD:
                new FoodFormSaver(this).toMap(map);
                break;
            case TabbedFormActivity.TAB_HEALTH:
                new HealthFormSaver(this).toMap(map);
                break;
            case TabbedFormActivity.TAB_SHELTER:
                new ShelterFormSaver(this).toMap(map);
                break;
            case TabbedFormActivity.TAB_EQUIPMENT:
                new EquipmentFormSaver(this).toMap(map);
                break;
            case TabbedFormActivity.TAB_WATER:
                new WaterFormSaver(this).toMap(map);
                break;
            case TabbedFormActivity.TAB_SECURITY:
                new SecurityFormSaver(this).toMap(map);
                break;
            case TabbedFormActivity.TAB_OTHERS:
                new OtherFormSaver(this).toMap(map);
                break;
        }
    }

    void saveCheckBoxToArray(List<String> destination, String value, int id, Map map) {
        CheckBox checkBox = (CheckBox)activity.findViewById(id);
        if(checkBox.isChecked()){
            destination.add(value);
        }
    }
    void saveCheckBox(String key, int id, Map map) {
        CheckBox checkBox = (CheckBox)activity.findViewById(id);
        map.put(key,checkBox.isChecked());
    }

    void saveSpinner(String key, int id, Map map) {
        Spinner spinner = (Spinner)activity.findViewById(id);
        map.put(key,spinner.getSelectedItemPosition());
    }

    void saveDouble(String key, int id, Map map) {
        String value = ((TextView)activity.findViewById(id)).getText().toString();
        try {
            map.put(key, Double.parseDouble(value));
        } catch (NumberFormatException e){
            // Do nothing
            Log.d("Saving to map", key + " does not have a valid value");
        }
    }

    void saveString(String key, int id, Map map) {
        String value = ((TextView)activity.findViewById(id)).getText().toString();
        map.put(key,value);
    }

    public void saveInt(String key, int id, Map map) {
        String valueAsString = ((TextView)activity.findViewById(id)).getText().toString();
        try {
            map.put(key, Integer.parseInt(valueAsString));
        } catch (NumberFormatException e){
            // Do nothing
            Log.d("Saving to map", key + " does not have a valid value");
        }
    }
}
