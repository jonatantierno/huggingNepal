package com.katmandu.katmandu.datasave;

import com.katmandu.katmandu.R;

import java.util.Map;

public class SecurityFormSaver {
    private final DataSaver dataSaver;

    public SecurityFormSaver(DataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void toMap(Map map) {
        dataSaver.saveCheckBox("cash", R.id.cash, map);
        dataSaver.saveCheckBox("sharing",R.id.sharing, map);
        dataSaver.saveCheckBox("conflicts",R.id.conflicts, map);
        dataSaver.saveCheckBox("other_help_before",R.id.other_help_before, map);
        dataSaver.saveCheckBox("volunteers_aggression",R.id.volunteers_aggression, map);
        dataSaver.saveCheckBox("other_help_expected",R.id.other_help_expected, map);
    }
}