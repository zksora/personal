package com.itstrongs.myapp.fragment.photo.girl;

import com.itstrongs.myapp.data.DataResultCallback;
import com.itstrongs.myapp.data.bean.Girl;
import com.itstrongs.myapp.data.http.HttpService;
import com.itstrongs.myapp.data.http.RetrofitHelper;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by itstrongs on 2017/12/19.
 */
public class GirlData {

    private static GirlData mInstance;

    private int mGirlPage = 1;
    private ArrayList<Girl.ResultsBean> mGirlData = new ArrayList<>();

    private GirlData() { }

    public static GirlData getInstance() {
        if (mInstance == null) {
            mInstance = new GirlData();
        }
        return mInstance;
    }

    public void loadGirlData(final DataResultCallback callback) {
        RetrofitHelper.getInstance().getRetrofit().create(HttpService.class).getGirlData(10, mGirlPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Girl>() {
                    @Override
                    public void accept(Girl girl) throws Exception {
                        mGirlData.addAll(girl.getResults());
                        callback.onResult(girl);
                    }
                });
    }

    public ArrayList<Girl.ResultsBean> getGirlData() {
        return mGirlData;
    }

    public void loadMoreGirlData() {
        mGirlPage++;
    }
}
