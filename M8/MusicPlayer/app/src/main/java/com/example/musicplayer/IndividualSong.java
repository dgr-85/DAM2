package com.example.musicplayer;

import android.net.Uri;

public class IndividualSong {
    private Uri uri;
    private String title;
    private String artist;
    private String path;

    private int duration;

    public IndividualSong(Uri uri, String title, String artist, String path, int duration) {
        this.uri = uri;
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.duration = duration;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        int seconds = duration / 1000;
        int minutes = seconds / 60;
        int modSeconds = seconds % 60;
        return "Title='" + title + '\'' +
                ", Artist='" + artist + '\'' +
                ", Duration=" + String.format("%d:%02d", minutes, modSeconds);
    }
}
