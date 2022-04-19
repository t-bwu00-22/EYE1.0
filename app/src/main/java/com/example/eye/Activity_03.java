package com.example.eye;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import music.LocalAudioListActivity;

public class Activity_03 extends Activity {
    private SeekBar seekbar0=null;
    private SeekBar seekbar1=null;
    private SeekBar seekbar2=null;
    private SeekBar seekbar3=null;
    int temperature;     //温度
    String pressure;        //气压
    String frequency;       //频率
    int time;            //时间


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_03);

        temperature = Integer.parseInt(getResources().getString(R.string.temperature));
        pressure = getResources().getString(R.string.pressure);
        frequency = getResources().getString(R.string.frequency);
        time = Integer.parseInt(getResources().getString(R.string.time));
        String tem=String.format(String.valueOf(temperature), 25);
        String pre=String.format(String.valueOf(pressure), "低");
        String fre=String.format(String.valueOf(frequency), "弱");
        String tim=String.format(String.valueOf(time), 0);

        SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar0);
        final TextView seekbarValue = (TextView) findViewById(R.id.text2);

        SeekBar seekbar_1 = (SeekBar)findViewById(R.id.seekBar1);
        final TextView seekbarValue_1 = (TextView) findViewById(R.id.text6);

        SeekBar seekbar_2 = (SeekBar)findViewById(R.id.seekBar2);
        final TextView seekbarValue_2 = (TextView) findViewById(R.id.text11);

        SeekBar seekbar_3 = (SeekBar)findViewById(R.id.seekBar3);
        final TextView seekbarValue_3 = (TextView) findViewById(R.id.text16);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekbar, int progress,
                                          boolean fromUser) {
                progress=progress+25;
                /*String sAgeFormat = getResources().getString(R.string.temperature);
                String sFinalAge = String.format(sAgeFormat, progress);
                System.out.println(sFinalAge);

                 */
                temperature=progress;

                // TODO Auto-generated method stub
                seekbarValue.setText(String.valueOf(progress));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                //isSeekBarChanging = false;
                seekbar.setProgress(seekBar.getProgress());
            }
        });

        seekbar_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekbar_1, int progress,
                                          boolean fromUser) {
                if(progress==0) {
                    // TODO Auto-generated method stub
                    seekbarValue_1.setText("低");
                    pressure="低";
                }
                else if(progress==1) {
                    seekbarValue_1.setText("中");
                    pressure="中";
                }
                else {
                    seekbarValue_1.setText("高");
                    pressure="高";
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


        seekbar_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekbar_2, int progress,
                                          boolean fromUser) {
                if(progress==0) {
                    // TODO Auto-generated method stub
                    seekbarValue_2.setText("弱");
                    frequency="弱";
                }
                else if(progress==1) {
                    seekbarValue_2.setText("中");
                    frequency="中";
                }
                else {
                    seekbarValue_2.setText("强");
                    frequency="强";
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


        seekbar_3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekbar_3, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekbarValue_3.setText(String.valueOf(progress));
                time=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        Intent intent = new Intent();//只用来携带被传递的值，不跳转活动

        intent.putExtra("temperature",temperature);
        setResult(RESULT_OK,intent);



    }

    public void creatfile(){
        File dirFirstFolder = new File("ceshi");//方法二：通过变量文件来获取需要创建的文件夹名字
        if(!dirFirstFolder.exists())
        { //如果该文件夹不存在，则进行创建
            dirFirstFolder.mkdirs();//创建文件夹

        }

    }
/*
    public void save()  {

        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //设置文件名称，以及存储方式：MODE_PRIVATE  覆盖的  MODE_APPEND  不覆盖的
            out = openFileOutput("DIY", Context.MODE_PRIVATE);
            //创建一个OutputStreamWriter对象，传入BufferedWriter的构造器中
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //向文件中写入数据
            writer.write(temperature);
            writer.write(pressure);
            writer.write(frequency);
            writer.write(time);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*
        FileInputStream in=null;
        in=openFileInput("DIY");
        System.out.println(in.getChannel());


    }
    */

}
