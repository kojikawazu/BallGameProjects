package com.example.ballgameprojects.library.Music;

import android.media.MediaPlayer;

import com.example.ballgameprojects.MainActivity;

/* ゲームミュージッククラス */
public class GameMusic implements  MediaPlayer.OnCompletionListener{
    /* field */
    private MediaPlayer media;

    public GameMusic()
    {
        // TODO コンストラクタ

    }

    public void LoadMusic(MainActivity ac, int musicpoint)
    {
        try {
            // TODO: 音楽のロード
            media = MediaPlayer.create(ac, musicpoint);
            media.setLooping(false);
            media.setOnCompletionListener(this);
            media.setVolume(0.5f, 0.5f);
        }catch (Exception e) {
            // TODO: exception
        }
    }

    public void SetLoop(boolean isLoop)
    {
        // TODO: 音楽のループ設定
        media.setLooping(isLoop);
    }

    public void SetVolume(float vol)
    {
        // TODO: 音楽の音量設定
        media.setVolume(vol, vol);
    }

    public void Play()
    {
        // TODO: 音楽の再生
        media.seekTo(0);
        media.start();
    }

    public void Pause(){

    }

    public void Stop()
    {
        // TODO: 音楽の停止
        try{
            if( media != null ){
                media.stop();
                media.setOnCompletionListener(null);
                media.release();
                media = null;
            }
        }catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // TODO: 音楽の停止を受け取った時の処理
        if(media == null) return ;
        media.release();
        media = null;
    }
}
