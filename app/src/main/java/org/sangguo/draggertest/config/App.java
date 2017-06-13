package org.sangguo.draggertest.config;

import android.app.Application;
import android.content.Context;
import okhttp3.Request;
import org.json.JSONObject;
import org.sangguo.draggertest.BuildConfig;
import org.sangguo.draggertest.http.core.JsonChanger;
import org.sangguo.draggertest.http.core.configuration.NetConfiguration;
import org.sangguo.draggertest.http.core.configuration.NetOptions;
import org.sangguo.draggertest.http.core.configuration.okhttpclient.SimpleHttpClient;
import org.sangguo.draggertest.http.core.util.RequestBuilderFilter;
import org.sangguo.draggertest.http.data.Result;
import org.sangguo.draggertest.utils.Res;

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
                .setJsonChanger(new JsonChanger() {
                  @Override public Result jsonToResult(JSONObject json) {
                    return null;
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
