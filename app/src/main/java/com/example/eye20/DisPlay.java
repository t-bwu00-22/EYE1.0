package com.example.eye20;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;

/**
 * 文件读取
 */
public class DisPlay extends Activity {

    //文件读取
    public static String readFileData_1(String fileName, Context context){
       // super.attachBaseContext(context);
        String result="";
        try{
            FileInputStream fis =context.openFileInput(fileName);
            //获取文件长度
            int lenght = fis.available();
            byte[] buffer = new byte[lenght];
            fis.read(buffer);
            //将byte数组转换成指定格式的字符串
            result = new String(buffer, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }
}

