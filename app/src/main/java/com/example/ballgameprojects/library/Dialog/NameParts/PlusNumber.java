package com.example.ballgameprojects.library.Dialog.NameParts;

import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;

/* 加算ナンバークラス */
public class PlusNumber  extends NameTagDialog {
    /* field */
    public enum PLUS_ACTION
    {
        PLUS_ACTION_FADEIN, PLUS_ACTION_WAIT, PLUS_ACTION_FADEOUT
    }

    private ScoreTag	    scoreTag;
    private PLUS_ACTION		state;
    private int             frameCnt, upSpd;

    public PlusNumber(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.scoreTag = new ScoreTag(render);

        this.SetDialogName(new String(""));
        this.SetColor(new GameColor(1, 1, 1, 1));
        this.parts.color.SetFadeAlpha();

        this.state = PLUS_ACTION.PLUS_ACTION_FADEIN;
        this.frameCnt = 0;
        this.upSpd = (int)(this.render.GetScreenHeight() * 0.005f);
    }

    public void SetScore(int dscore)
    {
        // TODO スコアの設定
        if( dscore > 0 )
        {
            this.SetName(new String("plus"));
            this.SetColor(new GameColor(1, 0, 0, 1));
        }
        else
        {
            this.SetName(new String("sub"));
            this.SetColor(new GameColor(1, 1, 0, 0));
        }

        if(dscore < 0)	dscore = -dscore;
        this.parts.color.SetFadeAlpha();
        this.scoreTag.SetScore(dscore);
        this.scoreTag.SetObject(this.parts.pos, this.parts.angle, this.parts.scale);
    }

    @Override
    public void Step() {
        // TODO アクション
        this.parts.pos.y -= upSpd;
        switch(this.state)
        {
            case PLUS_ACTION_FADEIN:
                this.FadeIn();
                break;
            case PLUS_ACTION_WAIT:
                this.Wait();
            case PLUS_ACTION_FADEOUT:
                this.FadeOut();
                break;
        }
    }

    private void FadeIn()
    {
        // TODO フェードイン
        if( this.parts.color.FadeInAlpha(0.05f) )
            this.state = PLUS_ACTION.PLUS_ACTION_WAIT;
    }

    private void Wait()
    {
        // TODO 待機
        if( ++this.frameCnt >= 30)
        {
            this.frameCnt = 0;
            this.state = PLUS_ACTION.PLUS_ACTION_FADEOUT;
        }

    }

    private void FadeOut()
    {
        // TODO フェードアウト
        if( this.parts.color.FadeOutAlpha(0.05f) )
            this.delFlag = true;
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogBackGroundDraw();
        this.NameTagDraw();

        this.scoreTag.SetPos(new Vector2(this.parts.pos.x + 50.0f, this.parts.pos.y));
        this.scoreTag.SetColor(this.parts.color);
        this.scoreTag.Draw();
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
