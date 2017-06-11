package org.sangguo.draggertest.module.abs.presenter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 在AbstractPresenterLifeCycleActivity注入生命周期
 * Created by chenwei on 2017/6/11.
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface PresenterLifeCycle {
}
