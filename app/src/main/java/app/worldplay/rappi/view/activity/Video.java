package app.worldplay.rappi.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import app.worldplay.rappi.R;
import app.worldplay.rappi.common.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Video extends Activity {

    @BindView(R.id.webVideo)
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra("video");
        setVideo(name);
    }

    private void setVideo(String video) {
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(Constants.youtube + video);
        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) { }
        });
    }
}