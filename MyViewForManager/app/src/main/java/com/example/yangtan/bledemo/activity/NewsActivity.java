package com.example.yangtan.bledemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yangtan.bledemo.R;
import com.example.yangtan.bledemo.view.TitleTool;

public class NewsActivity extends AppCompatActivity {
    private TitleTool titleTool;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        titleTool = new TitleTool();
        titleTool.setTitle(this,"热点新闻",true);

        webView = (WebView)findViewById(R.id.news_webview);


        String url = "https://www.baidu.com/";
        if(!TextUtils.isEmpty(url)){
            webView.loadUrl(url);
        }

        //是否可以后退
        webView.canGoBack();
        //后退网页
        webView.goBack();
        //是否可以前进
        webView.canGoForward();
        //前进网页
        webView.goForward();

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // view.loadUrl(url);   //在当前的webview中跳转到新的url
                System.out.print("URL" + url);
                return false;
            }
        });

    }
}
