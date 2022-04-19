package com.example.eye;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import file.DataFile;
import file.DisPlay;
import ro.serialno.DeviceIdUtil;
import ro.serialno.Serial;

public class MainActivity extends AppCompatActivity {
    private Button mybutton0=null;
    private Button mybutton1=null;
    private Button mybutton2=null;
    private Button mybutton3=null;
    private Button mybutton4=null;
    private Button mybutton5=null;
    private ToggleButton togglebutton=null;
    private TextView mytextview0=null;
    private TextView mytextview1=null;
    private TextView mytextview2=null;
    private TextView mytextview3=null;
    private TextView mytextview4=null;
    private TextView mytextview5=null;
    private TextView mytextview6=null;
    private TextView mytextview7=null;
    private TextView mytextview8=null;
    private TextView mytextview9=null;
    private TextView mytextview10=null;
    private Switch bluetoothswitch=null;

    private String diydata=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mybutton0=(Button)findViewById(R.id.Button0);
        mybutton1=(Button)findViewById(R.id.Button1);
        mybutton2=(Button)findViewById(R.id.Button2);
        mybutton3=(Button)findViewById(R.id.Button3);
        mybutton4=(Button)findViewById(R.id.Button4);
        mybutton5=(Button)findViewById(R.id.Button5);
        togglebutton=(ToggleButton)findViewById(R.id.togglebutton);
        mytextview0=(TextView)findViewById(R.id.textView0);
        mytextview1=(TextView)findViewById(R.id.textView1);
        mytextview2=(TextView)findViewById(R.id.textView2);
        mytextview3=(TextView)findViewById(R.id.textView3);
        mytextview4=(TextView)findViewById(R.id.textView4);
        mytextview5=(TextView)findViewById(R.id.textView5);
        mytextview6=(TextView)findViewById(R.id.textView6);
        mytextview7=(TextView)findViewById(R.id.textView7);
        mytextview8=(TextView)findViewById(R.id.textView8);
        mytextview9=(TextView)findViewById(R.id.textView9);
        mytextview10=(TextView)findViewById(R.id.textView10);
        bluetoothswitch=(Switch)findViewById(R.id.switch1);

        mybutton0.setOnClickListener(new MyButton0Listener());
        mybutton1.setOnClickListener(new MyButton1Listener());
        mybutton2.setOnClickListener(new MyButton2Listener());
        mybutton3.setOnClickListener(new MyButton3Listener());
        mybutton4.setOnClickListener(new MyButton4Listener());
        mybutton5.setOnClickListener(new MyButton5Listener());
        togglebutton.setOnClickListener(new togglebuttonListener());
        bluetoothswitch.setOnClickListener(new bluetoothswitchListener());
//uuid
        save();
        String uuid=readFileData("Device_uuid");
        System.out.println(uuid);

//serial
        saveserial();
        String serialno=readFileData("serialno");
        System.out.println(serialno);

       // fileIsExists();

//DIY数据
        fileIsExists();

        DisPlay.display();
    }

    
    
//模式选择按钮监控
    class MyButton0Listener implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent0 = new Intent();
            intent0.setClass(MainActivity.this, Activity_02.class);
            MainActivity.this.startActivity(intent0);
        }
    }
//DIY设置按钮监控
    class MyButton1Listener implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent();
            intent1.setClass(MainActivity.this, Activity_03.class);
            MainActivity.this.startActivity(intent1);
        }
    }
//播放音乐按钮监控
    class MyButton2Listener implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent2 = new Intent();
            intent2.setClass(MainActivity.this, Activity_04.class);
            MainActivity.this.startActivity(intent2);
        }
    }
//分析报告按钮监控
    class MyButton3Listener implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent3 = new Intent();
            intent3.setClass(MainActivity.this, Activity_05.class);
            MainActivity.this.startActivity(intent3);
        }
    }
//新手教程按钮监控
    class MyButton4Listener implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent4 = new Intent();
            intent4.setClass(MainActivity.this, Activity_06.class);
            MainActivity.this.startActivity(intent4);
        }
    }
