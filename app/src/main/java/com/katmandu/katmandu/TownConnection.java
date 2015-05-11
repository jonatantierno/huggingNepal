package com.katmandu.katmandu;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by jonatan on 6/05/15.
 */
public class TownConnection {
    public static final String BASE_URL = "http://huggingnepal.herokuapp.com";

    static void getTowns(final String passwd, final Callback<List<Map>> callback){
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RestAdapter boardRestAdapter = buildAdapter();
                GetTownsSrv getAllBoardsSrv = boardRestAdapter.create(GetTownsSrv.class);

                getAllBoardsSrv.getTowns(passwd, callback);
            }
        });
        thread.start();

    }

    static Map getMockTown(Context context) {
        try {
            Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.sample_town));
            scanner.useDelimiter("\\A");
            Gson gson = new Gson();
            return gson.fromJson(scanner.next(),Map.class);
        } catch (Exception e){
            Log.e("Parse ERROR", e.toString());
            new RuntimeException("JSON Parsing error");
        }
        return null;
    }

    static void uploadTown(final String passwd, final Map townMap, final Callback<Map> callback){

        new Thread(new Runnable(){
            @Override
            public void run() {
                RestAdapter boardRestAdapter = buildAdapter();
                GetTownsSrv getAllBoardsSrv = boardRestAdapter.create(GetTownsSrv.class);

                getAllBoardsSrv.uploadTown(townMap, passwd, callback);
            }
        }).start();
    }

    private static RestAdapter buildAdapter() {
        return new RestAdapter.Builder().setEndpoint(BASE_URL).build();
    }

    public interface SynchCallback {
        void success();
        void fail(Throwable error);
    }
}
