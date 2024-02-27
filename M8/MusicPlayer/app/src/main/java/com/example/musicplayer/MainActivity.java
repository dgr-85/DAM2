package com.example.musicplayer;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 1;
    ListView lvSongs;
    List<IndividualSong> allSongs;

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSongs = findViewById(R.id.songList);
        allSongs = new ArrayList<>();
        ArrayAdapter<IndividualSong> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allSongs);
        lvSongs.setAdapter(adapter);

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        checkPermissions();
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_AUDIO)) {
                // permission was granted, yay! Do the external storage task you need to do.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_AUDIO}, MY_PERMISSIONS_REQUEST);
                // MY_PERMISSIONS_REQUEST is an app-defined int constant. The callback method gets the result of the request.
            }
        } else {
            loadSongs();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean mediaAudioPermission = false;
        boolean externalStoragePermission = false;

        if (requestCode == MY_PERMISSIONS_REQUEST) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // permission was granted, yay! Do the external storage task you need to do.
                loadSongs();
            }
        }
    }

    public void loadSongs() {
        ContentResolver contentResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        List<IndividualSong> songsList = new ArrayList<>();

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.RELATIVE_PATH,
                MediaStore.Audio.Media.DURATION
        };
        try (Cursor cursor = getApplicationContext().getContentResolver().query(
                musicUri,
                projection,
                null,
                null,
                null
        )) {
            int colId=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
            int colTitle=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
            int colArtist=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
            int colPath=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.RELATIVE_PATH);
            int colDuration=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    long id=cursor.getLong(colId);
                    String title = cursor.getString(colTitle);
                    String artist = cursor.getString(colArtist);
                    String path = cursor.getString(colPath);
                    int duration=cursor.getInt(colDuration);

                    Uri contentUri= ContentUris.withAppendedId(musicUri,id);
                    allSongs.add(new IndividualSong(contentUri,title,artist,path,duration));
                }
            }
        }

    }
}