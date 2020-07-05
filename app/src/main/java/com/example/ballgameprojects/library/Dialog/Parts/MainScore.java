package com.example.ballgameprojects.library.Dialog.Parts;

import com.example.ballgameprojects.library.Dialog.NameParts.ScoreTag;
import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Vector.Vector2;

/* スコアクラス */
public class MainScore extends NameTagDialog {
    /* field */
    public enum SCORE_ENUM
    {
        SCORE_FADEIN, SCORE_WAIT, SCORE_FADEOUT
    }

    SCORE_ENUM			state;
    ScoreTag            score, damage;
    ScorePin	        pinDialog;
    public int			frameCnt;
    private Vector2 backScaleSpd, tagScaleSpd;

    public MainScore(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.SetObject(new Vector2(render.GetScreenWidth() * 0.5f, render.GetScreenHeight() * 0.5f), 0.0f,
                new Vector2(0,0));
        this.SetDialogName("scorebox");
        this.SetName(new String(""));
        this.parts.color.SetFadeAlpha();

        this.score = new ScoreTag(render);
        this.score.SetObject(new Vector2(render.GetScreenWidth() * 0.75f, render.GetScreenHeight() * 0.55f), 0.0f,
                new Vector2(0,0));
        this.score.SetFadeAlpha();

        this.damage = new ScoreTag(render);
        this.damage.SetObject(new Vector2(render.GetScreenWidth() * 0.75f, render.GetScreenHeight() * 0.47f), 0.0f,
                new Vector2(0,0));
        this.damage.SetFadeAlpha();

        this.state = SCORE_ENUM.SCORE_FADEIN;
        this.frameCnt = 0;
        this.pinDialog = null;
        this.backScaleSpd = new Vector2(render.GetScreenWidth() * 0.8f / 10, render.GetScreenHeight() * 0.4f / 10);
        this.tagScaleSpd = new Vector2(render.GetScreenWidth() * 0.05f / 10, render.GetScreenHeight() * 0.05f / 10);

    }

    public void SetScore(int dscore, int ddamage)
    {
        // TODO スコアの設定
        this.score.SetScore(dscore);
        this.damage.SetScore(ddamage);
    }

    public void SetFadeOut()
    {
        // TODO フェードアウト設定
        if( this.state != SCORE_ENUM.SCORE_FADEOUT)
            this.state = SCORE_ENUM.SCORE_FADEOUT;

        if(this.pinDialog != null)
            this.pinDialog.SetFadeOut();
    }

    @Override
    public void Step() {
        // TODO アクション
        if(this.pinDialog != null)
            this.pinDialog.Step();

        switch(this.state)
        {
            case SCORE_FADEIN:
                this.FadeIn();
                break;
            case SCORE_WAIT:
                this.Wait();
                break;
            case SCORE_FADEOUT:
                this.FadeOut();
                break;
        }
    }

    private void FadeIn()
    {
        // TODO フェードイン
        Vector2 dp = new Vector2(this.tagScaleSpd.x,this.tagScaleSpd.y);
        this.AddScl(new Vector2(this.backScaleSpd.x,this.backScaleSpd.y));
        this.score.AddScl(dp);
        this.damage.AddScl(dp);

        this.damage.FadeIn(0.1f);
        this.score.FadeIn(0.1f);

        if( this.parts.color.FadeInAlpha(0.1f) )
            this.state = SCORE_ENUM.SCORE_WAIT;
    }

    private void Wait()
    {
        // TODO 待機
        if(this.frameCnt++ == 0)
        {
            int pin = 0;
            if( this.score.score <= 30)			pin = 3;
            else if(this.score.score <= 60)		pin = 2;
            else if(this.score.score <= 90)		pin = 1;
            else								pin = 0;

            this.pinDialog = new ScorePin(this.render);
            this.pinDialog.SetRunk(pin);
        }
    }

    private void FadeOut()
    {
        // TODO フェードアウト
        Vector2 dp = new Vector2(this.tagScaleSpd.x,this.tagScaleSpd.y);
        this.SubScl(new Vector2(this.backScaleSpd.x,this.backScaleSpd.y));
        this.score.SubScl(dp);
        this.damage.SubScl(dp);

        this.score.FadeOut(0.1f);
        this.damage.FadeOut(0.1f);
        if( this.parts.color.FadeOutAlpha(0.1f) )
        {
            this.delFlag = true;
            this.pinDialog = null;
        }
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogDraw();
        this.score.Draw();
        this.damage.Draw();

        if(this.pinDialog != null)
            this.pinDialog.Draw();
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
