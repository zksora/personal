package com.itstrongs.myapp.service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by itstrongs on 2017/11/25.
 */
public class RobService extends AccessibilityService {

    private static final String TAG = "RobService3:";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                handleNotification(event);
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                String className = event.getClassName().toString();
                if (className.equals("com.tencent.mm.ui.LauncherUI")) {
                    Log.d(TAG, "com.tencent.mm.ui.LauncherUI");
                    getPacket();
                } else if (className.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI")) {
                    Log.d(TAG, "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI");
                    openPacket();
                } else if (className.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI")) {
                    Log.d(TAG, "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI");
                    close();
                }
                break;
        }

        switch (eventType) {
            // 当通知栏发生改变的时候
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                handleNotification2(event);
                break;
            // 当窗口内容发生改变的时候
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                break;
            // 当窗口状态发生改变的时候
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                String className = event.getClassName().toString();
                if (className.equals("com.tencent.mm.ui.LauncherUI")) { //聊天页
                    Log.d(TAG, "com.tencent.mm.ui.LauncherUI2");
//				getLastPacket();//注释的原因我在方法中写明了，如果放开这个方法就会出现死循环，大家可以尝试一下。
//				inputClick("com.tencent.mm:id/fz");//这条语句是聊天页面返回键的id,放不放开都没用。
                } else if (className  //拆红包页,点击“开”，抢红包
                        .equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI")) {
                    Log.d(TAG, "拆红包页,点击“开”，抢红包");
//                    inputClick("com.tencent.mm:id/bwn");
                } else if (className //红包详情页，点击返回键
                        .equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI")) {
                    Log.d(TAG, "红包详情页，点击返回键");
//                    inputClick("com.tencent.mm:id/hj");
                }
                break;
        }
    }

    private void handleNotification2(AccessibilityEvent event) {
        List<CharSequence> texts = event.getText();
        if (!texts.isEmpty()) {
            for (CharSequence text : texts) {
                String content = text.toString();
                if (content.contains("[微信红包]")) {
                    if ((event.getParcelableData() != null)
                            && (event.getParcelableData() instanceof Notification)) {
                        Notification notification = (Notification) event
                                .getParcelableData();
                        PendingIntent pendingIntent = notification.contentIntent;
                        try {
                            pendingIntent.send();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    /**
     * 处理通知栏信息
     *
     * 如果是微信红包的提示信息,则模拟点击
     *
     * @param event
     */
    private void handleNotification(AccessibilityEvent event) {
        Log.d(TAG, "handleNotification");
        List<CharSequence> texts = event.getText();
        if (!texts.isEmpty()) {
            for (CharSequence text : texts) {
                String content = text.toString();
                //如果微信红包的提示信息,则模拟点击进入相应的聊天窗口
                if (content.contains("[微信红包]")) {
                    Log.d(TAG, "handleNotification微信红包");
                    if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification) {
                        Notification notification = (Notification) event.getParcelableData();
                        PendingIntent pendingIntent = notification.contentIntent;
                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 关闭红包详情界面,实现自动返回聊天窗口
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void close() {
        Log.d(TAG, "close");
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            //为了演示,直接查看了关闭按钮的id
            List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByViewId("@id/hj");
            nodeInfo.recycle();
            for (AccessibilityNodeInfo item : infos) {
                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    /**
     * 模拟点击,拆开红包
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void openPacket() {
        Log.d(TAG, "openPacket");
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            //为了演示,直接查看了红包控件的id
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("@id/bwn");
            nodeInfo.recycle();
            for (AccessibilityNodeInfo item : list) {
                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    /**
     * 模拟点击,打开抢红包界面
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void getPacket() {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        AccessibilityNodeInfo node = recycle(rootNode);

        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        Log.d(TAG, "getPacket:" + (node == null));
        AccessibilityNodeInfo parent = node.getParent();
        Log.d(TAG, "getPacket:" + (parent == null));
        Log.d(TAG, "getPacket:" + (node == null));
        while (parent != null) {
            Log.d(TAG, "getPacket3");
            if (parent.isClickable()) {
                Log.d(TAG, "getPacket4");
                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
            parent = parent.getParent();
        }

    }

    /**
     * 递归查找当前聊天窗口中的红包信息
     *
     * 聊天窗口中的红包都存在"领取红包"一词,因此可根据该词查找红包
     *
     * @param node
     */
    public AccessibilityNodeInfo recycle(AccessibilityNodeInfo node) {
        if (node.getChildCount() == 0) {
            if (node.getText() != null) {
                if ("查看红包".equals(node.getText().toString())) {
                    Log.d(TAG, "recycle:" + node.getText());
                    return node;
                }
            }
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChild(i) != null) {
                    recycle(node.getChild(i));
                }
            }
        }
        return node;
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

}
