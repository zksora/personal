package com.itstrongs.myapp.data.bean;

import java.util.List;

/**
 * Created by itstrongs on 2017/11/13.
 */

public class Retrofit {
    /**
     * data : [{"id":0,"name":"qianyan.mp4","title":"前言","url":"https://bj.bcebos.com/course-mct/media/qianyan.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2F74abe69d17b33905bbf78c4ae0f079a66664d2661de200060beb4e2bad8d2d00"},{"id":1,"name":"riwenhanzi.mp4","title":"五十音图\u2014\u2014日文汉字","url":"https://bj.bcebos.com/course-mct/media/riwenhanzi.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2Ff300b82ca1fb24f7ca8452422826848afb7d3f7dea1e7d284355a00207938974"},{"id":2,"name":"a.mp4","title":"五十音图\u2014\u2014あ行","url":"https://bj.bcebos.com/course-mct/media/a.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2Ffea8e3c79fdeaa10ef6227ae7e0252f3027b20f74f591f3aa0098b8b773a6c39"},{"id":3,"name":"ka.mp4","title":"五十音图\u2014\u2014か行","url":"https://bj.bcebos.com/course-mct/media/ka.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2F5b73b04e65d30d934cc3237e9d3ae319b6674821cf151276844d440c230596df"},{"id":4,"name":"sa.mp4","title":"五十音图\u2014\u2014さ行","url":"https://bj.bcebos.com/course-mct/media/sa.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2F8f8ed37f7a8f0016b624aa12b1cadcf6d31c4d4a946d70dcbaacfef4873badbd"},{"id":5,"name":"ta.mp4","title":"五十音图\u2014\u2014た行","url":"https://bj.bcebos.com/course-mct/media/ta.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2F1e77cb84e3369717089e781a6655b0d929036f244f274164cc6dc415323ce70a"},{"id":6,"name":"na.mp4","title":"五十音图\u2014\u2014な行","url":"https://bj.bcebos.com/course-mct/media/na.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2F574efad18e0efb207612dd039f34f6cf9299a064895b727297505db07d09a2a1"},{"id":7,"name":"ha.mp4","title":"五十音图\u2014\u2014は行","url":"https://bj.bcebos.com/course-mct/media/ha.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2F9eae2599fe90d986cb9589bc6a65b280bb64be2129066aba1cdcb9435212c35a"},{"id":8,"name":"ma.mp4","title":"五十音图\u2014\u2014ま行","url":"https://bj.bcebos.com/course-mct/media/ma.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2Fbe071919dbc23322d7f0f822947ff8899f159913712dd7a8439e7c858fc6c8a1"},{"id":9,"name":"ya.mp4","title":"五十音图\u2014\u2014や行","url":"https://bj.bcebos.com/course-mct/media/ya.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2Fa61af3b77bed75df5ce331c5eeab97ea6c9d11322a4132ca0e239c4a195849be"},{"id":10,"name":"ra.mp4","title":"五十音图\u2014\u2014ら行","url":"https://bj.bcebos.com/course-mct/media/ra.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2Ffb03bc0342a494f63b17efcc005dc115f15dd70457c579702879c14407c9fe3e"},{"id":11,"name":"wa.mp4","title":"五十音图\u2014\u2014わ行","url":"https://bj.bcebos.com/course-mct/media/wa.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A24Z%2F6000%2F%2Fb0687642743c6c3bacde9f33a50e4bb6bd0fb0137dd0399b117d2865b8aa5de1"},{"id":12,"name":"boyin.mp4","title":"五十音图\u2014\u2014拨音","url":"https://bj.bcebos.com/course-mct/media/boyin.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A24Z%2F6000%2F%2F41da5e0f62853128aed640ddfe66db5047da449e14e16a98d9863d8c9fce7ca1"},{"id":13,"name":"zhuoyin.mp4","title":"五十音图\u2014\u2014浊音","url":"https://bj.bcebos.com/course-mct/media/zhuoyin.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A24Z%2F6000%2F%2F913aad4a87ecc562eb80bb060523d38e3b7d2c3b86ca5ddd65d5b83941f0884b"},{"id":14,"name":"cuyin.mp4","title":"五十音图\u2014\u2014促音","url":"https://bj.bcebos.com/course-mct/media/cuyin.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A24Z%2F6000%2F%2F5d39294d67922655debd6ede5d8116bd5fd97a07455d8132373b3c60b0e8dc8f"},{"id":15,"name":"changyin.mp4","title":"五十音图\u2014\u2014长音","url":"https://bj.bcebos.com/course-mct/media/changyin.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A24Z%2F6000%2F%2F39a35320fae29686615c34f16b0e4cac2ad795d0d36c1d9d7e247de3b70dbc85"},{"id":16,"name":"aoyin.mp4","title":"五十音图\u2014\u2014拗音","url":"https://bj.bcebos.com/course-mct/media/aoyin.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A24Z%2F6000%2F%2Fa94dd6a15f79d026beb66bf0123cde4f4669422d149b7b79f91c02e61ee66cff"},{"id":17,"name":"yindiao.mp4","title":"五十音图\u2014\u2014音调","url":"https://bj.bcebos.com/course-mct/media/yindiao.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A24Z%2F6000%2F%2Faf4de230512a9721460cdb76f1422771ed0b3f5d5819666c01930b393108626c"},{"id":18,"name":"50yinjieshu.mp4","title":"结束","url":"https://bj.bcebos.com/course-mct/media/50yinjieshu.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A24Z%2F6000%2F%2Fcb74cfa8ddb2714c930a4ea1eb43ffaf454eaea1d81ca57e92b29bc761ae6cca"}]
     * msg : 成功
     * ret : 1
     */
    private String msg;
    private int ret;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Retrofit{" +
                "msg='" + msg + '\'' +
                ", ret=" + ret +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 0
         * name : qianyan.mp4
         * title : 前言
         * url : https://bj.bcebos.com/course-mct/media/qianyan.mp4?authorization=bce-auth-v1%2Fde89d2e06dd7443a9e4422d5b3fb4eea%2F2017-11-16T02%3A33%3A23Z%2F6000%2F%2F74abe69d17b33905bbf78c4ae0f079a66664d2661de200060beb4e2bad8d2d00
         */

        private int id;
        private String name;
        private String title;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
