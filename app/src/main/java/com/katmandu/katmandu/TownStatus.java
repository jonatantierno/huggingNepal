package com.katmandu.katmandu;

/**
 * Created by jonatan on 5/05/15.
 */
public enum TownStatus {
    BLACK, GREEN, YELLOW, RED, BLUE;

    private static final int[] icon = new int[]{
            R.drawable.circle_black,
            R.drawable.circle_green,
            R.drawable.circle_yellow,
            R.drawable.circle_red,
            R.drawable.circle_blue
    };

    public static TownStatus fromInt(int i) {
        for(TownStatus value : TownStatus.values()){
            if(value.ordinal() == i){
                return value;
            }
        }
        throw new RuntimeException("Invalid TownStatus value: "+i);
    }

    public int getIcon() {
        return icon[ordinal()];
    }
}
