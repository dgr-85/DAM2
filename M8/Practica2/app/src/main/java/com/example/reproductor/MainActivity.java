package com.example.reproductor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_EXTERNAL_STORAGE = 1;
    private MediaPlayer mediaPlayer;
    private Button playPauseButton;
    private List<Audio> audioList;
    private int posAudio;
    private SeekBar time;
    private TextView progress;
    private TextView duration;
    private boolean isRepeating = false;
    private boolean isRepeatAll = false;

    static class Audio {
        private final Uri uri;
        private final String title;
        private final String artist;
        private final String path;
        private final int duration;

        public Audio(Uri uri, String title, String artist, String path, int duration) {
            this.uri = uri;
            this.title = title;
            this.artist = artist;
            this.path = path;
            this.duration = duration;
        }

        @NonNull
        @Override
        public String toString() {
            int durationInSeconds = duration / 1000;
            int minutes = durationInSeconds / 60;
            int seconds = durationInSeconds % 60;

            String durationFormatted = String.format("%d:%02d", minutes, seconds);

            return  title + " - " + artist + " - " + durationFormatted;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView songListView = findViewById(R.id.songListView);

        audioList = new ArrayList<>();
        ArrayAdapter<Audio> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, audioList);
        songListView.setAdapter(adapter);

        songListView.setOnItemClickListener((parent, view, position, id) -> {
            posAudio = position;
            Audio selectedSong = audioList.get(position);
            playAudio(selectedSong);
            findViewById(R.id.controls).setVisibility(View.VISIBLE);
        });

        time = findViewById(R.id.time);
        progress = findViewById(R.id.progress);
        duration = findViewById(R.id.duration);

        playPauseButton = findViewById(R.id.playResource);
        Button stopButton = findViewById(R.id.stop);
        Button nextButton = findViewById(R.id.next);
        Button prevButton = findViewById(R.id.previous);
        Button repeatButton = findViewById(R.id.repeat);

        checkPermission();

        playPauseButton.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    playPauseButton.setBackgroundResource(R.drawable.pause);
                } else {
                    mediaPlayer.pause();
                    playPauseButton.setBackgroundResource(R.drawable.play);
                }
            }
        });

        stopButton.setOnClickListener(v -> {
            if (mediaPlayer != null){
                mediaPlayer.stop();
                playPauseButton.setBackgroundResource(R.drawable.play);
                findViewById(R.id.controls).setVisibility(View.GONE);
            }
        });

        nextButton.setOnClickListener(v -> next());

        prevButton.setOnClickListener(v -> prev());

        repeatButton.setOnClickListener(v -> {
            if (!isRepeating && !isRepeatAll) {
                isRepeating = true;
                repeatButton.setBackgroundResource(R.drawable.repeat_one);
            } else if (isRepeating && !isRepeatAll) {
                isRepeatAll = true;
                isRepeating = false;
                repeatButton.setBackgroundResource(R.drawable.repeat_on);
            } else {
                isRepeating = false;
                isRepeatAll = false;
                repeatButton.setBackgroundResource(R.drawable.repeat);
            }
        });
    }

    private void next(){
        if (posAudio < audioList.size() - 1) {
            posAudio++;
            Audio cancion = audioList.get(posAudio);
            playAudio(cancion);
        }
    }

    private void prev(){
        if (posAudio > 0){
            posAudio--;
            Audio cancion = audioList.get(posAudio);
            playAudio(cancion);
        }
    }

    private void loadResources(){
        Uri collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.RELATIVE_PATH,
                MediaStore.Audio.Media.DURATION
        };

        try (Cursor cursor = getApplicationContext().getContentResolver().query(
                collection,
                projection,
                null,
                null,
                null
        )) {
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
            int titleColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
            int artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
            int pathColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.RELATIVE_PATH);
            int durationColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);

            while (cursor.moveToNext()) {
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(titleColumn);
                String artist = cursor.getString(artistColumn);
                int duration = cursor.getInt(durationColumn);
                String path = cursor.getString(pathColumn);

                Uri contentUri = ContentUris.withAppendedId(collection, id);
                audioList.add(new Audio(contentUri, name, artist, path, duration));
            }
        }
    }

    private void playAudio(Audio audio) {
        try {
            if (mediaPlayer == null){
                mediaPlayer = new MediaPlayer();
            } else {
                mediaPlayer.reset();
            }
            mediaPlayer.setDataSource(getApplicationContext(), audio.uri);
            mediaPlayer.prepare();
            mediaPlayer.start();

            playPauseButton.setBackgroundResource(R.drawable.pause);

            time.setMax(audio.duration);
            time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        mediaPlayer.seekTo(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            duration.setText(formatTime(audio.duration));

            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            time.setProgress(mediaPlayer.getCurrentPosition());
                            progress.setText(formatTime(mediaPlayer.getCurrentPosition()));
                        }
                    });
                }
            }, 0, 1000);

            mediaPlayer.setOnCompletionListener(mp -> {
                if (isRepeating) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    playPauseButton.setBackgroundResource(R.drawable.pause);
                } else if (isRepeatAll) {
                    next();
                } else {
                    playPauseButton.setBackgroundResource(R.drawable.play);
                }
            });
        } catch (IOException e) {
            Log.e("playAudio", "Error al reproducir el audio", e);
        }
    }

    private String formatTime(int milliseconds){
        int durationInSeconds = milliseconds / 1000;
        int minutes = durationInSeconds / 60;
        int seconds = durationInSeconds % 60;

        return String.format("%d:%02d", minutes, seconds);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_EXTERNAL_STORAGE);
        } else {
            loadResources();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadResources();
            } else {
                Toast.makeText(this, "Permiso de almacenamiento denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}