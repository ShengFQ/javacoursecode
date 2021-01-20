package com.geekbang.shengfq.week1;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义类加载器应用
 * 添加引用类
 */
public class JvmAppClassLoaderAddURL {

    public static void main(String[] args) {
        String appPath = "/Users/sheng/workspace/github/ShengFQ/course/src/main/java/com/geekbang/shengfq/week1/";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader, url);
            Class.forName("com.geekbang.shengfq.week1.Hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
