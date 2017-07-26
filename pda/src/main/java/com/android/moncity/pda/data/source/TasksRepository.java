package com.android.moncity.pda.data.source;

import android.support.annotation.NonNull;

import com.android.moncity.moncityandroidframework.http.MonCityResponse;
import com.android.moncity.moncityandroidframework.http.OkGoHttpUtil;
import com.android.moncity.moncityandroidframework.http.callback.JsonCallback;
import com.android.moncity.moncityandroidframework.utils.MonCityLog;
import com.android.moncity.pda.data.Task;
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
public class TasksRepository implements TasksDataSource {
    private static TasksRepository INSTANCE = null;

    private final TasksDataSource mTasksRemoteDataSource;

    private final TasksDataSource mTasksLocalDataSource;

    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
                            @NonNull TasksDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        mTasksLocalDataSource = checkNotNull(tasksLocalDataSource);
    }

    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
                                              TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
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
                MonCityLog.e("onSuccess" + response.message());
                callBack.onUpLoadSuccess(response.body().toString());
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                MonCityLog.e(progress.fraction + "             uploadProgress");
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                MonCityLog.e("11111" + response.message());
                callBack.onUpLoadError(response.message());
            }
        });
    }

    @Override
    public void login(@NonNull String name, @NonNull String pwd, @NonNull final LoginCallback callback) {



        HttpParams params = new HttpParams();
        params.put("name", name);
        params.put("pwd", pwd);
        OkGoHttpUtil.post(Urls.URL_JSONOBJECT, params, hashCode(), new JsonCallback<MonCityResponse<Task>>() {
            @Override
            public void onSuccess(Response<MonCityResponse<Task>> response) {
                callback.onLoginSuccess(response.body().data);
            }


            @Override
            public void onError(Response<MonCityResponse<Task>> response) {
                callback.onLoginError(response.message());
            }
        });
    }

}
