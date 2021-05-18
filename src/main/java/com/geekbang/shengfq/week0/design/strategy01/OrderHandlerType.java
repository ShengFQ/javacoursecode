package com.geekbang.shengfq.week0.design.strategy01;

import java.lang.annotation.*;
/**
 * 钩子,条件对象
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OrderHandlerType {
	int value() default 0;
}
