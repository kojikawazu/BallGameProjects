package com.example.ballgameprojects.library.Dialog.Parts;

import com.example.ballgameprojects.library.Dialog.DialogList;
import com.example.ballgameprojects.library.Dialog.Effect.StarEffect;
import com.example.ballgameprojects.library.Dialog.EffectDialog;
import com.example.ballgameprojects.library.Dialog.FadeNameDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;

/*　スコアピン */
public class ScorePin extends FadeNameDialog {
    /* field */
    private FADE_DG_ENUM 		state;
    private EffectDialog        score;
    private DialogList          starList;
    private Vector2             backScaleSpd, tagScaleSpd;

    public ScorePin(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    public void SetFadeOut()
    {
        // TODO フェードアウトの設定
        this.state = FADE_DG_ENUM.FADE_DG_FADEOUT;
        this.starList.Clear();
    }

    @Override
    public void FadeIn() {
        // TODO フェードイン
        this.score.GetParts().color.FadeInAlpha(0.1f);
        this.score.AddScl(new Vector2(this.tagScaleSpd.x,this.tagScaleSpd.y));
        this.AddScl(new Vector2(this.backScaleSpd.x,this.backScaleSpd.y));
        if(this.parts.color.FadeInAlpha(0.1f))
        {
            this.state = FADE_DG_ENUM.FADE_DG_WAIT;

            for(int i=0; i<3; i++)
            {
                StarEffect ef = new StarEffect(render);
                ef.SetStarPosition(this.parts.pos, i);
                this.starList.Add(ef);
            }
        }
    }

    @Override
    public void Wait() {
        // TODO 待機

    }

    @Override
    public void FadeOut() {
        // TODO フェードアウト
        this.score.SubScl(new Vector2(this.tagScaleSpd.x,this.tagScaleSpd.y));
        this.SubScl(new Vector2(this.backScaleSpd.x,this.backScaleSpd.y));
        this.score.GetParts().color.FadeOutAlpha(0.1f);
        if(this.parts.color.FadeOutAlpha(0.1f))
            this.delFlag = true;
    }


    @Override
    public void Init() {
        // TODO 初期化
        this.state = FADE_DG_ENUM.FADE_DG_FADEIN;

        this.SetDialogName(new String("scorepin"));
        this.SetName(new String(""));
        this.SetObject(new Vector2(this.render.GetScreenWidth() * 0.75f, this.render.GetScreenHeight() * 0.75f),
                0.0f, new Vector2(0,0));
        this.SetColor(new GameColor(1,1,1,1));
        this.SetFadeAlpha();

        this.score = new EffectDialog(this.render);
        this.score.SetDialogName(new String("runk"));
        this.score.SetObject(new Vector2(this.render.GetScreenWidth() * 0.75f, this.render.GetScreenHeight() * 0.7f),
                0.0f, new Vector2(0,0));
        this.score.SetColor(new GameColor(1,1,1,1));
        this.score.SetFadeAlpha();
        this.score.SetAnimation(4, 1, 4, 5, true);

        this.backScaleSpd = new Vector2(render.GetScreenWidth() * 0.3f / 10, render.GetScreenHeight() * 0.3f / 10);
        this.tagScaleSpd = new Vector2(render.GetScreenWidth() * 0.1f / 10, render.GetScreenHeight() * 0.1f / 10);
        this.starList = new DialogList();
    }

    void SetRunk(int rk)
    {
        this.score.SetAnimationNumber(rk);
    }

    @Override
    public void Step() {
        // TODO アクション
        this.starList.Step();

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
        this.score.Draw();
        this.starList.Draw();
    }


    @Override
    public void Del() {
        // TODO 削除
    }
}
