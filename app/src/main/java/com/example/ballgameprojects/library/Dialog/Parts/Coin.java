package com.example.ballgameprojects.library.Dialog.Parts;

import com.example.ballgameprojects.library.Dialog.EffectDialog;
import com.example.ballgameprojects.library.GLRenderer;

/* コインクラス */
public class Coin extends EffectDialog {
    /* field */
    public enum COIN_COLOR
    {
        COLOR_NORMAL, COLOR_RED, COLOR_BLUE, COLOR_GLEEN, COLOR_BLACK,
    }
    public COIN_COLOR coinKnd;

    public Coin(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.parts.pos.SetVector(0,0);
        this.parts.angle = 0.0f;
        this.parts.scale.SetVector(render.GetScreenWidth() * 0.1f, render.GetScreenHeight() * 0.1f);
        this.SetDialogName("coin");
        this.parts.color.SetColor(1, 1, 1, 1);
        this.SetAnimation(4, 2, 8, 5, true);
    }

    public void SetVectorRandom()
    {
        // TODO 位置ランダム設定
        this.parts.pos.x = this.render.GetScreenWidth() * 0.2f +
                this.render.random.nextInt( (int)(this.render.GetScreenWidth() * 0.6f) );
        this.parts.pos.y = this.render.GetScreenHeight() * 0.2f +
                this.render.random.nextInt( (int)(this.render.GetScreenHeight() * 0.6f) );

        int col = this.render.random.nextInt(5);
        switch(col)
        {
            case 0:
                this.coinKnd = COIN_COLOR.COLOR_NORMAL;
                break;
            case 1:
                this.coinKnd = COIN_COLOR.COLOR_RED;
                this.parts.color.SetColor(1, 1, 0, 0);
                break;
            case 2:
                this.coinKnd = COIN_COLOR.COLOR_BLUE;
                this.parts.color.SetColor(1, 0, 0, 1);
                break;
            case 3:
                this.coinKnd = COIN_COLOR.COLOR_GLEEN;
                this.parts.color.SetColor(1, 0, 1, 0);
                break;
            case 4:
                this.coinKnd = COIN_COLOR.COLOR_BLACK;
                this.parts.color.SetColor(1, 0, 1, 0);
                break;
        }
    }

    @Override
    public void Step() {
        // TODO アクション
        this.Animation();

        if(this.frameCnt % 120 == 0)
            this.delFlag = true;
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.AnimationDraw();

    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
