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

package com.android.moncity.pda.data.source.localdata;


import android.content.Context;
import android.support.annotation.NonNull;

import com.android.moncity.pda.data.source.HomeDataSource;

import java.io.File;

import static android.support.test.internal.util.Checks.checkNotNull;


/**
 * Concrete implementation of a data source as a db.
 */
public class HomePageLocalDataSource implements HomeDataSource {
    private static HomePageLocalDataSource INSTANCE;


    private HomePageLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
    }


    public static HomePageLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new HomePageLocalDataSource(context);
        }
        return INSTANCE;
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
