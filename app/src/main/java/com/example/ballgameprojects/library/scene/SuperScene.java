package com.example.ballgameprojects.library.scene;

import com.example.ballgameprojects.library.GLRenderer;

/* シーン基本クラス */
public abstract class SuperScene{
    /* field */
    public int frameCnt;
    private GLRenderer _render;
    private SceneManager _manager;

    public SuperScene(SceneManager in)
    {
        // TODO コンストラクタ
        _manager = in;
        this.SetRender(_manager.getRender());
        frameCnt = 0;
    }

    public SceneManager GetManager(){	    return this._manager;	}
    public GLRenderer   GetRender(){		return this._render;	}

    public void SetRender(GLRenderer in)
    {
        // TODO レンダリングクラスの設定
        _render = in;
    }

    public boolean IncrementFrameCnt(int interval)
    {
        return (++this.frameCnt >= interval);
    }

    public boolean DecrementFrameCnt(int interval)
    {
        return (--this.frameCnt <= interval);
    }

    /* 継承する時は以下を実装 */
    abstract public void Init();
    abstract public void Step();
    abstract public void Dispose();
    abstract public void Del();
    abstract public void Draw2D();
    abstract public void PauseMusic();
    abstract public void StopMusic();
}
