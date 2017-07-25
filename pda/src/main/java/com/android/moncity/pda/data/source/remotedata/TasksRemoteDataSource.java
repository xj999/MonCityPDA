/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.moncity.pda.data.source.remotedata;

import android.support.annotation.NonNull;

import com.android.moncity.pda.data.source.TasksDataSource;

import java.io.File;

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;


    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private TasksRemoteDataSource() {
    }


    @Override
    public void downLoad(@NonNull String url, @NonNull DownLoadCallBack callBack) {

    }

    @Override
    public void upload(@NonNull File file, @NonNull UploadCallBack callBack) {

    }


    @Override
    public void login(@NonNull String name, @NonNull String pwd, @NonNull LoginCallback callback) {

    }
}
