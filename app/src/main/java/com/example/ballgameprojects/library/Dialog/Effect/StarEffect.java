package com.example.ballgameprojects.library.Dialog.Effect;

import com.example.ballgameprojects.library.Dialog.FadeNameDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;

/* スターエフェクト */
public class StarEffect extends FadeNameDialog {
    /* field */
    private FADE_DG_ENUM	state;
    private int				frameCnt;
    private Vector2         speed;

    public StarEffect(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void FadeIn() {
        // TODO フェードイン
        if(this.parts.color.FadeInAlpha(0.5f))
            this.state = FADE_DG_ENUM.FADE_DG_WAIT;
    }

    @Override
    public void Wait() {
        // TODO 待機
        if(++this.frameCnt >= 30)
            this.state = FADE_DG_ENUM.FADE_DG_FADEOUT;
    }

    @Override
    public void FadeOut() {
        // TODO フェードアウト
        if(this.parts.color.FadeOutAlpha(0.05f))
            this.delFlag = true;
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.frameCnt = 0;
        this.state = FADE_DG_ENUM.FADE_DG_FADEIN;
        this.speed = new Vector2();
        this.parts.color.SetColor(new GameColor(1,1,1,1));
        this.SetFadeAlpha();
        this.SetScale(new Vector2(render.GetScreenWidth() * 0.4f,render.GetScreenHeight() * 0.4f));
        this.SetAngle(0.0f);
        this.SetDialogName(new String("star"));
    }

    public void SetStarPosition(Vector2 dpos, int number)
    {
        // TODO 位置設定
        this.SetPos(new Vector2(dpos.x, dpos.y));

        float ag = (number * 120.0f) + 30.0f;
        ag = ag * 3.14f / 180.0f;
        this.speed.SetVector((float)Math.cos(ag) * (this.render.GetScreenWidth() * 0.005f),
                (float)Math.sin(ag) * (this.render.GetScreenHeight() * 0.005f));
    }

    @Override
    public void Step() {
        // TODO アクション
        this.parts.pos.Plus(this.speed);
        this.parts.angle += 5.0f;

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
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
