package com.katmandu.katmandu.datasave;

import com.katmandu.katmandu.R;

import java.util.Map;

public class OtherFormSaver {
    private final DataSaver dataSaver;

    public OtherFormSaver(DataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void toMap(Map map) {
        dataSaver.saveCheckBox("school",R.id.school, map);
        dataSaver.saveCheckBox("school_damaged",R.id.school_damaged, map);
        dataSaver.saveString("comments", R.id.comments, map);
    }
}