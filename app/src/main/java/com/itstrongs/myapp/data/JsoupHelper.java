package com.itstrongs.myapp.data;

import com.itstrongs.library.helper.Logger;
import com.itstrongs.myapp.data.bean.Gallery;
import com.itstrongs.myapp.data.bean.Joke;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Android Jsoup爬虫处理器
 *
 * Created by itstrongs on 2017/12/21.
 */
public class JsoupHelper {

    private static JsoupHelper mInstance;

    private JsoupHelper() { }

    public static JsoupHelper getInstance() {
        if (mInstance == null) {
            mInstance = new JsoupHelper();
        }
        return mInstance;
    }

    //爬取豆瓣相册列表
    public<T> void doGetPhotoList(Consumer<List<Gallery>> onNext) {
        Observable.create(new ObservableOnSubscribe<List<Gallery>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Gallery>> observableEmitter) throws Exception {
                ArrayList<Gallery> galleries = new ArrayList<>();
                Document doc = Jsoup.connect("https://www.douban.com/people/itstrongs/photos").get();
                Elements els = doc.select(".albumlst");
                for (Element ele : els) {
                    Gallery gallery = new Gallery();
                    Elements select2 = ele.select(".album_photo");
                    gallery.setChildUrl(select2.get(0).attr("href"));
                    Elements select = ele.select(".pl2");
                    gallery.setName(select.get(0).text());
                    Elements select1 = ele.select(".albumlst_descri");
                    gallery.setDescription(select1.get(0).text());
                    galleries.add(gallery);
                }
                Logger.d("爬虫数据：" + galleries);
                observableEmitter.onNext(galleries);
                observableEmitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext);
    }

    //爬取豆瓣相册照片
    public<T> void doReptileGallery(Consumer<List<Gallery>> onNext) {
        Observable.create(new ObservableOnSubscribe<List<Gallery>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Gallery>> observableEmitter) throws Exception {
                ArrayList<Gallery> galleries = new ArrayList<>();
                Document doc = Jsoup.connect("https://www.douban.com/people/itstrongs/photos").get();
                Elements els = doc.select(".albumlst");
                for (Element ele : els) {
                    Gallery gallery = new Gallery();
                    Elements select2 = ele.select(".album_photo");
                    gallery.setPictures(getDoubanImage(select2.get(0).attr("href")));
                    Elements select = ele.select(".pl2");
                    gallery.setName(select.get(0).text());
                    Elements select1 = ele.select(".albumlst_descri");
                    gallery.setDescription(select1.get(0).text());
                    galleries.add(gallery);
                }
                Logger.d("爬虫数据：" + galleries);
                observableEmitter.onNext(galleries);
                observableEmitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext);
    }

    public<T> void doReptileDuanzi(final int page, Consumer<List<Joke>> onNext) {
        Observable.create(new ObservableOnSubscribe<List<Joke>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Joke>> observableEmitter) throws Exception {
                Logger.d("子线程爬取糗百数据");
                List<Joke> mJokeList = new ArrayList<>();
                Document doc = Jsoup.connect("http://www.qiushibaike.com/8hr/page/" + page + "/").get();
                Elements els = doc.select("a.contentHerf");
                for (int i = 0; i < els.size(); i++) {
                    Element el = els.get(i);
                    String href = el.attr("href");
                    //获取详情页内容
                    Document doc_detail = Jsoup.connect("http://www.qiushibaike.com" + href).get();
                    Elements els_detail = doc_detail.select(".content");
                    //获取图片
                    Elements els_pic = doc_detail.select(".thumb img[src$=jpg]");
                    String pic = "";
                    if (!els_pic.isEmpty()) {
                        pic = els_pic.attr("src");
                    }
                    mJokeList.add(new Joke("https:" + pic, els_detail.text()));
                }
                Logger.d("爬虫数据：" + mJokeList);
                observableEmitter.onNext(mJokeList);
                observableEmitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext);
    }

    public void getDoubanImage(final String url, Consumer<List<Gallery.Picture>> onNext) {
        Observable.create(new ObservableOnSubscribe<List<Gallery.Picture>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Gallery.Picture>> observableEmitter) throws Exception {
                observableEmitter.onNext(getDoubanImage(url));
                observableEmitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext);
    }

    private ArrayList<Gallery.Picture> getDoubanImage(String url) throws IOException {
        Document doc_detail = Jsoup.connect(url).get();
        Elements select = doc_detail.select(".photo_wrap");
        ArrayList<Gallery.Picture> pictures = new ArrayList<>();
        for (Element element : select) {
            Elements select1 = element.select(".photolst_photo");
//            String imgUrl = select1.get(0).attr("href");
//            Document imgDoc = Jsoup.connect(imgUrl).get();
//            imgDoc.select("");
            Gallery.Picture picture = new Gallery.Picture();
            picture.setName(select1.get(0).attr("title"));
            Elements img = select1.get(0).select("img");
            picture.setUrl(img.attr("src"));
            pictures.add(picture);
        }
        return pictures;
    }
}
