package com.geekbang.shengfq.week0.general;
/**
 * 泛型的定义
 * 泛型类
 * 泛型接口
 * 泛型方法
 * */
public class GenericDefineApp {
    public static void main(String[] args) {
        //泛型接口的泛型实现,泛型是一种强制性约束检测
        IBaseService<UserBean> baseService=new BaseService<UserBean>();
        baseService.save(new UserBean(1,"kim"));

        IBaseService<UserBean> baseService1=new UserService();
        baseService1.save(new UserBean(2,"hello"));

        IBaseService<String> stringBaseService=new BaseService<String>();
        stringBaseService.save("hello world");
    }
}
