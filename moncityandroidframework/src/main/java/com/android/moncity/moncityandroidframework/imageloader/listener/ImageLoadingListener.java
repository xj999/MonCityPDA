package com.android.moncity.moncityandroidframework.imageloader.listener;

import android.widget.ImageView;

/**
 *  图片加载监听，只是用于监听成功或失败的状态
 * Created by zhengjb on 2017/5/23.
 */

public interface ImageLoadingListener {
    void onSuccess(ImageView imageView, String path);
    void onError(ImageView imageView, String path);
}
