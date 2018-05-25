package com.itstrongs.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.itstrongs.library.helper.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        doGetPhotoList(this);
    }

    public<T> void doGetPhotoList(final Activity activity) {
        Observable.create(new ObservableOnSubscribe<ArrayList<Gallery>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ArrayList<Gallery>> observableEmitter) throws Exception {
                Logger.d("subscribe==");
                ArrayList<Gallery> galleries = new ArrayList<>();
                Logger.d("subscribe==");
                Document doc = Jsoup.connect("https://www.douban.com/people/itstrongs/photos").get();
                Logger.d("subscribe==");
                Elements els = doc.select(".albumlst");
                Logger.d("subscribe==");
                for (Element ele : els) {
                    Logger.d("subscribe==");
                    Gallery gallery = new Gallery();
                    Elements select2 = ele.select(".album_photo");
                    gallery.setChildUrl(select2.get(0).attr("href"));
                    Elements select = ele.select(".pl2");
                    gallery.setName(select.get(0).text());
                    Elements select1 = ele.select(".albumlst_descri");
                    gallery.setDescription(select1.get(0).text());
                    galleries.add(gallery);
                }

                observableEmitter.onNext(galleries);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Gallery>>() {
                    @Override
                    public void accept(ArrayList<Gallery> integer) throws Exception {
                        Logger.d("爬虫数据：" + integer);
                    }
                });
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    final ArrayList<Gallery> galleries = new ArrayList<>();
//                    Document doc = Jsoup.connect("https://www.douban.com/people/itstrongs/photos").get();
//                    Elements els = doc.select(".albumlst");
//                    for (Element ele : els) {
//                        Gallery gallery = new Gallery();
//                        Elements select2 = ele.select(".album_photo");
//                        gallery.setChildUrl(select2.get(0).attr("href"));
//                        Elements select = ele.select(".pl2");
//                        gallery.setName(select.get(0).text());
//                        Elements select1 = ele.select(".albumlst_descri");
//                        gallery.setDescription(select1.get(0).text());
//                        galleries.add(gallery);
//                    }
//                    Logger.d("爬虫数据：" + galleries);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }

    @OnClick({R.id.btn_0, R.id.btn_1})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        switch (view.getId()) {
            case R.id.btn_0:
                intent.putExtra("type", "login");
                break;
            case R.id.btn_1:
                intent.putExtra("type", "pay");
                break;
        }
        startActivity(intent);
    }
}
