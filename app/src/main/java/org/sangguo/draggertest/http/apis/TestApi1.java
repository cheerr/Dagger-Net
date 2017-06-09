package org.sangguo.draggertest.http.apis;

import org.sangguo.draggertest.http.core.ApiImpl;

/**
 * 每条API单独走文件形式
 * 保证独立性
 * Created by chenwei on 2017/6/8.
 */
public class TestApi1 extends ApiImpl {

  @Override public String apiName() {
    return "xxx/test/";
  }
}
