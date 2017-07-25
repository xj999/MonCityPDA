package com.android.moncity.pda.home;

import android.support.annotation.NonNull;

import com.android.moncity.moncityandroidframework.utils.MonCityLog;
import com.android.moncity.pda.data.Task;
import com.android.moncity.pda.data.source.TasksDataSource;
import com.android.moncity.pda.data.source.TasksRepository;
import com.android.moncity.pda.utils.Urls;

import java.io.File;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public class HomePresenter implements HomeContract.Presenter {
    private final TasksRepository mTasksRepository;

    private final HomeContract.View mTasksView;


    private boolean mFirstLoad = true;

    public HomePresenter(@NonNull TasksRepository tasksRepository, @NonNull HomeContract.View view) {
        mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null");
        mTasksView = checkNotNull(view, "tasksView cannot be null!");
        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void upload() {
        mTasksRepository.upload(new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/minishop.db"), new TasksDataSource.UploadCallBack() {
            @Override
            public void onUpLoadSuccess(String msg) {
                mTasksView.uploadSuccess();
            }

            @Override
            public void onUpLoadError(String msg) {
                mTasksView.uploadError();
            }
        });
    }

    @Override
    public void downLoad() {
        mTasksRepository.downLoad(Urls.URL_DOWNLOAD, new TasksDataSource.DownLoadCallBack() {
            @Override
            public void onDownLoadSuccess(String msg) {
                MonCityLog.e(msg);
                mTasksView.downLoadSuccess(msg);
            }

            @Override
            public void onDownLoading(int proess) {
                mTasksView.downLoadProgress(proess);
            }

            @Override
            public void onDwonLoadError(String msg) {
                mTasksView.downLoadError();
            }
        });
    }

    @Override
    public void login(String user, String pwd) {
        mTasksRepository.login(user, pwd, new TasksDataSource.LoginCallback() {
            @Override
            public void onLoginSuccess(Task task) {
                mTasksView.loginSuccess(task);
            }

            @Override
            public void onLoginError(String msg) {
                mTasksView.loginError();
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
