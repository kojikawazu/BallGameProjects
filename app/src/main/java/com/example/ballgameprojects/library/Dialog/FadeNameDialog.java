package com.example.ballgameprojects.library.Dialog;

import com.example.ballgameprojects.library.GLRenderer;

public abstract class FadeNameDialog extends NameTagDialog  {
    /* field */
    public enum FADE_DG_ENUM
    {
        FADE_DG_FADEIN, FADE_DG_WAIT, FADE_DG_FADEOUT
    }

    public FADE_DG_ENUM state;

    public FadeNameDialog(GLRenderer in) {
        super(in);
        // TODO　コンストラクタ
        this.state = FADE_DG_ENUM.FADE_DG_FADEIN;
        this.Init();
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogDraw();
    }

    public abstract void Init();
    public abstract void Step();
    public abstract void Del();
    public abstract void FadeIn();
    public abstract void Wait();
    public abstract void FadeOut();
}
