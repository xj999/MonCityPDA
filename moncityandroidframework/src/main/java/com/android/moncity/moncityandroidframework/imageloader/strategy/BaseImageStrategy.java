package com.android.moncity.moncityandroidframework.imageloader.strategy;

import android.content.Context;
import android.widget.ImageView;

import com.android.moncity.moncityandroidframework.imageloader.gilde.RoundedCornersTransformation;
import com.android.moncity.moncityandroidframework.imageloader.listener.ImageBitmapListener;
import com.android.moncity.moncityandroidframework.imageloader.listener.ImageDownLoadListener;
import com.android.moncity.moncityandroidframework.imageloader.listener.ImageLoadingListener;


/**
 * @author Luxj
 * @date create 2017/4/27
 * @description 使用模式进行封装图片框架  达到底层可拔插效果
 */
public interface BaseImageStrategy {

    void loadImage(String url, int placeholder, ImageView imageView, ImageLoadingListener imageLoadingListener);

    void loadImage(String url, int placeholder, ImageView imageView);

    void loadGifImage(int url, ImageView imageView);

    void loadImage(String url, ImageView imageView, ImageLoadingListener imageLoadingListener);

    void loadImage(String url, ImageView imageView);

    void loadRoundImage(String url, ImageView imageView);

    void loadRoundImage(String url, int placeholder, ImageView imageView);

    void loadBorderRoundImage(String url, RoundedCornersTransformation.CornerType cornerType, ImageView imageView, int borderColor);

    void loadBorderRoundImage(String url, RoundedCornersTransformation.CornerType cornerType, ImageView imageView, int placeholder, int borderColor);

    void loadBorderRoundImage(String url, RoundedCornersTransformation.CornerType cornerType, ImageView imageView, float borderWidth, int borderColor);

    void loadBorderRoundImage(String url, RoundedCornersTransformation.CornerType cornerType, ImageView imageView, int placeholder, float borderWidth, int borderColor);


    void loadCustomRoundImage(String url, RoundedCornersTransformation.CornerType cornerType, ImageView imageView);

    void loadCustomRoundImage(String url, int radius, RoundedCornersTransformation.CornerType cornerType, ImageView imageView);

    void loadCustomRoundImage(String url, int radius, int placeholder, RoundedCornersTransformation.CornerType cornerType, ImageView imageView);

    void loadCircleImage(String url, int placeholder, ImageView imageView);

    void loadCircleImage(String url, ImageView imageView);

    void loadBorderCircleImage(String url, ImageView imageView, int color);

    void loadBorderCircleImage(String url, ImageView imageView, int placeholder, int color);

    void getBitmap(Context context, String url, ImageBitmapListener listener);

    void clearImageDiskCache(final Context context);

    void clearImageMemoryCache(Context context);

    void saveImage(Context context, String url, String savePath, String saveFileName, ImageDownLoadListener listener);
}
