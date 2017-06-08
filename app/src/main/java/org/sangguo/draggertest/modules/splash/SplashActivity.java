package org.sangguo.draggertest.modules.splash;

import android.os.Bundle;
import java.util.Random;
import org.sangguo.draggertest.constants.Args;
import org.sangguo.draggertest.modules.abs.BaseActivity;
import org.sangguo.draggertest.modules.home.activity.MainActivity;
import org.sangguo.draggertest.modules.home.model.MainData;
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
