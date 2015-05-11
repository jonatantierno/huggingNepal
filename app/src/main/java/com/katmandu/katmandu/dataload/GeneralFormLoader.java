package com.katmandu.katmandu.dataload;

import android.view.View;
import android.widget.TextView;

import com.katmandu.katmandu.R;
import com.katmandu.katmandu.Utl;

import java.util.List;
import java.util.Map;

public class GeneralFormLoader implements FormLoader{
    private final DataLoader dataLoader;

    public GeneralFormLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void load(View v) {
        dataLoader.loadString(v, "name", R.id.name);

        dataLoader.loadSpinner(v, "status", R.id.status);

        dataLoader.loadDouble(v, "lat", R.id.lat);
        dataLoader.loadDouble(v, "lon", R.id.lon);

        dataLoader.loadString(v, "date", R.id.date);
        dataLoader.loadString(v, "district", R.id.district);

        Map teamLeader = dataLoader.getMap("team_leader");
        ((TextView)v.findViewById(R.id.name_team_leader)).setText(Utl.getString(teamLeader,"name"));
        ((TextView)v.findViewById(R.id.phone_team_leader)).setText(Utl.getString(teamLeader,"tlf"));

        dataLoader.loadString(v, "total_families", R.id.total_families);
        dataLoader.loadString(v, "affected_families", R.id.affected_families);
        dataLoader.loadString(v, "pregnant", R.id.pregnant);
        dataLoader.loadString(v, "pregnant", R.id.pregnant);

        List<String> transport = dataLoader.getStringList("transport");
        dataLoader.loadCheckBoxFromArray(v, transport, "motorbike", R.id.motorbike);
        dataLoader.loadCheckBoxFromArray(v, transport, "car", R.id.car);
        dataLoader.loadCheckBoxFromArray(v, transport, "van", R.id.van);
        dataLoader.loadCheckBoxFromArray(v, transport, "jeep", R.id.jeep);
        dataLoader.loadCheckBoxFromArray(v, transport, "truck", R.id.truck);
    }
}