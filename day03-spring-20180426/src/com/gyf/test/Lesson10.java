package com.gyf.test;

import com.gyf.service.IUserService;
import com.gyf.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lesson10 {

    @Test
    public void test1() throws Exception {

        //获取Spring容器中代理对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans10.xml");

        IUserService userService = (IUserService) context.getBean("userService");

        userService.deleteUser();



    }
}
