package org.sangguo.draggertest.http.apis;

import org.sangguo.draggertest.http.core.ApiImpl;
import org.sangguo.draggertest.http.core.annotation.Get;

/**
 * 每条API单独走文件形式
 * 保证独立性
 * Created by chenwei on 2017/6/8.
 */

@Get("xxx/test/")
public class TestApi1 extends ApiImpl {

}
