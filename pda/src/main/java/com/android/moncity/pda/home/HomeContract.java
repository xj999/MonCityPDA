package com.android.moncity.pda.home;

import com.android.moncity.moncityandroidframework.base.BasePresenter;
import com.android.moncity.moncityandroidframework.base.BaseView;
import com.android.moncity.pda.data.Task;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public class HomeContract {
    interface View extends BaseView<Presenter> {
        void uploadSuccess();

        void uploadError();

        void downLoadSuccess(String path);

        void downLoadProgress(int i);

        void downLoadError();

        void loginSuccess(Task task);

        void loginError();
    }

    interface Presenter extends BasePresenter {
        void upload();

        void downLoad();

        void login(String user, String pwd);

        void onDestroy();
    }
}
