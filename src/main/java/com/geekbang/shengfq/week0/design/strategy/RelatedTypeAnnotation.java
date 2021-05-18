package com.geekbang.shengfq.week0.design.strategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RelatedTypeAnnotation {
    /**
     * 用户相关类型名称
     */
    UserRelatedType value();
}
