package com.itstrongs.myapp.learn.mode;

/**
 * 设计模式 —— Instance模式
 *
 * 确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。
 *
 * Created by itstrongs on 2018/1/3.
 */
public class InstanceMode {

    public static void main(String[] args) {
        HttpHelper.getInstance().post();
    }

    static class HttpHelper {

        private static volatile HttpHelper mInstance;

        private HttpHelper() { }

        public static HttpHelper getInstance() {
            if (mInstance == null) {
                synchronized(HttpHelper.class) {
                    if (mInstance == null) {
                        mInstance = new HttpHelper();
                    }
                }
            }
            return mInstance;
        }

        public void post() {
            System.out.println("do post");
        }
    }
}

