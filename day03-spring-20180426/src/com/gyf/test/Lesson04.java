package com.gyf.test;

import com.gyf.service.IUserService;
import com.gyf.factory.MyBeanFactory;
import org.junit.Test;

public class Lesson04 {

    @Test
    public void test1() throws Exception {

        //自己实现AOP编程，使用JDK代理来实现

        IUserService userService = MyBeanFactory.createUserService();

        userService.deleteUser(10);
        userService.addUser();
        userService.updateUser();

    }
}
