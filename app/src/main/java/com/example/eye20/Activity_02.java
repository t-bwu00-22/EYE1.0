package com.example.eye20;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

/**
 * 模式选择界面
 */
public class Activity_02 extends Activity {
    private Button mybutton0=null;
    private Button mybutton1=null;
    private Button mybutton2=null;
    private TextView mytext0=null;
    private TextView mytext1=null;
    private TextView mytext2=null;
    private TextView mytext3=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);

        mybutton0=(Button)findViewById(R.id.Button0);
        mybutton1=(Button)findViewById(R.id.Button1);
        mybutton2=(Button)findViewById(R.id.Button2);
        mytext0=(TextView)findViewById(R.id.text0);
        mytext1=(TextView)findViewById(R.id.text1);
        mytext2=(TextView)findViewById(R.id.text2);
        mytext3=(TextView)findViewById(R.id.text3);

        mybutton0.setOnClickListener(new MyButton0Listener());
        mybutton1.setOnClickListener(new MyButton1Listener());
        mybutton2.setOnClickListener(new MyButton2Listener());



    }

    /**
     * 强力模式按钮监控
     * 点击按钮将强力模式数据传输
     * 数值可修改
     * data1：温度，data2：气压，data3：震动频率，data4：定时（单位min）
     */
    class MyButton0Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String data1="30",data2="中",data3="中",data4="10";
            DataFile.datafile_0("datafile1",data1,Activity_02.this);
            DataFile.datafile_0("datafile2",data2,Activity_02.this);
            DataFile.datafile_0("datafile3",data3,Activity_02.this);
            DataFile.datafile_0("datafile4",data4,Activity_02.this);
        }
    }

    /**
     * 轻柔模式按钮监控
     * 点击按钮将轻柔模式数据传输
     * 数值可修改
     * data1：温度，data2：气压，data3：震动频率，data4：定时（单位min）
     */
    class MyButton1Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String data1="28",data2="低",data3="中",data4="10";
            DataFile.datafile_0("datafile1",data1,Activity_02.this);
            DataFile.datafile_0("datafile2",data2,Activity_02.this);
            DataFile.datafile_0("datafile3",data3,Activity_02.this);
            DataFile.datafile_0("datafile4",data4,Activity_02.this);
        }
    }

    /**
     * 轻柔模式按钮监控
     * 点击按钮将轻柔模式数据传输
     * 数值可修改
     * data1：温度，data2：气压，data3：震动频率，data4：定时（单位min）
     */
    class MyButton2Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String data1="26",data2="低",data3="弱",data4="10";
            DataFile.datafile_0("datafile1",data1,Activity_02.this);
            DataFile.datafile_0("datafile2",data2,Activity_02.this);
            DataFile.datafile_0("datafile3",data3,Activity_02.this);
            DataFile.datafile_0("datafile4",data4,Activity_02.this);
        }
    }


}

