package com.buzz.yora.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;

import com.buzz.yora.infrastructure.YoraApplication;
import com.squareup.otto.Bus;

public class BaseDialogFragment extends DialogFragment {

    protected YoraApplication application;
    protected Bus bus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (YoraApplication) getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
