package com.soar.cloud.constant;

import android.Manifest;

/**
 * NAME：YONG_
 * Created at: 2017/11/9 16
 * Describe:6.0+ 需动态申请的危险权限
 */
public class DangerousPermissions {
    public static final String WRITE_CONTACTS= Manifest.permission.WRITE_CONTACTS;//写入联系人，但不可读取
    public static final String GET_ACCOUNTS= Manifest.permission.GET_ACCOUNTS;//访问一个帐户列表在Accounts Service中
    public static final String READ_CONTACTS= Manifest.permission.READ_CONTACTS;//读取联系人，但不可写入
    public static final String READ_CALL_LOG= Manifest.permission.READ_CALL_LOG;//读取通话记录
    public static final String READ_PHONE_STATE= Manifest.permission.READ_PHONE_STATE;//读取手机状态
    public static final String CALL_PHONE= Manifest.permission.CALL_PHONE;//读取手机号码
    public static final String WRITE_CALL_LOG= Manifest.permission.WRITE_CALL_LOG;//写入通话记录
    public static final String USE_SIP= Manifest.permission.USE_SIP;
    public static final String PROCESS_OUTGOING_CALLS= Manifest.permission.PROCESS_OUTGOING_CALLS;//允许程序监视、修改有关播出电话
    public static final String ADD_VOICEMAIL= Manifest.permission.ADD_VOICEMAIL;
    public static final String READ_CALENDAR= Manifest.permission.READ_CALENDAR;//允许程序读取用户日历数据
    public static final String WRITE_CALENDAR= Manifest.permission.WRITE_CALENDAR;//允许一个程序写入但不读取用户日历数据
    public static final String CAMERA= Manifest.permission.CAMERA;//相机
    public static final String BODY_SENSORS= Manifest.permission.BODY_SENSORS;//人体传感器;
    public static final String ACCESS_FINE_LOCATION= Manifest.permission.ACCESS_FINE_LOCATION;//这个权限用于访问GPS定位
    public static final String ACCESS_COARSE_LOCATION= Manifest.permission.ACCESS_COARSE_LOCATION;//这个权限用于进行网络定位
    public static final String READ_EXTERNAL_STORAGE= Manifest.permission.READ_EXTERNAL_STORAGE;//读取外部存储器
    public static final String WRITE_EXTERNAL_STORAGE= Manifest.permission.WRITE_EXTERNAL_STORAGE;//写入外部存储器
    public static final String RECORD_AUDIO= Manifest.permission.RECORD_AUDIO;//允许程序录制音频
    public static final String READ_SMS= Manifest.permission.READ_SMS;//读取短信
    public static final String RECEIVE_WAP_PUSH= Manifest.permission.RECEIVE_WAP_PUSH;//允许程序监控将收到WAP PUSH信息
    public static final String RECEIVE_MMS= Manifest.permission.RECEIVE_MMS;//允许一个程序监控将收到MMS彩信,记录或处理
    public static final String RECEIVE_SMS= Manifest.permission.RECEIVE_SMS;//允许程序监控一个将收到短信息，记录或处理
    public static final String SEND_SMS= Manifest.permission.SEND_SMS;//允许程序发送SMS短信
}
