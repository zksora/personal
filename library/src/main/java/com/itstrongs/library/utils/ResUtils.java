package com.itstrongs.library.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * android资源工具类
 *
 * Created by itstrongs on 2017/12/28.
 */
public class ResUtils {

	/**
	 * 通过资源name获取资源
	 * @param context 上下文环境
	 * @param resName 资源name
	 * @param resType 资源类型
	 * @return 资源id
	 */
	public static int getResById(Context context, String resName, String resType){
		return context.getResources().getIdentifier(resName, resType, context.getPackageName());
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * dp转化为px
	 * @param dp
	 * @return
	 */
	public static int dpToPx(int dp) {
		return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
	}

	/**
	 * 获取屏幕宽度
	 * @return
	 */
	public static int getScreenWidth() {
		return Resources.getSystem().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * @return
	 */
	public static int getScreenHeight() {
		return Resources.getSystem().getDisplayMetrics().heightPixels;
	}
}
