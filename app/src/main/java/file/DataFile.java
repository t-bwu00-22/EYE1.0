package file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

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

    public static void datafile(){
        try {
            String Filepath = Environment.getExternalStorageDirectory().getPath() ;//这是sd卡的目录
            String FileName = "DIYdatafile";
            String textContent = "这是一个测试字符串，写进text文本的";

            File dictionaryFile = new File(Filepath);//这是目录路径
            if (!dictionaryFile.exists())//如果不存在该目录
            {
                dictionaryFile.mkdirs();//这个方法的好处在于：如果上层目录不存在，会按照顺序一层层创建目录，
                //所以建议以后不管怎么样，使用该方法创建目录
            }

            File textFile = new File(Filepath + FileName);//这是文件路径
            if (!textFile.exists())//如果不存在该目录
            {
                textFile.createNewFile();//这个方法是创建文件的方法
            }
            RandomAccessFile raf = new RandomAccessFile(Filepath + FileName, "rwd");
            raf.seek(textFile.length());
            raf.write("我是谁".getBytes());
            raf.close();
        }
        catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }

    }



}
