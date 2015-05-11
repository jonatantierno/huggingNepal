package com.katmandu.katmandu;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jonatan on 6/05/15.
 */
public class TownAdapter {
    private static final String TAG = "TownAdapter";
    private final TownListActivity activity;
    private final TownStore store;

    public TownAdapter(TownListActivity activity) {
        this.activity = activity;
        store = new TownStore(activity);
    }

    public void downloadData(final TownConnection.SynchCallback synchCallback) {
        TownConnection.getTowns(PasswordStore.get(activity), new Callback<List<Map>>() {
            @Override
            public void success(List<Map> maps, Response response) {
                for (Map map : maps) {
                    if (Utl.isValidInt(map, Utl.ID)) {
                        store.saveUpToDate(Utl.getInt(map, Utl.ID, -1), map);
                    } else {
                        synchCallback.fail(new Exception("Invalid Village ID"));
                        return;
                    }
                }
                synchCallback.success();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString());
                synchCallback.fail(error);
            }
        });
    }

    public void uploadData(final TownConnection.SynchCallback synchCallback) {
        List<TownSummary> summaries= store.getSummariesToUpload();

        uploadData(summaries, synchCallback);
    }

    private void uploadData(final List<TownSummary> summaries, final TownConnection.SynchCallback synchCallback) {
        if (summaries.isEmpty()) {
            synchCallback.success();
            return;
        }
        TownSummary summary = summaries.get(0);
        if (summary.isDraft) {
            uploadNewAndContinue(summaries, synchCallback);
        } else if (summary.synchPending) {
            uploadModifiedAndContinue(summaries, synchCallback);
        } else {
            continueUpload(summaries, synchCallback);
        }
    }

    private void continueUpload(List<TownSummary> summaries, TownConnection.SynchCallback synchCallback) {
        summaries.remove(0);
        uploadData(summaries,synchCallback);
    }

    private void uploadNewAndContinue(final List<TownSummary> summaries, final TownConnection.SynchCallback synchCallback) {
        final TownSummary summary = summaries.remove(0);

        // TODO upload directly as String, without converting to map and back
        Map map = store.getTown(summary.id);
        map.put(Utl.ID, -1);

        TownConnection.uploadTown(PasswordStore.get(activity), map, new Callback<Map>() {
            @Override
            public void success(Map map, Response response) {
                if (Utl.isValidInt(map, Utl.ID)) {
                    store.saveUpToDate(Utl.getInt(map, Utl.ID, -1), map);
                    store.removeDraft(summary.id);

                    uploadData(summaries, synchCallback);
                } else {
                    synchCallback.fail(new Exception("Error uploading: Invalid id"));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                synchCallback.fail(error);
            }
        });
    }
    private void uploadModifiedAndContinue(final List<TownSummary> summaries, final TownConnection.SynchCallback synchCallback) {
        TownSummary summary = summaries.remove(0);

        // TODO upload directly as String, without converting to map and back
        Map map = store.getTown(summary.id);

        TownConnection.uploadTown(PasswordStore.get(activity), map, new Callback<Map>() {
            @Override
            public void success(Map map, Response response) {
                if (Utl.isValidInt(map, Utl.ID)) {
                    store.saveUpToDate(Utl.getInt(map, Utl.ID, -1), map);

                    uploadData(summaries, synchCallback);
                } else {
                    synchCallback.fail(new Exception("Error uploading: Invalid id"));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                synchCallback.fail(error);
            }
        });
    }

    public List<TownSummary> getLocalSummaries() {
        final List<TownSummary> list = new ArrayList<>();
        store.getLocalSumaries(new TownStore.LoadCallback() {
            @Override
            public void loaded(TownSummary summary) {
                list.add(summary);
            }
        });
        Collections.sort(list);
        return list;
    }

}
