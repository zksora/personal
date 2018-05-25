package com.itstrongs.myapp.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by itstrongs on 2017/10/23.
 */

public class Girl {

    /**
     * error : false
     * results : [{"_id":"59ed70a4421aa90fef2034bc","createdAt":"2017-10-23T12:31:32.639Z","desc":"10-23","publishedAt":"2017-10-23T12:44:23.660Z","source":"chrome","type":"福利","url":"https://img.gank.io/anri.kumaki_23_10_2017_12_27_30_151.jpg","used":true,"who":"代码家"},{"_id":"59e6aadf421aa90fef2034a0","createdAt":"2017-10-18T09:14:07.966Z","desc":"10-18","publishedAt":"2017-10-20T10:26:24.673Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171018091347_Z81Beh_nini.nicky_18_10_2017_9_13_35_727.jpeg","used":true,"who":"代码家"},{"_id":"59deaa0c421aa90fe7253583","createdAt":"2017-10-12T07:32:28.644Z","desc":"10-13","publishedAt":"2017-10-17T13:10:43.731Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171012073213_p4H630_joycechu_syc_12_10_2017_7_32_7_433.jpeg","used":true,"who":"daimajia"},{"_id":"59dea9cf421aa90fef203477","createdAt":"2017-10-12T07:31:27.363Z","desc":"10-12","publishedAt":"2017-10-16T12:19:20.311Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171012073108_0y12KR_anri.kumaki_12_10_2017_7_30_58_141.jpeg","used":true,"who":"daimajia"},{"_id":"59dd6a91421aa90fef20346c","createdAt":"2017-10-11T08:49:21.485Z","desc":"10-11","publishedAt":"2017-10-11T12:40:42.545Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171011084856_0YQ0jN_joanne_722_11_10_2017_8_39_5_505.jpeg","used":true,"who":"代码家"},{"_id":"59dc4ec1421aa94e04c2adca","createdAt":"2017-10-10T12:38:25.180Z","desc":"1","publishedAt":"2017-10-10T12:41:34.882Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-10-10-sakura.gun_10_10_2017_12_33_34_751.jpg","used":true,"who":"daimajia"},{"_id":"59caf6bb421aa972845f2099","createdAt":"2017-09-27T08:54:19.73Z","desc":"9-27","publishedAt":"2017-10-09T13:07:56.458Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjxu5qqdjoj20qo0xc0wk.jpg","used":true,"who":"daimajia"},{"_id":"59cd9b53421aa9727fdb25eb","createdAt":"2017-09-29T09:01:07.894Z","desc":"9-29","publishedAt":"2017-09-29T11:21:16.116Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fk05lf9f4cj20u011h423.jpg","used":true,"who":"daimajia"},{"_id":"59c46011421aa972845f2089","createdAt":"2017-09-22T08:57:53.998Z","desc":"9-22","publishedAt":"2017-09-26T12:12:07.813Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjs25xfq48j20u00mhtb6.jpg","used":true,"who":"daimajia"},{"_id":"59c30b37421aa9727ddd19b4","createdAt":"2017-09-21T08:43:35.381Z","desc":"9-21","publishedAt":"2017-09-21T13:27:15.675Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjqw4n86lhj20u00u01kx.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable {
        /**
         * _id : 59ed70a4421aa90fef2034bc
         * createdAt : 2017-10-23T12:31:32.639Z
         * desc : 10-23
         * publishedAt : 2017-10-23T12:44:23.660Z
         * source : chrome
         * type : 福利
         * url : https://img.gank.io/anri.kumaki_23_10_2017_12_27_30_151.jpg
         * used : true
         * who : 代码家
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        protected ResultsBean(Parcel in) {
            _id = in.readString();
            createdAt = in.readString();
            desc = in.readString();
            publishedAt = in.readString();
            source = in.readString();
            type = in.readString();
            url = in.readString();
            used = in.readByte() != 0;
            who = in.readString();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel in) {
                return new ResultsBean(in);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int i) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
        }
    }
}
