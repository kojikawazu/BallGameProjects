package com.example.ballgameprojects.library.scene;

import com.example.ballgameprojects.Loading.LoadingScene;
import com.example.ballgameprojects.library.GLRenderer;

import java.util.ArrayList;

/* シーン管理クラス */
public class SceneManager {

    /* field */
    private int						sceneSum;
    private GLRenderer 				_render;
    private ArrayList<SuperScene>   sceneList;

    public SceneManager(GLRenderer in)
    {
        // TODO コンストラクタ
        _render = in;
        this.Init();
    }

    public GLRenderer getRender() {
        // TODO レンダリングクラスの取得
        return _render;
    }

    public void Init()
    {
        // TODO 最初の初期化
        sceneList = new ArrayList<SuperScene>();
        this.sceneSum = 0;
    }

    public void Step()
    {
        // TODO アクション
        SuperScene sc;
        for(int i=0; i<this.sceneSum; i++)
        {
            sc = sceneList.get(i);
            sc.Step();
            sc.Del();
        }
    }

    public void Draw()
    {
        // TODO 描画
        SuperScene sc;
        for(int i=0; i<this.sceneSum; i++)
        {
            sc = sceneList.get(i);
            sc.Draw2D();
        }
    }

    public boolean IsScene(){
        return (!sceneList.isEmpty());
    }

    public void Add(SuperScene in)
    {
        // TODO シーン追加
        this.sceneSum++;
        sceneList.add(in);
    }

    public void Insert(SuperScene in, int number)
    {
        // TODO シーン挿入
        this.sceneSum++;
        sceneList.add(number, in);
    }

    public void Insert(SuperScene in, SuperScene index)
    {
        // TODO シーン挿入
        this.sceneSum++;
        for(int i=0, siz=sceneList.size(); i<siz; i++)
        {
            if( (sceneList.get(i)) == index )
            {
                sceneList.add(i, in);
                break;
            }
        }
    }

    public SuperScene UnLink(int number)
    {
        // TODO シーンをアンリンク
        this.sceneSum--;
        return sceneList.remove(number);
    }

    public void ListClear()
    {
        // TODO シーンを全てアンリンク
        this.sceneSum = 0;
        sceneList.clear();
    }

    public void Dispose(int number)
    {
        // TODO シーンを対象からアンリンク
        this.sceneSum--;
        sceneList.get(number).Dispose();
        sceneList.remove(number);
    }

    public void Dispose(SuperScene in)
    {
        // TODO 対象のシーンをアンリンク
        SuperScene sc;
        for(int i=0, siz=sceneList.size(); i<siz; i++)
        {
            if( (sc = sceneList.get(i)) == in )
            {
                sc.Dispose();
                break;
            }
        }

        this.sceneSum--;
        sceneList.remove(in);
    }

    public void PauseMusic()
    {
        // TODO 音楽の再開
        SuperScene sc;
        for(int i=0, siz=sceneList.size(); i<siz; i++)
        {
            sc = sceneList.get(i);
            sc.PauseMusic();
        }
    }

    public void StopMusic()
    {
        // TODO 音楽の停止
        SuperScene sc;
        for(int i=0, siz=sceneList.size(); i<siz; i++)
        {
            sc = sceneList.get(i);
            sc.StopMusic();
        }
    }
}
