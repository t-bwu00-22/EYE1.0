package music;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eye.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
//@RuntimePermissions
public class LocalAudioListActivity extends AppCompatActivity {
    private static final String TAG = LocalAudioListActivity.class.getSimpleName();

    private List<AudioInfo> mAudioInfos;
    private static final String[] sLocalAudioColumns = {
            MediaStore.Audio.Media._ID, // 音频id
            MediaStore.Audio.Media.DATA, // 音频路径
            MediaStore.Audio.Media.SIZE, // 音频字节大小
            MediaStore.Audio.Media.DISPLAY_NAME, // 音频名称 xxx.amr
            MediaStore.Audio.Media.TITLE, // 音频标题
            MediaStore.Audio.Media.DATE_ADDED, // 音频添加到MediaProvider的时间
            MediaStore.Audio.Media.DATE_MODIFIED, // 上次修改时间，该列用于内部MediaScanner扫描，外部不要修改
            MediaStore.Audio.Media.MIME_TYPE, // 音频类型 audio/3gp
            MediaStore.Audio.Media.TITLE_KEY,
            MediaStore.Audio.Media.DURATION, // 音频时长
            MediaStore.Audio.Media.BOOKMARK, // 上次音频的回放位置
            MediaStore.Audio.Media.ARTIST_ID, // 艺人id
            MediaStore.Audio.Media.ARTIST, // 艺人名称
            MediaStore.Audio.Media.ARTIST_KEY, // 用于对艺人搜索、排序、归类使用
            MediaStore.Audio.Media.ALBUM_ID, // 艺人专辑id
            MediaStore.Audio.Media.ALBUM, // 艺人专辑名称
            MediaStore.Audio.Media.ALBUM_KEY, // 用于对艺人专辑搜索、排序、归类使用
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.YEAR, // 录制音频的年份
            MediaStore.Audio.Media.IS_MUSIC, // 是否为音乐音频
            MediaStore.Audio.Media.IS_PODCAST,
            MediaStore.Audio.Media.IS_RINGTONE, // 是否为警告音频
            MediaStore.Audio.Media.IS_ALARM, // 是否为闹钟音频
            MediaStore.Audio.Media.IS_NOTIFICATION // 是否为通知音频
    };




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_audio_list);

        String property = "ro.serialno";
        String sn=getSerialNumber(property);

        initAudioData();

        ListView lvLocalAudioList = findViewById(R.id.lv_local_audio_list);
        lvLocalAudioList.setAdapter(new AudioAdapter(this, mAudioInfos));
    }

    /**
     * 获取设备号
     * @return
     * @param property
     */
    private String getSerialNumber(String property){
        String serial = null;

        try {
            Class c =Class.forName("android.os.SystemProperties");

            Method get =c.getMethod("get", String.class);

            serial = (String)get.invoke(c, "ro.serialno");

        } catch (Exception e) {
            e.printStackTrace();

        }

        return serial;

    }



    private static class AudioAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private List<AudioInfo> mAudioInfos;

        AudioAdapter(Context context, List<AudioInfo> audioInfos) {
            this.mInflater = LayoutInflater.from(context);
            this.mAudioInfos = audioInfos;
        }

        @Override
        public int getCount() {
            return mAudioInfos.size();
        }

        @Override
        public AudioInfo getItem(int position) {
            return mAudioInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AudioInfoHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.local_audio_list_item, parent, false);
                holder = new AudioInfoHolder();
                holder.tvArtist = convertView.findViewById(R.id.tv_artist);
                holder.tvAlbum = convertView.findViewById(R.id.tv_album);
                convertView.setTag(holder);
            } else {
                holder = (AudioInfoHolder) convertView.getTag();
            }

            AudioInfo audioInfo = getItem(position);
            holder.tvArtist.setText(audioInfo.artist);
            holder.tvAlbum.setText(audioInfo.album);

            return convertView;
        }

        private static final class AudioInfoHolder {
            TextView tvArtist;
            TextView tvAlbum;
        }
    }

    private void initAudioData() {
        mAudioInfos = new ArrayList<>();

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, sLocalAudioColumns,
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                AudioInfo audioInfo = new AudioInfo();

                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                long dateAdded = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
                long dateModified = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DATE_MODIFIED));
                String mimeType = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE));
                String titleKey = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE_KEY));
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                long bookmark = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.BOOKMARK));
                long artistId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String artistKey = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_KEY));
                long albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String albumKey = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_KEY));
                int track = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK));
                int year = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.YEAR));
                int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                int isPodcast = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_PODCAST));
                int isRingtone = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_RINGTONE));
                int isAlarm = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_ALARM));
                int isNotification = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_NOTIFICATION));

                audioInfo.id = id;
                audioInfo.data = data;
                audioInfo.size = size;
                audioInfo.displayName = displayName;
                audioInfo.title = title;
                audioInfo.dateAdded = dateAdded;
                audioInfo.dateModified = dateModified;
                audioInfo.mimeType = mimeType;
                audioInfo.titleKey = titleKey;
                audioInfo.duration = duration;
                audioInfo.bookmark = bookmark;
                audioInfo.artistId = artistId;
                audioInfo.artist = artist;
                audioInfo.artistKey = artistKey;
                audioInfo.albumId = albumId;
                audioInfo.album = album;
                audioInfo.albumKey = albumKey;
                audioInfo.track = track;
                audioInfo.year = year;
                audioInfo.isMusic = isMusic;
                audioInfo.isPodcast = isPodcast;
                audioInfo.isRingtone = isRingtone;
                audioInfo.isAlarm = isAlarm;
                audioInfo.isNotification = isNotification;

                Log.v(TAG, "audioInfo = " + audioInfo.toString());

                mAudioInfos.add(audioInfo);
            } while (cursor.moveToNext());

            cursor.close();
        }
    }

    private static final class AudioInfo {
        private int id;
        private String data;
        private long size;
        private String displayName;
        private String title;
        private long dateAdded;
        private long dateModified;
        private String mimeType;
        private String titleKey;
        private long duration;
        private long bookmark;
        private long artistId;
        private String artist;
        private String artistKey;
        private long albumId;
        private String album;
        private String albumKey;
        private int track;
        private int year;
        private int isMusic;
        private int isPodcast;
        private int isRingtone;
        private int isAlarm;
        private int isNotification;

        @Override
        public String toString() {
            return "AudioInfo{" +
                    "id=" + id +
                    ", data='" + data + '\'' +
                    ", size=" + size +
                    ", displayName='" + displayName + '\'' +
                    ", title='" + title + '\'' +
                    ", dateAdded=" + dateAdded +
                    ", dateModified=" + dateModified +
                    ", mimeType='" + mimeType + '\'' +
                    ", titleKey='" + titleKey + '\'' +
                    ", duration=" + duration +
                    ", bookmark=" + bookmark +
                    ", artistId=" + artistId +
                    ", artist='" + artist + '\'' +
                    ", artistKey='" + artistKey + '\'' +
                    ", albumId=" + albumId +
                    ", album='" + album + '\'' +
                    ", albumKey='" + albumKey + '\'' +
                    ", track=" + track +
                    ", year=" + year +
                    ", isMusic=" + isMusic +
                    ", isPodcast=" + isPodcast +
                    ", isRingtone=" + isRingtone +
                    ", isAlarm=" + isAlarm +
                    ", isNotification=" + isNotification +
                    '}';
        }
    }

}
