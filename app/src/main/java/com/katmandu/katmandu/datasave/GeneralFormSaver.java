package com.katmandu.katmandu.datasave;

import com.katmandu.katmandu.R;
import com.katmandu.katmandu.Utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralFormSaver {
    private final DataSaver dataSaver;

    public GeneralFormSaver(DataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    public void toMap(Map map) {
        dataSaver.saveString("name", R.id.name, map);

        dataSaver.saveSpinner(Utl.STATUS, R.id.status, map);
        dataSaver.saveDouble("lat", R.id.lat, map);
        dataSaver.saveDouble("lon", R.id.lon, map);

        dataSaver.saveString("date", R.id.date, map);
        dataSaver.saveString("district", R.id.district, map);

        Map teamLeader= new HashMap();
        dataSaver.saveString("name", R.id.name_team_leader, teamLeader);
        dataSaver.saveString("tlf", R.id.phone_team_leader, teamLeader);
        map.put("team_leader",teamLeader);

        dataSaver.saveInt("total_families", R.id.total_families, map);
        dataSaver.saveInt("affected_families", R.id.affected_families, map);
        dataSaver.saveInt("pregnant", R.id.pregnant, map);
        dataSaver.saveInt("pregnant", R.id.pregnant, map);

        List<String> transport = new ArrayList<String>();

        dataSaver.saveCheckBoxToArray(transport, "motorbike", R.id.motorbike, map);
        dataSaver.saveCheckBoxToArray(transport, "car", R.id.car, map);
        dataSaver.saveCheckBoxToArray(transport, "van", R.id.van, map);
        dataSaver.saveCheckBoxToArray(transport, "jeep", R.id.jeep, map);
        dataSaver.saveCheckBoxToArray(transport, "truck", R.id.truck, map);

        map.put("transport", transport);
    }
}