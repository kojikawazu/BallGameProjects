package com.example.ballgameprojects.library.Dialog.Effect;

import com.example.ballgameprojects.library.Dialog.FadeNameDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;

/* 吹き出しエフェクト */
public class SpeechEffect extends FadeNameDialog  {
    /* field */
    private FadeNameDialog.FADE_DG_ENUM state;
    private int				frameCnt, interval, saveInterval;
    private Vector2         speed;

    public SpeechEffect(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.frameCnt = 0;
        this.interval = this.saveInterval = 10;
        this.state = FadeNameDialog.FADE_DG_ENUM.FADE_DG_FADEIN;
        this.speed = new Vector2(new Vector2(render.GetScreenWidth() * 0.3f / 10,render.GetScreenHeight() * 0.3f / 10));
        this.parts.color.SetColor(new GameColor(1,1,1,1));
        this.SetScale(new Vector2(0,0));
        this.SetAngle(0.0f);
        this.SetDialogName(new String("fukidasi"));
    }

    public void SetPos(Vector2 vec)
    {
        // TODO 位置の設定
        this.parts.pos = vec ;
    }

    @Override
    public void FadeIn() {
        // TODO フェードイン
        this.parts.scale.x += this.speed.x;
        this.parts.scale.y += this.speed.y;
        --this.interval;

        if( this.interval <= 0 )
            this.state = FadeNameDialog.FADE_DG_ENUM.FADE_DG_WAIT;
    }

    @Override
    public void Wait() {
        // TODO 待機処理
        if(++this.frameCnt % 30 == 0)
        {
            this.state = FadeNameDialog.FADE_DG_ENUM.FADE_DG_FADEOUT;
            this.frameCnt = 0;
        }
    }

    @Override
    public void FadeOut() {
        // TODO フェードアウト
        if( this.delFlag )	return ;

        this.parts.scale.x -= this.speed.x;
        this.parts.scale.y -= this.speed.y;
        if( ++this.interval >= this.saveInterval )
            this.delFlag = true;

    }

    @Override
    public void Step() {
        // TODO アクション
        switch(state)
        {
            case FADE_DG_FADEIN:
                this.FadeIn();
                break;
            case FADE_DG_WAIT:
                this.Wait();
                break;
            case FADE_DG_FADEOUT:
                this.FadeOut();
                break;
        }
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogDraw();
        this.NameTagDraw();
    }

    @Override
    public void Del() {
        // TODO 削除
    }
}
