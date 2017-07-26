package com.android.moncity.pda.data.source;

import android.support.annotation.NonNull;

import com.android.moncity.moncityandroidframework.http.MonCityResponse;
import com.android.moncity.moncityandroidframework.http.OkGoHttpUtil;
import com.android.moncity.moncityandroidframework.http.callback.JsonCallback;
import com.android.moncity.moncityandroidframework.utils.MonCityLog;
import com.android.moncity.pda.data.HomeDataBean;
import com.android.moncity.pda.utils.Urls;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public class HomePageRepository implements HomeDataSource {
    private static HomePageRepository INSTANCE = null;

    private final HomeDataSource mTasksRemoteDataSource;

    private final HomeDataSource mTasksLocalDataSource;

    private HomePageRepository(@NonNull HomeDataSource tasksRemoteDataSource,
                               @NonNull HomeDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        mTasksLocalDataSource = checkNotNull(tasksLocalDataSource);
    }

    public static HomePageRepository getInstance(HomeDataSource tasksRemoteDataSource,
                                                 HomeDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new HomePageRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void downLoad(@NonNull String url, @NonNull final DownLoadCallBack callBack) {
        HttpParams params = new HttpParams();
        params.put("ttt", "ttt");
        OkGoHttpUtil.post(url, params, hashCode(), new FileCallback("ttt.apk") {
            @Override
            public void onSuccess(Response<File> response) {
                MonCityLog.e("onSuccess" + response.message());
                callBack.onDownLoadSuccess(response.body().getAbsolutePath());
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                callBack.onDownLoading((int) (progress.fraction * 10000));
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                MonCityLog.e("11111" + response.message());
                callBack.onDwonLoadError(response.message());
            }
        });
    }

    @Override
    public void upload(@NonNull File file, @NonNull final UploadCallBack callBack) {
        HttpParams params = new HttpParams();
        params.put("ttt", "ttt");
        params.put("file", file);
        OkGoHttpUtil.post(Urls.URL_FORM_UPLOAD, params, hashCode(), new JsonCallback<String>() {
            @Override
            public void onSuccess(Response response) {
                callBack.onUpLoadSuccess(response.body().toString());
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                callBack.onUpLoadError(response.message());
            }
        });
    }

    @Override
    public void login(@NonNull String name, @NonNull String pwd, @NonNull final LoginCallback callback) {


        HttpParams params = new HttpParams();
        params.put("name", name);
        params.put("pwd", pwd);
        OkGoHttpUtil.post(Urls.URL_JSONOBJECT, params, hashCode(), new JsonCallback<MonCityResponse<HomeDataBean>>() {
            @Override
            public void onSuccess(Response<MonCityResponse<HomeDataBean>> response) {
                callback.onLoginSuccess(response.body().data);
            }


            @Override
            public void onError(Response<MonCityResponse<HomeDataBean>> response) {
                callback.onLoginError(response.message());
            }
        });
    }

}
