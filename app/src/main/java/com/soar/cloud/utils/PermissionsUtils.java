package com.soar.cloud.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.soar.cloud.R;
import com.soar.cloud.imp.Consumer;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;


/**
 * NAME：YONG_
 * Created at: 2019/2/14
 * Describe: https://www.jianshu.com/p/a9f28cc81c9c
 */
public class PermissionsUtils {

    public static void checkPermission(Context mContext, Consumer<List<String>> consumer, String... mPermissions) {
        AndPermission.with(mContext)
                .permission(mPermissions)
                .onGranted(consumer::accept)
                // 用户拒绝权限，包括不再显示权限弹窗也在此列
                .onDenied(permissions -> {
                    // 判断用户是不是不再显示权限弹窗了，若不再显示的话进入权限设置页
                    if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                        showDialog(mContext);
                        return;
                    }
                }).start();
    }

    private static void showDialog(Context context) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.permission_title_permission_failed))
                .setMessage(context.getString(R.string.permission_message_permission_failed))
                .setPositiveButton(context.getString(R.string.permission_setting), (dialog, which) -> {
                    AndPermission.permissionSetting(context).execute();
                    dialog.dismiss();
                })
                .setNegativeButton(context.getString(R.string.permission_cancel), (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}
