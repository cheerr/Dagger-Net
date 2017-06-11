package org.sangguo.draggertest.module.modules.home.fragment;

import org.sangguo.draggertest.module.modules.home.di.HomeMainComponent;
import org.sangguo.draggertest.module.modules.home.inject.InjectHomeMainBarFragment;

/**
 * Created by chenwei on 2017/6/11.
 */

public class MainFragment extends InjectHomeMainBarFragment {
  @Override public void inject(HomeMainComponent component) {
    component.inject(this);
  }
}
