package org.sangguo.draggertest.internal.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * Created by chenwei on 2017/6/7.
 */

@Qualifier //可以理解为一种标志
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}