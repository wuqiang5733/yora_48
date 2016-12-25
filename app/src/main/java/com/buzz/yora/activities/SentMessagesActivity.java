package com.buzz.yora.activities;

import android.os.Bundle;

import com.buzz.yora.R;
import com.buzz.yora.views.MainNavDrawerView;

public class SentMessagesActivity extends BaseAuthenticateActivity{

    @Override
    protected void onYoraCreate(Bundle savedState) {
        setContentView(R.layout.activity_sent_messages);
        setNavDrawerView(new MainNavDrawerView(this));
    }
}
