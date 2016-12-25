package com.buzz.yora.fragments;

import android.os.Bundle;
import android.app.Fragment;

import com.buzz.yora.infrastructure.YoraApplication;
import com.squareup.otto.Bus;

public class BaseFragment extends Fragment {

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
