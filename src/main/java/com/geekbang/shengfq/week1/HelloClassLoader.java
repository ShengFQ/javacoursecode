package com.geekbang.shengfq.week1;
import java.util.Base64;
/**
 * 自定义类加载器
 *
 * */
public class HelloClassLoader extends ClassLoader {
    /**
     * 定义一个自定义类加载器方法
     * */
    public static void main(String[] args) {
        try{
            new HelloClassLoader().findClass("com.geekbang.shengfq.week1.Hello").newInstance();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //return super.findClass(name);
        String helloBase64="yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgE" +
                "AClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEAGEhlbGxvIGNsYXNzIEluaXRpYWxpemVkIQcAGQwAGgAbAQAgY29tL2dlZWtiYW5n" +
                "L3NoZW5nZnEvd2VlazEvSGVsbG8BABBqYXZhL2xhbmcvT2JqZWN0AQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtO" +
                "wEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWACEABQAGAAAAAAACAAEABwAIAAEACQAAAB0AAQ" +
                "ABAAAABSq3AAGxAAAAAQAKAAAABgABAAAABQAIAAsACAABAAkAAAAlAAIAAAAAAAmyAAISA7YABLEAAAABAAoAAAAKAAIAAAAIAAgACQABAAwAAAACAA0=";
        byte[] bytes=decode(helloBase64);
        return defineClass(name,bytes,0,bytes.length);
    }

    public byte[] decode(String base64){
        return Base64.getDecoder().decode(base64);
    }
}
