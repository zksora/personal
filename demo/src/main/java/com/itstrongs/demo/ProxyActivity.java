package com.itstrongs.demo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by itstrongs on 2018/1/3.
 */
public class ProxyActivity extends AppCompatActivity {

    private ActivityLife life;
    private ActivityLife target;

    protected AssetManager mAssetManager;
    private ActivityInfo mActivityInfo;
    protected Resources mResources;
    protected Resources.Theme mTheme;
    private PackageInfo packageInfo;

    private static final String  dexPath = "/data/data/com.example.lujianxin.myapplication/cache/proxy1.apk";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getIntent().getStringExtra("type");
        switch (type) {
            case "login":
                target = new LoginActivity();
                break;
            case "pay":
                target = new PayActivity();
                break;
        }
        life = (ActivityLife) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(target, args);
                    }
                }
        );
        life.onCreate(this);

        loadResources(dexPath, this);
    }

    protected void loadResources(String dexPath, Activity mProxyActivity) {
        initializeActivityInfo(dexPath);
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, dexPath);
            mAssetManager = assetManager;
        } catch (Exception e) {
            Log.i("inject", "loadResource error:"+Log.getStackTraceString(e));
            e.printStackTrace();
        }
        Resources superRes = super.getResources();
        superRes.getDisplayMetrics();
        superRes.getConfiguration();

        if (mActivityInfo.theme > 0) {
            mProxyActivity.setTheme(mActivityInfo.theme);
        }
        Resources.Theme superTheme = mProxyActivity.getTheme();
        mResources = new Resources(mAssetManager, superRes.getDisplayMetrics(),superRes.getConfiguration());
        mTheme = mResources.newTheme();
        mTheme.setTo(superTheme);
        try {
            mTheme.applyStyle(mActivityInfo.theme, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeActivityInfo(String dexPath) {
        packageInfo = getApplicationContext().getPackageManager().getPackageArchiveInfo(dexPath,
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if ((packageInfo.activities != null) && (packageInfo.activities.length > 0)) {
//            if (mClass == null) {
//                mClass = packageInfo.activities[0].name;
//            }

            //Finals 修复主题BUG
            int defaultTheme = packageInfo.applicationInfo.theme;
            for (ActivityInfo a : packageInfo.activities) {
//                if (a.name.equals(mClass)) {
                mActivityInfo = a;
                // Finals ADD 修复主题没有配置的时候插件异常
                if (mActivityInfo.theme == 0) {
                    if (defaultTheme != 0) {
                        mActivityInfo.theme = defaultTheme;
                    } else {
                        if (Build.VERSION.SDK_INT >= 14) {
                            mActivityInfo.theme = android.R.style.Theme_DeviceDefault;
                        } else {
                            mActivityInfo.theme = android.R.style.Theme;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        life.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        life.onDestroy();
    }
}
