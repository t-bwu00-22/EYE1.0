package com.example.eye20;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

/**
 * 数据文件处置
 */
public class DataFile {
    private static Context ctx;


    //DIY设置数据文件
    public static void datafile(String data, Context ctx) {
        FileOutputStream out = null;
        BufferedWriter writer = null;

        try {

            //设置文件名称，以及存储方式
            out = ctx.openFileOutput("DIYdatafile", Context.MODE_PRIVATE);
            //创建一个OutputStreamWriter对象，传入BufferedWriter的构造器中
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //向文件中写入数据
            writer.write(data);
            System.out.println("成功");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //DIY设置数据文件
    public static void datafile_0(String tn, String data, Context context) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //设置文件名称，以及存储方式
            out = context.openFileOutput(tn, Context.MODE_PRIVATE);
            //创建一个OutputStreamWriter对象，传入BufferedWriter的构造器中
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //向文件中写入数据
            writer.write(data);




        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                //System.out.println("文件路径：" + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
