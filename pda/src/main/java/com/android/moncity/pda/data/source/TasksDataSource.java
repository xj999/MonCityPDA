package com.android.moncity.pda.data.source;

import android.support.annotation.NonNull;

import com.android.moncity.pda.data.Task;

import java.io.File;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public interface TasksDataSource {
    interface LoginCallback {

        void onLoginSuccess(Task task);

        void onLoginError(String msg);
    }

    interface DownLoadCallBack {

        void onDownLoadSuccess(String msg);

        void onDownLoading(int proess);

        void onDwonLoadError(String msg);
    }

    interface UploadCallBack {

        void onUpLoadSuccess(String msg);


        void onUpLoadError(String msg);
    }

    void downLoad(@NonNull String url, @NonNull final DownLoadCallBack callBack);

    void upload(@NonNull File file, @NonNull UploadCallBack callBack);

    void login(@NonNull String name, @NonNull String pwd, @NonNull LoginCallback callback);

}