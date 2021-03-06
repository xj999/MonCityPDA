package com.android.moncity.pda.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.moncity.moncityandroidframework.base.BaseActivity;
import com.android.moncity.moncityandroidframework.imageloader.MonCityImageLoader;
import com.android.moncity.moncityandroidframework.utils.MonCityLog;
import com.android.moncity.pda.R;
import com.android.moncity.pda.data.HomeDataBean;
import com.android.moncity.pda.data.source.HomePageRepository;
import com.android.moncity.pda.data.source.localdata.HomePageLocalDataSource;
import com.android.moncity.pda.data.source.remotedata.HomePageRemoteDataSource;
import com.android.moncity.pda.dbview.DbActivity;
import com.android.moncity.pda.scan.ScanActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v4.util.Preconditions.checkNotNull;


public class HomePage extends BaseActivity implements HomeContract.View {
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.ppp)
    ProgressBar ppp;
    private HomeContract.Presenter mPresenter;

    private List<String> imgurl = new ArrayList<>();
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter = new HomePresenter(
                HomePageRepository.getInstance(HomePageRemoteDataSource.getInstance(),
                        HomePageLocalDataSource.getInstance(getApplicationContext())), this);
        imgurl.add("http://wx1.sinaimg.cn/mw690/624c2f04gy1fhvuzwqh0zj20oq0dx3zx.jpg");
        imgurl.add("http://wx4.sinaimg.cn/mw690/624c2f04gy1fhvuocjs3ej20fd0aa3zd.jpg");
        imgurl.add("http://wx4.sinaimg.cn/mw690/632dab64ly1fhvug783uoj20dc08wn08.jpg");
        imgurl.add("http://wx3.sinaimg.cn/mw690/624c2f04gy1fhvu2dk4h5j20fd0a9gm3.jpg");
        imgurl.add("http://wx3.sinaimg.cn/mw690/710c95f3ly1fhvsgn7pplj20de08w0up.jpg");
        imgurl.add("http://wx3.sinaimg.cn/mw690/710c95f3ly1fhvsgnmp6dj20de08wgnw.jpg");
        imgurl.add("http://wx4.sinaimg.cn/mw690/632dab64ly1fhvtiiurcmj20dc08wtal.jpg");
        imgurl.add("http://wx2.sinaimg.cn/mw690/96f7c652gy1fhve8n8qxxj21bf0qoh35.jpg");
        imgurl.add("http://wx3.sinaimg.cn/mw690/96f7c652gy1fhve8rx7elj21bf0qokho.jpg");
        imgurl.add("http://wx2.sinaimg.cn/mw690/96f7c652gy1fhveb0vfdxj21bf0qo7n6.jpg");
        imgurl.add("http://wx3.sinaimg.cn/mw690/96f7c652gy1fhveb3c2u8j21bf0qo7k0.jpg");
        setTitleText("主页");
        setNavBackVisibility(View.GONE);

    }

    @Override
    public void uploadSuccess() {
        dismissLoading();
        showToast("上传成功");
    }

    @Override
    public void uploadError() {
        dismissLoading();
        showToast("上传失败");
    }

    @Override
    public void downLoadSuccess(String path) {
        showToast("下载成功" + path);
        ppp.setVisibility(View.GONE);
    }

    @Override
    public void downLoadProgress(int i) {
        MonCityLog.e("downLoadProgress: " + i);
        ppp.setProgress(i);
        if (ppp.getVisibility() == View.GONE) {
            ppp.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void downLoadError() {
        showToast("下载失败");
        ppp.setVisibility(View.GONE);

    }

    @Override
    public void loginSuccess(HomeDataBean task) {
        dismissLoading();
        showToast(task.author.des);

    }

    @Override
    public void loginError() {
        dismissLoading();
        showToast("失败");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @OnClick({R.id.show_pic, R.id.update, R.id.download, R.id.login, R.id.activity_main, R.id.db_btn, R.id.scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                startActivity(new Intent(HomePage.this, ScanActivity.class));
                break;
            case R.id.show_pic:

                MonCityImageLoader.getInstance().loadImage(imgurl.get(i), img);  //加载普通图片
                MonCityImageLoader.getInstance().loadImage(imgurl.get(i), R.mipmap.ic_launcher, img);//加载带占位符图片
                MonCityImageLoader.getInstance().loadCircleImage(imgurl.get(i), img);//加载圆形图片
                MonCityImageLoader.getInstance().loadBorderCircleImage(imgurl.get(i), img, R.color.nav_line_gray_color);//加载圆形带边框图片自定义边框颜色
                MonCityImageLoader.getInstance().loadBorderCircleImage(imgurl.get(i), R.mipmap.ic_launcher, R.color.nav_line_gray_color, img);//加载圆形带边框图片带占位符自定义边框颜色
                MonCityImageLoader.getInstance().loadRoundImage(imgurl.get(i), img);//加载圆角图片
                MonCityImageLoader.getInstance().loadRoundImage(imgurl.get(i), img,5);//加载圆角图片自定义圆角角度

                if (i == imgurl.size() - 1) {
                    i = 0;
                } else {
                    i++;
                }
                break;
            case R.id.update:
                showLoading();
                mPresenter.upload();
                break;
            case R.id.download:
                ppp.setMax(10000);
                mPresenter.downLoad();
                break;
            case R.id.login:
                showLoading();
                mPresenter.login("111", "222");
                break;
            case R.id.db_btn:
                startActivity(new Intent(HomePage.this, DbActivity.class));
                break;
        }
    }
}
