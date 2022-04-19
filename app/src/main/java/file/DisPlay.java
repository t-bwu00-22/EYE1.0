package file;

import android.content.Context;

import java.io.FileInputStream;

public class DisPlay {
    private static Context context;

    public static void display(){
        System.out.println("错误11");
        String data=readFileData_1("datafile1",context);
        if(data!=null)
            System.out.println(data);
        else
            System.out.println("错误");
    }


    //文件读取
    public static String readFileData_1(String fileName, Context context){
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
