package com.buzz.yora.activities;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Rodrigo on 19/03/2016.
 */
public abstract class BaseAuthenticateActivity extends BaseActivity {

    @Override
    protected final void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        if (!application.getAuth().getUser().isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        onYoraCreate(savedState);
    }

    protected abstract void onYoraCreate(Bundle savedState);
}
