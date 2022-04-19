package com.example.eye;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;


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
       /*View v = findViewById(R.id.content);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(0);
       */

        mybutton0.setOnClickListener(new Activity_02.MyButton0Listener());
        mybutton1.setOnClickListener(new Activity_02.MyButton1Listener());
        mybutton2.setOnClickListener(new Activity_02.MyButton2Listener());



    }

    class MyButton0Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            File dirFirstFolder = new File("ceshi");//方法二：通过变量文件来获取需要创建的文件夹名字
            if(!dirFirstFolder.exists())
            { //如果该文件夹不存在，则进行创建
                dirFirstFolder.mkdirs();//创建文件夹
                System.out.println("文件创建成功");
            }


        }
    }

    class MyButton1Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }

    class MyButton2Listener implements View.OnClickListener {
        private ro.serialno.Serial Serial;

        @Override
        public void onClick(View v) {
            Context application = null;
            String serialno = Serial.getSerialNumber(application);
            System.out.println(serialno);
        }
    }


}

