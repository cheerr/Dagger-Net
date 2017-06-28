package org.sangguo.draggertest.config;

import android.app.Application;
import android.content.Context;
import okhttp3.Request;
import org.json.JSONObject;
import org.sangguo.draggertest.BuildConfig;
import org.sangguo.draggertest.module.model.SimpleResult;
import org.sangguo.draggertest.utils.Res;
import thirdparty.http.lib.core.configuration.NetConfiguration;
import thirdparty.http.lib.core.configuration.NetOptions;
import thirdparty.http.lib.core.configuration.okhttpclient.SimpleHttpClient;
import thirdparty.http.lib.core.util.RequestBuilderFilter;
import thirdparty.http.lib.data.Result;
import thirdparty.http.lib.data.ResultBuilder;

/**
 * Created by chenwei on 2017/6/7.
 */

public class App {

  //维护一个全局的Context，以便工具类使用
  public static Context appContext;

  public static void initApp(Application application) {
    App.appContext = application.getApplicationContext();
    Res.initRes(application);
    App.initNet(application);
  }

  private static void initNet(Application application) {
    new NetConfiguration.Builder()
        .setDebug(BuildConfig.DEBUG)
        .setDefaultNetOptions(
            new NetOptions()
                .setBaseUrl("http://www.baidu.com")
                .setResultBuilder(new ResultBuilder() {
                  @Override public Result jsonToResult(JSONObject json) {
                    return new SimpleResult(json);
                  }
                })
                .setOkHttpClient(new SimpleHttpClient().get())
                .setRequestBuilderFilter(new RequestBuilderFilter() {
                  @Override public void filter(Request.Builder builder) {

                  }
                })
        ).build();
  }
}
