package org.sangguo.draggertest.module.modules.home.model;

import org.sangguo.draggertest.module.abs.model.BaseBean;

/**
 * Created by chenwei on 2017/6/7.
 */

public class MainData extends BaseBean {

  private String data = getClass().getSimpleName();

  public String text() {
    return data;
  }
}
