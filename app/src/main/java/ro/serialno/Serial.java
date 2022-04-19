package ro.serialno;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Method;

public class Serial {
    private static Log LogUtils;

    /**
     * 获取手机序列号
     *
     * @return 手机序列号
     * @param application
     */
    @SuppressLint({"NewApi", "MissingPermission"})
    public static String getSerialNumber(Context application) {
        String serial = "";
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {//8.0+
                serial = Build.SERIAL;
            } else {//8.0-
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class);
                serial = (String) get.invoke(c, "ro.serialno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("e", "读取设备序列号异常：" + e.toString());
        }
        return serial;
    }
}
