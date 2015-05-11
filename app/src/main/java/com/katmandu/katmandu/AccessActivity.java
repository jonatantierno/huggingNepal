package com.katmandu.katmandu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class AccessActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        if(PasswordStore.exists(this)){
            goToList(false);
        }
    }

    public void clickOkButton(View v){
        String password = ((EditText) findViewById(R.id.editText)).getText().toString();

        PasswordStore.store(password, this);

        goToList(true);
    }

    private void goToList(boolean firstTime) {
        final Intent intent = new Intent(this, TownListActivity.class);
        intent.putExtra(TownListActivity.FIRST_TIME, firstTime);
        startActivity(intent);
        finish();
    }

}
