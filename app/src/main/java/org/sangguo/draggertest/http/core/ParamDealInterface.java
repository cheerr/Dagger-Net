package org.sangguo.draggertest.http.core;

import org.sangguo.draggertest.http.core.util.RequestBuilderDeal;
import org.sangguo.draggertest.http.params.Params;

/**
 * Created by chenwei on 2017/6/12.
 */

public interface ParamDealInterface {

  public void encryptParams(Params params); //加密或者修改 参数

  public RequestBuilderDeal requestBuilderDeal();//处理RequestBuilder的类，比如添加Header之类都在这边做
}
