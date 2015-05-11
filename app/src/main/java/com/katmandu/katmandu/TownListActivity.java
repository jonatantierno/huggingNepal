package com.katmandu.katmandu;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class TownListActivity extends ActionBarActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String SYNCHRONIZE = "SYNCHRONIZE";
    public static final String FIRST_TIME = "FIRST_TIME";

    RecyclerView listView;
    LinearLayoutManager listLayoutManager;
    TownListAdapter listAdapter;
    SwipeRefreshLayout swipeContainer;
    ProgressBar progressBar;
    TownAdapter adapter = new TownAdapter(this);
    EditText searchEditText;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_list);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        progressBar = (ProgressBar) findViewById(R.id.synchProgressBar);
        searchEditText = (EditText) findViewById(R.id.searchEditText);

        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        swipeContainer.setOnRefreshListener(this);

        prepareListView();
    }
    void synchronize(){
        progressBar.setVisibility(View.VISIBLE);

        adapter.uploadData(new TownConnection.SynchCallback() {
            @Override
            public void success() {
                downloadData();
            }

            @Override
            public void fail(Throwable error) {
                if (error == TownConnection.INVALID_PASSWORD) {
                    requestPassword();
                } else {
                    showTextOnUi(error.getMessage());
                    downloadData();
                }
            }
        });
    }

    void downloadData() {
        progressBar.setVisibility(View.VISIBLE);
        adapter.downloadData(new TownConnection.SynchCallback() {

            @Override
            public void success() {
                progressBar.setVisibility(View.GONE);
                swipeContainer.setRefreshing(false);
                showLocalData();
            }

            @Override
            public void fail(Throwable error) {
                if (error == TownConnection.INVALID_PASSWORD) {
                    requestPassword();
                } else {
                    progressBar.setVisibility(View.GONE);
                    swipeContainer.setRefreshing(false);
                    showTextOnUi(error.getMessage());
                }
            }
        });
    }

    private void requestPassword() {
        final Intent intent = new Intent(TownListActivity.this, AccessActivity.class);
        intent.putExtra(AccessActivity.WRONG_PASSWORD,true);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        showLocalData();

        if(getIntent().getBooleanExtra(FIRST_TIME,false)){
            synchronize();
            getIntent().removeExtra(FIRST_TIME);
        }
    }

    private void showLocalData() {
        listAdapter.setData(adapter.getLocalSummaries(), searchEditText.getText().toString());
    }

    private void showTextOnUi(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showText(text);
            }
        });
    }
    private void showText(String text) {
        Toast.makeText(TownListActivity.this, text, Toast.LENGTH_LONG).show();
    }

    private void prepareListView() {
        listView = (RecyclerView) findViewById(R.id.list);
        listLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(listLayoutManager);

        listAdapter = new TownListAdapter(this);
        listView.setAdapter(listAdapter);
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
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(getString(R.string.web))));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        final TownSummary summary = listAdapter.getSummaryFromClickedElement(v);

        final Intent intent = new Intent(this, TabbedFormActivity.class);

        intent.putExtra(TabbedFormActivity.TOWN_ID_EXTRA, summary.id);
        intent.putExtra(TabbedFormActivity.TOWN_DRAFT_EXTRA, summary.isDraft);

        startActivityForResult(intent,0);
    }

    public void onClickNew(View v){
        startActivityForResult(new Intent(this, TabbedFormActivity.class),0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null && data.getBooleanExtra(SYNCHRONIZE,false)) synchronize();
    }
    @Override
    public void onRefresh() {
        synchronize();
    }
}
