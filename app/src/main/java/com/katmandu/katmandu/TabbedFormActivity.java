package com.katmandu.katmandu;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.katmandu.katmandu.dataload.DataLoader;
import com.katmandu.katmandu.dataload.MapLoader;
import com.katmandu.katmandu.datasave.DataSaver;


public class TabbedFormActivity extends ActionBarActivity implements ActionBar.TabListener {

    public static final int[] TAB_TITLES= {R.string.tab_general,R.string.tab_food,R.string.tab_health,R.string.tab_shelter,R.string.tab_equipment,R.string.tab_water_and_sanitation,R.string.tab_security,R.string.tab_others};

    public static final String TOWN_ID_EXTRA = "TOWN_ID_EXTRA";
    public static final String TOWN_DRAFT_EXTRA = "TOWN_DRAFT_EXTRA";

    public static final int TABS_TOTAL = 8;
    public static final int TAB_GENERAL = 0;
    public static final int TAB_FOOD = 1;
    public static final int TAB_HEALTH = 2;
    public static final int TAB_SHELTER = 3;
    public static final int TAB_EQUIPMENT = 4;
    public static final int TAB_WATER = 5;
    public static final int TAB_SECURITY = 6;
    public static final int TAB_OTHERS = 7;

    private TownStore store= new TownStore(this);
    private boolean isDraft;
    private String id;

    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    private DataLoader loader;
    private Map<String,Object> savedMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_form);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        id = getIntent().getStringExtra(TOWN_ID_EXTRA);
        isDraft = getIntent().getBooleanExtra(TOWN_DRAFT_EXTRA, true);

        assert(id==null && !isDraft);

        Map map = store.getTown(id);

        loader = MapLoader.build(map);
        getSupportActionBar().setTitle(Utl.getString(map,"name","New Village"));
    }

    private boolean isNewDraft() {
        return id == null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_synch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            synchronize();
            return true;
        }
        if (id == R.id.action_web) {
            goToWeb();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToWeb() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.web))));
    }

    private void synchronize() {
        setResult(RESULT_OK,new Intent().putExtra(TownListActivity.SYNCHRONIZE,true));
        finish();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public DataLoader getLoader(){
        return loader;
    }

    public void save(int position) {
        new DataSaver(this).toMap(savedMap, position);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FormFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return TABS_TOTAL;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            return getString(TAB_TITLES[position]).toUpperCase(l);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (isNewDraft()) {
            id = store.saveNewDraft(savedMap);
        } else {
            if (isDraft){
                store.saveDraft(savedMap, id);
            } else {
                store.saveModified(id, savedMap);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "GPS Positioning cancelled", Toast.LENGTH_LONG).show();
        }
        if (data != null) {
            ((TextView) findViewById(R.id.lat)).setText(data.getStringExtra(PositionActivity.EXTRA_LAT));
            ((TextView) findViewById(R.id.lon)).setText(data.getStringExtra(PositionActivity.EXTRA_LON));
        }
    }

    public void clickHere(View v) {
        startActivityForResult(new Intent(this, PositionActivity.class), 0);
    }

    public void clickMap(View v){
        Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();

    }

}
