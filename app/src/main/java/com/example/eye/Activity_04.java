package com.example.eye;

import android.app.TabActivity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_04 extends TabActivity {


    private MediaPlayer mediaPlayer;//媒体播放器
    private ToggleButton playButton;
    private Button replayButton ;
    private boolean isCellPlay;/*在挂断电话的时候，用于判断是否为是来电时中断*/
    private boolean isSeekBarChanging;//互斥变量，防止进度条与定时器冲突。
    private int currentPosition;//当前音乐播放的进度
    private SeekBar seekBar;
    private Timer timer;
    private TextView textView1_total_time;

    private static final String TAG = "MediaActivity";


    TabHost tabHost;
    private RadioButton radiobutton0, radiobutton1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity_04,
                tabHost.getTabContentView(), true);


        /*音乐时间
        timeTV.setText(formatime(music.lengrh));
        //获得音乐时长
        totalTime=music.lengrh;
         */

        /* 以上创建和添加标签页也可以用如下代码实现 */
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("播放界面").setContent(R.id.tab01));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("歌单列表").setContent(R.id.tab02));


        //标签切换事件处理，setOnTabChangedListener
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            // tabId是newTabSpec参数设置的tab页名，并不是layout里面的标识符id
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {   //第一个标签
                    Toast.makeText(Activity_04.this, "点击播放界面", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab2")) {   //第二个标签
                    Toast.makeText(Activity_04.this, "点击歌单列表", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //实例化媒体播放器
        mediaPlayer = new MediaPlayer();

        //监听滚动条事件
        seekBar = (SeekBar) findViewById(R.id.playSeekBar);
        seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) new MySeekBar());

        // 监听[播放或暂停]事件
        playButton= (ToggleButton) findViewById(R.id.Button3);
        playButton.setOnClickListener(new PalyListener());


        //监听[重播]事件
        replayButton= (Button) findViewById(R.id.Button4);
        replayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.reset();
                currentPosition = 0;
                play();
            }
        });
/*
        //监听来电事件
        TelephonyManager phoneyMana = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        phoneyMana.listen(new myPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);


 */

    }




    /*销毁时释资源*/
    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        //timer.cancel();
        timer = null;
        mediaPlayer = null;
        super.onDestroy();
    }

    /*播放或暂停事件处理*/
    private class PalyListener implements View.OnClickListener {
        public void onClick(View v) {
            if(playButton.getText().toString().equals("播放"))
            {
                currentPosition = mediaPlayer.getCurrentPosition();//记录播放的位置
                mediaPlayer.stop();//暂停状态
                playButton.setText(R.string.stop);
                //timer.purge();//移除所有任务;
            }
            else{
                mediaPlayer.reset();
                play();
            }
        }
    }

    /*播放处理*/
    private void play() {
        File media = new File("text.mp3");//由于是练习，就把mp3名称固定了
        Log.i(TAG, media.getAbsolutePath());
        if(media.exists())
        {
            try {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置音频类型
                mediaPlayer.setDataSource(media.getAbsolutePath());//设置mp3数据源
                mediaPlayer.prepareAsync();//数据缓冲
                /*监听缓存 事件，在缓冲完毕后，开始播放*/
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        mp.seekTo(currentPosition);
                        playButton.setText(R.string.play);
                        seekBar.setMax(mediaPlayer.getDuration());
                    }
                });
                //监听播放时回调函数
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(!isSeekBarChanging){
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        }
                    }
                },0,50);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), R.string.playError, Toast.LENGTH_LONG).show();
                e.printStackTrace();
                System.out.println(e);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.fileError, Toast.LENGTH_LONG).show();
        }
    }

    /*来电事件处理*/
 /*   private class myPhoneStateListener extends PhoneStateListener
    {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING://来电，应当停止音乐
                    if(mediaPlayer.isPlaying() && playButton.getText().toString().equals("播放")){
                        currentPosition = mediaPlayer.getCurrentPosition();//记录播放的位置
                        mediaPlayer.stop();
                        isCellPlay = true;//标记这是属于来电时暂停的标记
                        playButton.setText(R.string.stop);
                        timer.purge();//移除定时器任务;
                    }
                    break;
                case TelephonyManager.CALL_STATE_IDLE://无电话状态
                    if(isCellPlay){
                        isCellPlay = false;
                        mediaPlayer.reset();
                        play();
                    }
                    break;
            }
        }
    }
    */

    /*进度条处理*/
    public class MySeekBar implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
        }

        /*滚动时,应当暂停后台定时器*/
        public void onStartTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = true;
        }
        /*滑动结束后，重新设置值*/
        public void onStopTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = false;
            mediaPlayer.seekTo(seekBar.getProgress());
        }
    }


/*
    //时间转换类，将得到的音乐时间毫秒转换为时分秒格式
    private String formatime(int lengrh) {
        Date date = new Date(lengrh);
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        String totalTime = sdf.format(date);
        return totalTime;
    }


 */



}
