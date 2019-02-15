package com.soar.cloud.vm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.soar.cloud.R;
import com.soar.cloud.adapter.ImageAdapter;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.constant.DangerousPermissions;
import com.soar.cloud.utils.CommonUtils;
import com.soar.cloud.utils.FileUtils;
import com.soar.cloud.utils.PermissionsUtils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;

/**
 * NAME：YONG_
 * Created at: 2019/1/11
 * Describe:
 */
public class ImageViewModel extends BaseViewModel {

    private Activity context;

    public ImageAdapter adapter = new ImageAdapter();

    public ObservableInt position = new ObservableInt();

    public void setContext(Activity context) {
        this.context = context;
    }

    public ImageViewModel(@NonNull Application application) {
        super(application);
    }

    public ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int pos) {
            position.set(pos);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public View.OnClickListener keepOnClickListener = v -> PermissionsUtils.checkPermission(context, permissions -> downloadImg(), DangerousPermissions.WRITE_EXTERNAL_STORAGE);

    @SuppressLint("CheckResult")
    private void downloadImg() {
        Observable.create((ObservableEmitter<File> emitter) ->
                emitter.onNext(Glide.with(context)
                        .load(adapter.getItem(position.get()))
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get()))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(file -> {
                    File pictureFolder = Environment.getExternalStorageDirectory();
                    File appDir = new File(pictureFolder, context.getString(R.string.app_name));
                    if (!appDir.exists())
                        appDir.mkdirs();
                    String fileName = CommonUtils.MD5(file.getName()) + ".jpg";
                    File destFile = new File(appDir, fileName);
                    FileUtils.copy(file, destFile);
                    // 最后通知图库更新
                    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(destFile.getPath()))));
                    uiLiveData.toastEvent.show(String.format(context.getString(R.string.success_img_keep), context.getString(R.string.app_name)));
                });
    }

    @Override
    public void onDestroy() {
        context = null;
    }
}
