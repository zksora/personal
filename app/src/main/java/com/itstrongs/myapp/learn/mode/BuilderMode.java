package com.itstrongs.myapp.learn.mode;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计模式 —— Builder模式
 *
 * 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 *
 * Created by itstrongs on 2018/1/3.
 */
public class BuilderMode {

    public static void main(String[] args) {
        Http build = new Http.Builder()
                .url("https://www.baidu.com")
                .param(new HashMap<String, String>())
                .build();
        build.post();
    }

    static class Http {

        private String url;

        private Map<String, String> param;

        Http() { }

        public void post() {
            System.out.println("do post: url = " + url + " param = " + param);
        }

        static class Builder {

            private Http target;

            public Builder() {
                target = new Http();
            }

            public Builder url(String url) {
                target.url = url;
                return this;
            }

            public Builder param(Map<String, String> param) {
                target.param = param;
                return this;
            }

            public Http build() {
                return target;
            }
        }
    }
}