package com.geekbang.shengfq.week1;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * 类加载器
 * 启动类加载器
 * 扩展类加载器
 * 应用类加载器
 * */
public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        //启动类
        URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for(URL url:urls){
            System.out.println(String.format("---> %s",url.toExternalForm()));
        }

        //扩展类加载器
        printClassLoader("扩展类加载器",JvmClassLoaderPrintPath.class.getClassLoader().getParent());
        //应用类加载器
        printClassLoader("应用类加载器",JvmClassLoaderPrintPath.class.getClassLoader());

    }
    /**
     * 扩展类加载器
     * */
    public static void printClassLoader(String name,ClassLoader cl){
        if(cl!=null){
            System.out.println(String.format("%s classLoader -> %s",name,cl.toString()));
            printURLForClassLoader(cl);
        }else{
            System.out.println(String.format("%s classLoader -> null"));
        }
    }

    public static void printURLForClassLoader(ClassLoader cl){
        Object ucp=insightField(cl,"ucp");
        Object path=insightField(ucp,"path");
        ArrayList ps=(ArrayList)path;
        for(Object p:ps){
            System.out.println(String.format("--> %s ",p.toString()));
        }
    }

    public static Object  insightField(Object obj,String fname){
        try {
            Field f=null;
            if(obj instanceof URLClassLoader){
                f=URLClassLoader.class.getDeclaredField(fname);
            }else{
                f=obj.getClass().getDeclaredField(fname);
            }
            f.setAccessible(true);
            return f.get(obj);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
