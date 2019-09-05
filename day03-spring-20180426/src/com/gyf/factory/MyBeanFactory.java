package com.gyf.factory;

import com.gyf.aspect.MyAspect2;
import com.gyf.service.IUserService;
import com.gyf.service.StudentService;
import com.gyf.service.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory {

    /**
     * JDK实现代理
     * @return
     */
    public static IUserService createUserService(){
        //1.创建目标对象target
        final IUserService userService = new UserServiceImpl();

        //2.声明切面类对象
        final MyAspect2 aspect = new MyAspect2();

        //3.把切面类2个方法 应用 目标类
        //3.1 创建JDK代理,拦截方法
        /*newProxyInstance(
                ClassLoader loader, 类加载器，写当类
                Class<?>[] interfaces, 接口,接口的方法会被拦截
                InvocationHandler h) 处理
                */
        IUserService seriviceProxy = (IUserService) Proxy.newProxyInstance(
                MyBeanFactory.class.getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //开启事务
                        //aspect.before();
                        aspect.before();

                        //方法返回值是 业务方法的返回值
                        Object retObj = method.invoke(userService,args);
                        //System.out.println("拦截返回值:" + retObj);

                        //提交事务
                        aspect.after();
                        return retObj;
                    }
                }
        );

        return seriviceProxy;
    }

    /**
     * cglib实现代理
     * @return
     */
    public static StudentService createStudentService(){
        //1.创建目标对象target
        final StudentService studentService = new StudentService();

        //2.声明切面类对象
        final MyAspect2 aspect = new MyAspect2();

        //3.创建增强对象
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(studentService.getClass());
        //设置回调【拦截】
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                /**
                 * proxy:
                 * om.gyf.service.StudentService$$EnhancerByCGLIB$$fbb8ef26
                 * proxy代理对象是StudentService的子类
                 */
                //System.out.println(proxy);
                //System.out.println(methodProxy);

                aspect.before();

                //放行方法
                //Object retObj = method.invoke(studentService,args);
                Object retObj = methodProxy.invokeSuper(proxy,args);//解藕
                System.out.println("拦截.....");

                //System.out.println(proxy);
                //System.out.println(methodProxy);
                aspect.after();
                return retObj;
            }
        });

        //创建代理对象
        StudentService serviceProxy = (StudentService) enhancer.create();
        //System.out.println("serviceProxy);
        return serviceProxy;
    }
}
