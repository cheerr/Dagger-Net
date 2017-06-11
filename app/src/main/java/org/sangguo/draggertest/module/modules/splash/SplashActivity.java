package org.sangguo.draggertest.module.modules.splash;

import android.os.Bundle;
import java.util.Random;
import org.sangguo.draggertest.module.abs.activity.BaseActivity;
import org.sangguo.draggertest.module.constant.Args;
import org.sangguo.draggertest.module.modules.home.activityfragment.MainActivity;
import org.sangguo.draggertest.module.modules.home.model.MainData;
import org.sangguo.draggertest.utils.launcher.LParams;
import org.sangguo.draggertest.utils.launcher.LauncherHelper;

/**
 * Created by chenwei on 2017/6/7.
 */

public class SplashActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    LauncherHelper.from(this).startActivity(MainActivity.class,
        LParams.create()
            .putSerializable(Args.DATA, new MainData())
            .putInt(Args.PAGE, new Random().nextInt(100))
            .build());
    finish();
  }
}
