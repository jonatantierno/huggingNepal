package com.katmandu.katmandu.dataload;

import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.katmandu.katmandu.Utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonatan on 6/05/15.
 */
public class MapLoader implements DataLoader {
    public static DataLoader NULL = new NullLoader();

    private final Map<String, Object> map;

    MapLoader(Map map) {
        this.map = map;
    }

    public void loadCheckBoxFromArray(View v, List<String> fields, String option, int id) {
        ((CheckBox)v.findViewById(id)).setChecked(fields.contains(option));
    }

    @Override
    public void loadCheckBox(View v, String key, int id) {
        ((CheckBox)v.findViewById(id)).setChecked(Utl.getBoolean(map, key));
    }

    public void loadDouble(View v, String key, int id) {
        ((TextView)v.findViewById(id)).setText(Utl.getString(map, key));
    }

    public void loadSpinner(View v, String key, int id) {
        Spinner spinner = (Spinner)v.findViewById(id);
        spinner.setSelection(Utl.getInt(map, key, 0));
    }

    @Override
    public void loadString(View v, String key, int id) {
        ((TextView)v.findViewById(id)).setText(Utl.getString(map, key));
    }

    @Override
    public Map getMap(String key) {
        Object o = map.get(key);
        if (o instanceof Map) return (Map) o;
        return new HashMap();
    }

    @Override
    public List<String> getStringList(String transport) {
        return Utl.getStringList(map, "transport");
    }

    public static DataLoader build(Map map) {
        if (map.isEmpty()) return NULL;
        return new MapLoader(map);
    }

    private static class NullLoader implements DataLoader {

        @Override
        public void loadCheckBoxFromArray(View v, List<String> fields, String option, int id) {}

        @Override
        public void loadCheckBox(View v, String option, int id) { }

        @Override
        public void loadDouble(View v, String key, int id) { }

        @Override
        public void loadSpinner(View v, String key, int id) { }

        @Override
        public void loadString(View v, String key, int id) { }

        @Override
        public Map getMap(String key) {
            return new HashMap();
        }

        @Override
        public List<String> getStringList(String transport) {
            return new ArrayList<>();
        }

    }


}
