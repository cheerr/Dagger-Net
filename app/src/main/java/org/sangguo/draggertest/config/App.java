package org.sangguo.draggertest.config;

import android.app.Application;
import android.content.Context;
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
  }
}
