package com.soar.cloud.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import com.soar.cloud.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * NAME：YONG_
 * Created at: 2019/2/14
 * Describe:
 */
public class PermissionsUtils {

    public interface PermissionListener {
        void onSucceed();
    }

    @SuppressLint("CheckResult")
    public static void checkPermission(Context context, PermissionListener listener, String... permissions) {
        new RxPermissions((FragmentActivity) context)
                .requestEach(permissions)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // `permission.name` is granted !
                        listener.onSucceed();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                    } else {
                        showDialog(context);
                    }
                });
    }

    private static void showDialog(Context context) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.permission_title_permission_failed))
                .setMessage(context.getString(R.string.permission_message_permission_failed))
                .setPositiveButton(context.getString(R.string.permission_setting), (dialog, which) -> {
                    toSetting(context);
                    dialog.dismiss();
                })
                .setNegativeButton(context.getString(R.string.permission_cancel), (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private static void toSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
