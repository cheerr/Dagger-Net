package org.sangguo.draggertest.http.apis;

import org.sangguo.draggertest.http.core.ApiImpl;
import org.sangguo.draggertest.http.core.annotation.Post;
import org.sangguo.draggertest.http.core.configuration.ApiConfiguration;
import org.sangguo.draggertest.http.core.configuration.ApiConfigurationImpl2;

/**
 * Created by chenwei on 2017/6/9.
 */
@Post("xxx/test/")
public class TestApi2 extends ApiImpl {

  @Override public ApiConfiguration getApiConfiguration() {
    return ApiConfigurationImpl2.DEFAULT;
  }
}
