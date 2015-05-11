package com.katmandu.katmandu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jonatan on 6/05/15.
 */
public class Utl {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String STATUS = "status";

    private Utl(){}

    public static String getString(Map map, String key, String defaultValue) {
        Object res = map.get(key);

        if (res == null || res.toString().isEmpty()){
            return defaultValue;
        } else {
            return res.toString();
        }
    }

    public static int getInt(Map map, String key, int defaultValue) {
        Object res = map.get(key);

        if (res == null) return defaultValue;
        try{
            return (int)Double.parseDouble(res.toString());
        } catch (NumberFormatException e){
            return defaultValue;
        }
    }


    public static TownStatus getTownStatus(Map map, String key, TownStatus defaultValue) {
        try {
            return TownStatus.fromInt(Utl.getInt(map,key,defaultValue.ordinal()));
        } catch(ClassCastException e){
            return defaultValue;
        } catch(IllegalArgumentException e) {
            return defaultValue;
        }
    }

    public static String getString(Map<String, Object> map, String key) {
        return getString(map,key,"");
    }

    public static boolean isValidString(Map map, String key) {
        Object res = map.get(key);

        return res != null && res instanceof String && !res.toString().isEmpty();
    }

    public static boolean isValidInt(Map map, String key) {
        Object res = map.get(key);
        if (res == null) return false;
        try{
            Double.parseDouble(res.toString());
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static boolean getBoolean(Map<String, Object> map, String key, boolean defaultValue) {
        Object o = map.get(key);
        if (o == null) return defaultValue;
        if (o.toString().equals("true")){
            return true;
        }
        return false;
    }

    public static boolean getBoolean(Map<String, Object> map, String key) {
        return getBoolean(map,key,false);
    }

    public static List<String> getStringList(Map<String, Object> map, String key) {
        Object o = map.get(key);
        if (o == null) return new ArrayList<>();
        try {
            return (List<String>) o;
        } catch (ClassCastException e) {
            return new ArrayList<>();
        }
    }
}
