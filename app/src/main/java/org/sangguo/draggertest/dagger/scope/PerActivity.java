package org.sangguo.draggertest.dagger.scope;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by chenwei on 2017/6/7.
 */
@Scope  //作用域
@Retention(RUNTIME)
public @interface PerActivity {
}
