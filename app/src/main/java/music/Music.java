package music;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.FileUtils;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Music  extends MusicStart{








/*
    public static List<MusicStart> list;

    public static MusicStart musicstart;


    public static List<MusicStart> getmusic(Context context) {

        list = new ArrayList<>();


        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                , null, null, null, MediaStore.Audio.AudioColumns.IS_MUSIC);

        if (cursor != null) {
            while (cursor.moveToNext()) {

                musicstart = new Music();
                musicstart.song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                musicstart.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                musicstart.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                musicstart.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

//                把歌曲名字和歌手切割开
                if (musicstart.size > 1000 * 800) {
                    if (musicstart.song.contains("-")) {
                        String[] str = musicstart.song.split("-");
                        musicstart.singer = str[0];
                        musicstart.song = str[1];
                    }
                    list.add(musicstart);
                }

            }
        }

        cursor.close();
        return list;

    }



    //    转换歌曲时间的格式
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            String tt = time / 1000 / 60 + ":0" + time / 1000 % 60;
            return tt;
        } else {
            String tt = time / 1000 / 60 + ":" + time / 1000 % 60;
            return tt;
        }
    }


 */

}
