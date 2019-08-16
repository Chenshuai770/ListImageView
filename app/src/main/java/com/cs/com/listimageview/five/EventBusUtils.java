package com.cs.com.listimageview.five;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by Chenshuai
 * Date 2019/8/16/016 18:14
 * Descripton
 */
public class EventBusUtils {
    public static void unregister(Object subscriber) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /*public static void post(BaseEvent baseEvent) {
        EventBus.getDefault().post(baseEvent);
    }

    public static void postSticky(BaseEvent baseEvent) {
        EventBus.getDefault().postSticky(baseEvent);
    }*/


}
