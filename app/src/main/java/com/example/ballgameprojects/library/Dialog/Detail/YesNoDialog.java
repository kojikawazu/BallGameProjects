package com.example.ballgameprojects.library.Dialog.Detail;

import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Vector.Vector2;

/* はい、いいえダイアログ */
public class YesNoDialog extends NameTagDialog {
    /* field */
    public enum YESNO_ENUM
    {
        YESNO_FADEIN, YESNO_DG_WAIT, YESNO_DG_FADEOUT
    }

    public enum YESNO_SCORE
    {
        YESNO_NONE, YESNO_YES, YESNO_NO,
    }

    private int frameCnt;
    public YESNO_ENUM state;
    public YESNO_SCORE	score;
    private NameTagDialog yes, no;
    private Vector2 backScaleSpd, tagScaleSpd;

    public YesNoDialog(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.SetObject(new Vector2(render.GetScreenWidth() * 0.5f, render.GetScreenHeight() * 0.5f), 0.0f,
                new Vector2(0,0));
        this.SetDialogName("yesno");
        this.SetName(new String(""));
        this.parts.color.SetFadeAlpha();

        this.yes = new NameTagDialog(render);
        this.yes.SetObject(new Vector2(render.GetScreenWidth() * 0.3f, render.GetScreenHeight() * 0.6f), 0.0f,
                new Vector2(0,0));
        this.yes.SetDialogName("plate");
        this.yes.SetName(new String(""));
        this.yes.SetFadeAlpha();

        this.no = new NameTagDialog(render);
        this.no.SetObject(new Vector2(render.GetScreenWidth() * 0.7f, render.GetScreenHeight() * 0.6f), 0.0f,
                new Vector2(0,0));
        this.no.SetDialogName("plate");
        this.no.SetName(new String(""));
        this.no.SetFadeAlpha();

        this.state = YESNO_ENUM.YESNO_FADEIN;
        this.score = YESNO_SCORE.YESNO_NONE;
        this.frameCnt = 0;
        this.backScaleSpd = new Vector2(render.GetScreenWidth() * 0.8f / 10, render.GetScreenHeight() * 0.4f / 10);
        this.tagScaleSpd = new Vector2(render.GetScreenWidth() * 0.2f / 10, render.GetScreenHeight() * 0.05f / 10);
    }

    @Override
    public void Step() {
        // TODO アクション
        switch(this.state)
        {
            case YESNO_FADEIN:
                this.FadeIn();
                break;
            case YESNO_DG_WAIT:
                this.Wait();
                break;
            case YESNO_DG_FADEOUT:
                this.FadeOut();
                break;
        }
    }

    private void FadeIn()
    {
        // TODO フェードイン
        Vector2 dp = new Vector2(this.tagScaleSpd.x,this.tagScaleSpd.y);
        this.AddScl(new Vector2(this.backScaleSpd.x,this.backScaleSpd.y));
        this.yes.AddScl(dp);
        this.no.AddScl(dp);

        this.yes.FadeIn(0.1f);
        this.no.FadeIn(0.1f);

        if( this.parts.color.FadeInAlpha(0.1f) )
            this.state = YESNO_ENUM.YESNO_DG_WAIT;
    }

    private void Wait()
    {
        // TODO 待機
        if( this.yes.IsTouchBoundingbox() )
        {
            this.score = YESNO_SCORE.YESNO_YES;
            this.state = YESNO_ENUM.YESNO_DG_FADEOUT;
        }
        else if( this.no.IsTouchBoundingbox() )
        {
            this.score = YESNO_SCORE.YESNO_NO;
            this.state = YESNO_ENUM.YESNO_DG_FADEOUT;
        }

    }

    private void FadeOut()
    {
        // TODO フェードアウト
        Vector2 dp = new Vector2(this.tagScaleSpd.x,this.tagScaleSpd.y);
        this.SubScl(new Vector2(this.backScaleSpd.x,this.backScaleSpd.y));
        this.yes.SubScl(dp);
        this.no.SubScl(dp);

        this.yes.FadeOut(0.1f);
        this.no.FadeOut(0.1f);
        if( this.parts.color.FadeOutAlpha(0.1f) )
            this.delFlag = true;
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogDraw();
        this.yes.DialogDraw();
        this.no.DialogDraw();
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
