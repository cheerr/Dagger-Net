package org.sangguo.draggertest.http.core.util;

import okhttp3.Request;

/**
 * Created by chenwei on 2017/6/8.
 */

public interface RequestBuilderFilter {

  public void filter(Request.Builder builder);
}
