package com.example.ballgameprojects.library.Music;

import java.util.ArrayList;

/* 音楽のリストクラス */
public class MusicList {
    /* field */
    private ArrayList<GameMusic> list;
    private int listSum;

    public MusicList()
    {
        // TODO コンストラクタ
        this.Clear();
        this.list = new ArrayList<GameMusic>();
    }

    public void Clear()
    {
        // TODO 初期化
        GameMusic music;
        for(int i=0; i<this.listSum; i++)
        {
            music = this.list.get(i);
            music.Stop();
            this.list.remove(i);
            music = null;
            this.listSum--;
        }
        this.listSum = 0;
    }

    public void Add(GameMusic in)
    {
        // TODO 音楽の追加
        this.list.add(in);
        this.listSum++;
    }

    public void Step()
    {
        // TODO 音楽の周期処理
        if(this.listSum >= 10)
        {
            int sub = this.listSum - 10;
            if(sub <= 0 || this.list.size() == 0)	return ;
            GameMusic music;
            for(int i=0; i<sub; i++)
            {
                music = this.list.get(0);
                music.Stop();
                this.list.remove(0);
                music = null;
            }
            this.listSum = 10;
        }
    }

}
