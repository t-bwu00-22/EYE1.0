package music;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;


import java.lang.reflect.Method;

public class PhoneNumber extends Activity {



    /**
     * 获取序列号
     *
     * @time 2020/8/5 15:17
     */
    public String PhoneNumber(Context context) {
        String serial = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                // 9.0 +
                serial = Build.getSerial();
            }
            else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                // 8.0 +
                if (ActivityCompat.checkSelfPermission( context, Manifest.permission.READ_PHONE_STATE ) != PackageManager.PERMISSION_GRANTED) {
                    return "没有权限";
                }
                serial = Build.getSerial();
            } else {
                // 8.0 -
                Class<?> c = Class.forName( "android.os.SystemProperties" );
                Method get = c.getMethod( "get", String.class );
                serial = (String) get.invoke( c, "ro.serialno" );
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e( "setSerialNumber", "获取设备序列号失败" );
        }
        return serial;
    }
}
