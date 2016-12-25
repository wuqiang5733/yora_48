package com.buzz.yora.activities;

import android.os.Bundle;

import com.buzz.yora.R;
import com.buzz.yora.views.MainNavDrawerView;

public class ContactsActivity extends BaseAuthenticateActivity {

    @Override
    protected void onYoraCreate(Bundle savedState) {
        setContentView(R.layout.activity_contacts);
        setNavDrawerView(new MainNavDrawerView(this));
    }
}
