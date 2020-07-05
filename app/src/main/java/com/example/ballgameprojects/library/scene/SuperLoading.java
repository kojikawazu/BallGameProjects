package com.example.ballgameprojects.library.scene;

import com.example.ballgameprojects.library.Dialog.BackgroundDialog;

/* ローディング基本クラス */
public abstract class SuperLoading extends SuperScene {
    /* field */
    protected BackgroundDialog background;

    public SuperLoading(SceneManager in)
    {
        // TODO コンストラクタ
        super(in);
        background = new BackgroundDialog(this.GetManager().getRender());
    }

    /* 継承する時は以下を実装 */
    abstract public void Init();
    abstract public void Step();
    abstract public void Dispose();
    abstract public void Del();
    abstract public void Draw2D();
    abstract public void StopMusic();
    abstract public void Load();
}