//音乐控制按钮监控
    class togglebuttonListener implements OnClickListener{
    private BluetoothAdapter adapter;
        @Override
        public void onClick(View v) {
            adapter  = BluetoothAdapter.getDefaultAdapter();
            boolean isenable = adapter.isEnabled();
            System.out.println(isenable + "<-=-=-=-isenable");
            //获取蓝牙是否可用
            //Toast.makeText(MainActivity.this, isenable + "<-=-=-=-isenable", Toast.LENGTH_SHORT).show();
            if(!adapter.isEnabled()) {
                /*Intent intent = new Intent();
                intent.setClass(MainActivity.this, BlueToothActivity.class);
                MainActivity.this.startActivity(intent);*/
                Toast.makeText(MainActivity.this, "蓝牙未连接", Toast.LENGTH_SHORT).show();
            }
            //togglebutton.getTextOff("暂停");
        }
    }

//疑难解答按钮监控
    class MyButton5Listener implements OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent5 = new Intent();
            intent5.setClass(MainActivity.this, Activity_07.class);
            MainActivity.this.startActivity(intent5);
        }
    }

//蓝牙开关
    class bluetoothswitchListener implements OnClickListener{
    private BluetoothAdapter adapter;
    @Override
    public void onClick(View v) {
        adapter  = BluetoothAdapter.getDefaultAdapter();
        boolean isenable = adapter.isEnabled();
        System.out.println(isenable + "<-=-=-=-isenable");
        //获取蓝牙是否可用
        Toast.makeText(MainActivity.this, isenable + "<-=-=-=-isenable", Toast.LENGTH_SHORT).show();
        if(!adapter.isEnabled()) {
            //Intent intent = new Intent();
            //intent.setClass(MainActivity.this, BlueToothActivity.class);
            //MainActivity.this.startActivity(intent);

            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            bluetoothAdapter.enable();

            //带有提示的
            /*Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);*/
        }
        else {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            bluetoothAdapter.disable();
        }
    }
}

    //创建Device_uuid文件
    public void save() {
        Context application = null;
        String deviceId = DeviceIdUtil.getDeviceId(application);
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //设置文件名称，以及存储方式
            out = openFileOutput("Device_uuid", Context.MODE_PRIVATE);
            //创建一个OutputStreamWriter对象，传入BufferedWriter的构造器中
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //向文件中写入数据
            writer.write(deviceId);

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

    //文件读取
    public String readFileData(String fileName){
        String result="";
        try{
            FileInputStream fis = openFileInput(fileName);
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


    //创建serial文件
    public void saveserial() {
        Context application = null;
        String serialno = Serial.getSerialNumber(application);
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //设置文件名称，以及存储方式
            out = openFileOutput("serialno", Context.MODE_PRIVATE);
            //创建一个OutputStreamWriter对象，传入BufferedWriter的构造器中
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //向文件中写入数据
            writer.write(serialno);

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
    public void datafile(String tn,String data) {
        String temperature="25",pressure="低",frequency="弱",time="0";
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //设置文件名称，以及存储方式
            out = openFileOutput(tn, Context.MODE_PRIVATE);
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


    //文件读取
    public String readFileData_1(String fileName){
        String result="";
        try{
            FileInputStream fis = openFileInput(fileName);
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

    //判断文件是否存在
    public void fileIsExists()
    {
        String strFile1="datafile1";
        String strFile2="datafile2";
        String strFile3="datafile3";
        String strFile4="datafile4";
        try
        {
            File f1=new File(strFile1);
            File f2=new File(strFile2);
            File f3=new File(strFile3);
            File f4=new File(strFile4);
            if(!f1.exists())
            {
               datafile("datafile1","25");
            }
            if(!f2.exists())
            {
                datafile("datafile2","低");
            }
            if(!f3.exists())
            {
                datafile("datafile3","弱");
            }
            if(!f4.exists())
            {
                datafile("datafile4","0");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        String data=readFileData_1(strFile3);
        if(data!=null)
            System.out.println(data);
        else
            System.out.println("错误");
    }



}