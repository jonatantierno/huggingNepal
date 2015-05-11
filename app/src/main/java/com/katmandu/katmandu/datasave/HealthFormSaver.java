package com.katmandu.katmandu.datasave;

import com.katmandu.katmandu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HealthFormSaver {
    private final DataSaver dataSaver;

    public HealthFormSaver(DataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void toMap(Map map) {

        List<String> healthIssues = new ArrayList<String>();
        dataSaver.saveCheckBoxToArray(healthIssues, "diarrhea", R.id.diarrhea, map);
        dataSaver.saveCheckBoxToArray(healthIssues, "cholera", R.id.cholera, map);
        dataSaver.saveCheckBoxToArray(healthIssues, "cough", R.id.cough, map);
        dataSaver.saveCheckBoxToArray(healthIssues, "vomiting", R.id.vomiting, map);
        dataSaver.saveCheckBoxToArray(healthIssues, "injury_head", R.id.injury_head, map);
        dataSaver.saveCheckBoxToArray(healthIssues, "injury_back", R.id.injury_back, map);
        dataSaver.saveCheckBoxToArray(healthIssues, "injury_broken_bones", R.id.injury_broken_bones, map);
        dataSaver.saveCheckBoxToArray(healthIssues, "injury_other", R.id.injury_other, map);
        dataSaver.saveCheckBoxToArray(healthIssues, "milk_shortage", R.id.milk_shortage, map);

        map.put("health_issues", healthIssues);

        dataSaver.saveString("health_conditions", R.id.health_conditions_other, map);

        dataSaver.saveCheckBox("health_worker",R.id.health_worker, map);
        dataSaver.saveCheckBox("med_assistance",R.id.med_assistance, map);

        dataSaver.saveString("med_agengy", R.id.med_agency, map);

        dataSaver.saveCheckBox("psy_distress",R.id.psy_distress, map);

        dataSaver.saveSpinner("mood", R.id.moodSpinner, map);

    }
}