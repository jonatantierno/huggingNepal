package com.katmandu.katmandu;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jonatan on 7/05/15.
 */
public class FormFragment extends Fragment{

    public static final int[] TAB_LAYOUT= {R.layout.general_form,R.layout.food_form,R.layout.health_form,R.layout.shelter_form,R.layout.equipment_form,R.layout.water_form,R.layout.security_form,R.layout.others_form};
    private static final String TAB_POSITION_EXTRA = "TAB_POSITION_EXTRA";

    View rootView;
    int position;

    public static FormFragment newInstance(int position) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_POSITION_EXTRA, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        position = getArguments().getInt(TAB_POSITION_EXTRA);

        int layout = TAB_LAYOUT[position];
        rootView = inflater.inflate(layout,container,false);

        FragmentLoader.load(position,((TabbedFormActivity)getActivity()).getLoader(),rootView);

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        ((TabbedFormActivity)getActivity()).save(position);
    }
}
