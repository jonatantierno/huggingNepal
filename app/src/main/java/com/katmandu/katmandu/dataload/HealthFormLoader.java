package com.katmandu.katmandu.dataload;

import android.view.View;

import com.katmandu.katmandu.R;

import java.util.List;

public class HealthFormLoader implements FormLoader{
    private final DataLoader dataLoader;

    public HealthFormLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void load(View v) {

        List<String> healthIssues = dataLoader.getStringList("health_issues");
        dataLoader.loadCheckBoxFromArray(v, healthIssues, "diarrhea", R.id.diarrhea);
        dataLoader.loadCheckBoxFromArray(v, healthIssues, "cholera", R.id.cholera);
        dataLoader.loadCheckBoxFromArray(v, healthIssues, "cough", R.id.cough);
        dataLoader.loadCheckBoxFromArray(v, healthIssues, "vomiting", R.id.vomiting);
        dataLoader.loadCheckBoxFromArray(v, healthIssues, "injury_head", R.id.injury_head);
        dataLoader.loadCheckBoxFromArray(v, healthIssues, "injury_back", R.id.injury_back);
        dataLoader.loadCheckBoxFromArray(v, healthIssues, "injury_broken_bones", R.id.injury_broken_bones);
        dataLoader.loadCheckBoxFromArray(v, healthIssues, "injury_other", R.id.injury_other);
        dataLoader.loadCheckBoxFromArray(v, healthIssues, "milk_shortage", R.id.milk_shortage);

        dataLoader.loadString(v, "health_conditions", R.id.health_conditions_other);

        dataLoader.loadCheckBox(v, "health_worker", R.id.health_worker);
        dataLoader.loadCheckBox(v, "med_assistance", R.id.med_assistance);

        dataLoader.loadString(v, "med_agengy", R.id.med_agency);

        dataLoader.loadCheckBox(v, "psy_distress", R.id.psy_distress);

        dataLoader.loadSpinner(v, "mood", R.id.moodSpinner);
    }
}