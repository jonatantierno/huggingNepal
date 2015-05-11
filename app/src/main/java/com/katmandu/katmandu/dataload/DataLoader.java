package com.katmandu.katmandu.dataload;

import android.view.View;

import java.util.List;
import java.util.Map;

/**
 * Created by jonatan on 8/05/15.
 */
public interface DataLoader {

        void loadCheckBoxFromArray(View v, List<String> fields, String option, int id);

        void loadCheckBox(View v, String option, int id);

        void loadDouble(View v, String key, int id);

        void loadSpinner(View v, String key, int id);

        void loadString(View v, String key, int id);

        Map getMap(String key);

        List<String> getStringList(String transport);

}
