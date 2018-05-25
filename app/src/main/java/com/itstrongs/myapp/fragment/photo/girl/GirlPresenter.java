package com.itstrongs.myapp.fragment.photo.girl;

import com.itstrongs.library.utils.ActivityUtils;
import com.itstrongs.myapp.data.DataResultCallback;
import com.itstrongs.myapp.data.bean.Girl;

import java.util.ArrayList;

/**
 * 实现GirlContract.Presenter接口，执行具体的逻辑操作
 *
 * Created by itstrongs on 2017/10/23.
 */
public class GirlPresenter implements GirlContract.Presenter {

    private GirlContract.View mGirlView;
    private GirlData mGirlData;

    public GirlPresenter(GirlContract.View girlView) {
        this.mGirlView = ActivityUtils.checkNotNull(girlView, "tasksView cannot be null!");
        this.mGirlData = GirlData.getInstance();
        this.mGirlView.setPresenter(this);
    }

    @Override
    public void start() {
        doLoadGirlData();
    }

    @Override
    public void doLoadGirlData() {
        mGirlData.loadGirlData(new DataResultCallback<Girl>() {
            @Override
            public void onResult(Girl girlBean) {
                mGirlView.showImage(girlBean);
            }
        });
    }

    @Override
    public void doLoadMoreData() {
        mGirlData.loadMoreGirlData();
        doLoadGirlData();
    }

    @Override
    public ArrayList doGetGirlData() {
        return mGirlData.getGirlData();
    }
}
