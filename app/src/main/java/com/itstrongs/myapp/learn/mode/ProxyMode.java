package com.itstrongs.myapp.learn.mode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 设计模式 —— Proxy模式
 *
 * Created by itstrongs on 2018/1/3.
 */
public class ProxyMode {

    public static void main(String[] args) {
        IHttp http = new HttpImpl();

        StaticProxy httpProxy = new StaticProxy(http);
        httpProxy.post();

        System.out.println(http.getClass());
        IHttp proxy = (IHttp) new DynamicProxy(http).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.post();
        proxy.get();
    }

    static class StaticProxy implements IHttp {

        private IHttp target;

        public StaticProxy(IHttp target) {
            this.target = target;
        }

        @Override
        public void get() {

        }

        @Override
        public void post() {
            System.out.println("start do post");
            target.post();
            System.out.println("do post finish");
        }
    }

    static class DynamicProxy {

        private Object target;

        public DynamicProxy(Object target) {
            this.target = target;
        }

        public Object getProxyInstance() {
            return Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("开始事务2");
                            Object returnValue = method.invoke(target, args);
                            System.out.println("提交事务2");
                            return returnValue;
                        }
                    }
            );
        }
    }

    static class HttpImpl implements IHttp {

        @Override
        public void get() {
            System.out.println("do get");
        }

        @Override
        public void post() {
            System.out.println("do post");
        }
    }

    interface IHttp {

        void get();

        void post();
    }
}