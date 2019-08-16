package com.cs.com.listimageview.five;

/**
 * Create by Chenshuai
 * Date 2019/8/16/016 18:08
 * Descripton
 */

import android.util.Log;

/**
 * EventBus 传递数据对象
 * Created by zhangyuting on 2016/4/6.
 */
public class MessageEvent<T> {
    public static String TAG = "MessageEvent";
    /**
     * reserved data
     */
    private T data;
    /**
     * this code distinguish between different events
     */
    private int eventCode = -1;

    public MessageEvent(int eventCode) {
        this(eventCode, null);
    }

    public MessageEvent(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    /**
     * get event code
     *
     * @return
     */
    public int getEventCode() {
        Log.i(TAG, String.valueOf(eventCode));
        return this.eventCode;
    }

    /**
     * get event reserved data
     *
     * @return
     */
    public T getData() {
        Log.i(TAG, String.valueOf(data));
        return this.data;
    }
    /**
     * EventBus 相关变量
     * Created by zhangyuting on 2016/4/6.
     */
    public class EventConfig {
        public static final int POST = 10;
        public static final int POST_STICKY = 11;
    }
}

