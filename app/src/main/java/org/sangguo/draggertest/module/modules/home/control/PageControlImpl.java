package org.sangguo.draggertest.module.modules.home.control;

import android.content.Intent;
import android.util.Log;
import javax.inject.Inject;
import org.sangguo.draggertest.R;
import org.sangguo.draggertest.dagger.scope.PerActivity;
import org.sangguo.draggertest.module.abs.activity.BaseActivity;
import org.sangguo.draggertest.module.constant.Args;
import org.sangguo.draggertest.module.modules.home.model.MainData;

/**
 * Created by chenwei on 2017/6/7.
 */

@PerActivity
public class PageControlImpl implements PageControl {

  private int currIndex = 0;
  private BaseActivity activity;

  private MainData mainData;

  @Inject
  public PageControlImpl(BaseActivity activity) {
    this.activity = activity;
    Log.i("TAG", "PageControlImpl_CREATE" + hashCode());
  }

  @Override
  public void init() {
    Intent intent = activity.getIntent();
    currIndex = intent == null ? 0 : intent.getIntExtra(Args.PAGE, currIndex);
    mainData = (MainData) intent.getSerializableExtra(Args.DATA);

    showPage(currIndex);
  }

  @Override
  public void showPage(int index) {

    activity.bindText(R.id.pageText,
        "Index " + index + "\nData " + (mainData == null ? "null" : mainData.hashCode()));

    currIndex = index;
  }
}
