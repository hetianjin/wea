package com.example.games;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
    private WebView webview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview=(WebView)findViewById(R.id.webview1);
        //处理各种通知请求和事件，如果不使用该句代码，将使用内置浏览器访问网页
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);//设置兼容JavaScript
        webview.setWebChromeClient(new WebChromeClient());//处理JavaScript对话框
        //设置默认显示的天气预报信息
        webview.loadUrl("http://m.weather.com.cn/m/pn12/weather.htm");
        webview.setInitialScale(57*2);//将网页内容放大2倍

        Button bj=(Button)findViewById(R.id.beijing);
        bj.setOnClickListener(this);
        Button sh=(Button)findViewById(R.id.shanghai);
        sh.setOnClickListener(this);
        Button hrb=(Button)findViewById(R.id.haerbin);
        hrb.setOnClickListener(this);
        Button cc=(Button)findViewById(R.id.changchun);
        cc.setOnClickListener(this);
        Button sy=(Button)findViewById(R.id.shenyang);
        sy.setOnClickListener(this);
        Button gz=(Button)findViewById(R.id.guangzhou);
        gz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.beijing: //单击的是"北京"按钮
                openUrl("CHXX0008:1:CH");
                break;
            case R.id.shanghai: //单击的是"上海"按钮
                openUrl("7f14186934f484d567841e8646abc61b81cce4d88470d519beeb5e115c9b425a");
                break;
            case R.id.haerbin: //单击的是"哈尔滨"按钮
                openUrl("982a018d0a7187f5c83fff734f6668111e108e1924c5d861a8db6b92cdde7a9f");
                break;
            case R.id.changchun: //单击的是"长春"按钮
                openUrl("7161ab060fb449863581bc006eac05a5fa223ae2c306882288a7f311c40454a7");
                break;
            case R.id.shenyang: //单击的是"沈阳"按钮
                openUrl("756e1632bcb29b64a10adfbd0af4dc8f333170a6b31e3c83f35957042ec52f35");
                break;
            case R.id.guangzhou: //单击的是"广州"按钮
                openUrl("8531a23947fdad24dcfb9cd37e6d6bd77617fa7c8b242e4773c74381cf55845b");
                break;


            default:
                break;
        }
    }
    private void openUrl(String id) {
        //获取并显示天气预报信息
        webview.loadUrl("https://weather.com/zh-CN/weather/today/l/"+id+"");

    }
}