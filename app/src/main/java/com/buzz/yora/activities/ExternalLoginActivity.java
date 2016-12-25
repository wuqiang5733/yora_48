package com.buzz.yora.activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.buzz.yora.R;

public class ExternalLoginActivity extends BaseActivity implements View.OnClickListener {

    public static final String EXTRA_EXTERNAL_SERVICE = "EXTRA_EXTERNAL_SERVICE";

    private Button testButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_external_login);

        testButton = (Button) findViewById(R.id.activity_external_login_testButton);
        webView = (WebView) findViewById(R.id.activity_external_login_webView);

        testButton.setOnClickListener(this);
        testButton.setText("Log in with: " + getIntent().getStringExtra(EXTRA_EXTERNAL_SERVICE));
    }

    @Override
    public void onClick(View view) {
        if (view == testButton) {
            application.getAuth().getUser().setLoggedIn(true);
            setResult(RESULT_OK);
            finish();
        }
    }
}
