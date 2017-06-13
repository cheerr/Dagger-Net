package org.sangguo.draggertest.http.core;

import org.json.JSONObject;
import org.sangguo.draggertest.http.data.Result;

/**
 * Created by chenwei on 2017/6/12.
 */

public interface JsonChanger {

  public Result jsonToResult(JSONObject json);  //JSONObject 转换为Result的实现方式
}
