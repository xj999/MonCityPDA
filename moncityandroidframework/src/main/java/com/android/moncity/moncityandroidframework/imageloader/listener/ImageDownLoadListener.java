package com.android.moncity.moncityandroidframework.imageloader.listener;

import java.io.File;

/**
 * @author Luxj
 * @date create 2017/4/27
 * @description
 */
public interface ImageDownLoadListener {
    void onDownLoadSuccess(File file);

    void onDownLoadFail();
}
