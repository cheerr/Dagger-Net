package org.sangguo.draggertest.http.util;

import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.callback.ResponseListener;

/**
 * Created by chenwei on 2017/6/8.
 */

public class ResponseInterfaceNull {

  public static ResponseInterface avoidNull(ResponseInterface responseInterface) {
    if (responseInterface == null) {
      return new ResponseListener();
    }
    return responseInterface;
  }
}
