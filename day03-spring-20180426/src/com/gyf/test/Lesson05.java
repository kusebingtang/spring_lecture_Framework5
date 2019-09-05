package com.gyf.test;

import com.gyf.factory.MyBeanFactory;
import com.gyf.service.StudentService;
import org.junit.Test;

public class Lesson05 {

    @Test
    public void test1() throws Exception {

        //实现AOP编程，使用cglib代理来实现

        StudentService ss = MyBeanFactory.createStudentService();

        ss.delete();
       /* ss.update();
        ss.add();*/

    }
}
