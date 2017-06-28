package org.sangguo.draggertest.module.apis;

import thirdparty.http.lib.core.ApiImpl;
import thirdparty.http.lib.core.annotation.Get;

/**
 * 每条API单独走文件形式
 * 保证独立性
 * Created by chenwei on 2017/6/8.
 */
@Get("xxx/test/")
public class TestApi1 extends ApiImpl {

}
