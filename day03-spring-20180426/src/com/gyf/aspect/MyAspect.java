package com.gyf.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 切面类：增加代码 与 切入点 结合
 */
public class MyAspect implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //拦截方法

        System.out.println("开启事务...");
        //放行
        Object retObj = mi.proceed();

        System.out.println("拦截.....");

        System.out.println("提交事务...");

        return retObj;
    }
}
