package com.gyf.test;

import com.gyf.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lesson07 {

    @Test
    public void test1() throws Exception {

        //获取Spring容器中代理对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans07.xml");

        IUserService userService = (IUserService) context.getBean("serviceProxy");

        userService.deleteUser();

    }
}
