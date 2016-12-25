package com.buzz.yora.activities;

import android.os.Bundle;
import com.buzz.yora.R;
import com.buzz.yora.views.MainNavDrawerView;

public class MainActivity extends BaseAuthenticateActivity {

    @Override
    protected void onYoraCreate(Bundle savedState) {
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Inbox");
        setNavDrawerView(new MainNavDrawerView(this));
    }
}
