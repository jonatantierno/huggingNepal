package com.katmandu.katmandu;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jonatan on 5/05/15.
 */
public class TownStore {

    public static final String SERVER_JSON_STORE = "SERVER_JSON_STORE";
    public static final String SERVER_SUMMARIES_STORE = "SERVER_SUMMARIES_STORE";
    private final Context context;

    public TownStore(Context context){
        this.context = context;
    }

    public void saveModified(String id, Map map) {
        map.put(Utl.ID,id);
        storeAsJson(id, SERVER_JSON_STORE, map);
        storeSummary(id, buildModifiedSummary(map, id), SERVER_SUMMARIES_STORE);
    }
    public void saveUpToDate(int serverId, Map map) {
        map.put(Utl.ID,Integer.toString(serverId));
        storeAsJson(Integer.toString(serverId), SERVER_JSON_STORE, map);
        storeSummary(Integer.toString(serverId), buildUpToDateSummary(map, serverId), SERVER_SUMMARIES_STORE);
    }

    public void saveDraft(Map map, String id) {
        map.put(Utl.ID,id);

        storeAsJson(id, SERVER_JSON_STORE, map);
        storeSummary(id, buildDraftSummary(map, id), SERVER_SUMMARIES_STORE);
    }


    public String saveNewDraft(Map map) {
        final String draftKey = createTemporaryDraftKey();

        map.put(Utl.ID,draftKey);

        saveDraft(map, draftKey);
        return draftKey;
    }

    private TownSummary buildModifiedSummary(Map map, String id) {
        assert id != null;
        TownStatus status = Utl.getTownStatus(map, Utl.STATUS, TownStatus.BLACK);

        return TownSummary.buildModified(Utl.getString(map, Utl.NAME, "No name"), status, id);
    }
    private TownSummary buildUpToDateSummary(Map map, int serverId) {
        TownStatus status = Utl.getTownStatus(map, Utl.STATUS, TownStatus.BLACK);

        return TownSummary.buildUpToDate(Utl.getString(map, Utl.NAME, "No name"), status, serverId);
    }

    private TownSummary buildDraftSummary(Map map, String id) {
        String name = Utl.getString(map, Utl.NAME, "Draft");

        TownStatus status = Utl.getTownStatus(map, Utl.STATUS, TownStatus.BLACK);

        return TownSummary.buildDraft(name, status, id);
    }
    private void storeSummary(String id, TownSummary townSummary, String store) {
        SharedPreferences settings = context.getSharedPreferences(store, 0);
        settings.edit().putString(id,townSummary.toString()).apply();
    }

    public void removeDraft(String draftId){
        SharedPreferences settings = context.getSharedPreferences(SERVER_JSON_STORE, 0);
        settings.edit().remove(draftId).apply();
        settings = context.getSharedPreferences(SERVER_SUMMARIES_STORE, 0);
        settings.edit().remove(draftId).apply();
    }


    private void storeAsJson(String key, String store, Map map) {
        String jsonString = new Gson().toJson(map);
        SharedPreferences settings = context.getSharedPreferences(store, 0);
        settings.edit().putString(key, jsonString).apply();
    }

    public Map getTown(String id){
        if (id == null){
            return new HashMap();
        }
        return getTown(id, SERVER_JSON_STORE);
    }

    private Map getTown(String id, String store) {
        SharedPreferences settings = context.getSharedPreferences(store, 0);
        String jsonString = settings.getString(id, "");
        Log.d("TownStore",jsonString);

        return new Gson().fromJson(jsonString, Map.class);
    }

    private static String createTemporaryDraftKey() {
        return "DRAFT_"+Long.toString(System.currentTimeMillis());
    }

    public List<TownSummary> getSummariesToUpload() {
        List<TownSummary> list = new ArrayList<>();

        Iterator<String> iterator = getSummaries(SERVER_SUMMARIES_STORE);

        while(iterator.hasNext()) {
            final TownSummary summary = TownSummary.deserialize(iterator.next());
            if (summary.synchPending) {
                list.add(summary);
            }
        }
        return list;
    }

    public void getLocalSumaries(LoadCallback loadCallback) {
        getSummaries(SERVER_SUMMARIES_STORE, loadCallback);
    }


    private void getSummaries(String store, LoadCallback loadCallback) {
        Iterator<String> iterator = getSummaries(store);

        while(iterator.hasNext()){
            loadCallback.loaded(TownSummary.deserialize(iterator.next()));
        }
    }

    private Iterator<String> getSummaries(String store) {
        SharedPreferences settings = context.getSharedPreferences(store, 0);
        return (Iterator<String>) settings.getAll().values().iterator();
    }

    public interface LoadCallback {
        void loaded(TownSummary deserialize);
    }
}
