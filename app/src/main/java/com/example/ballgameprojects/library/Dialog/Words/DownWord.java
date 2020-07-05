package com.example.ballgameprojects.library.Dialog.Words;

import com.example.ballgameprojects.library.Dialog.FadeNameDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Vector.Vector4;

/* ダウン文字クラス */
public class DownWord extends FadeNameDialog {
    /* field */
    public int divX, divY, wordNumber, bound, boundSpd, boundCnt, boundTime, frameCnt;

    public DownWord(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    public void SetBoundingWord(int divX, int divY, int number)
    {
        // TODO 文字の設定
        this.divX = divX;
        this.divY = divY;
        this.wordNumber = number;
    }

    public void SetBounding(int spd, int cnt, int timing)
    {
        // TODO アクション設定
        this.boundTime = timing;
        this.bound = this.boundSpd = spd;
        this.boundCnt = cnt * 2;
    }

    @Override
    public void FadeIn() {
        // TODO フェードイン
        this.parts.color.FadeInAlpha(0.1f);

        this.parts.pos.y += this.bound;
        this.bound++;
        if(++this.frameCnt >= this.boundCnt)
        {
            this.state = FADE_DG_ENUM.FADE_DG_WAIT;
            this.frameCnt = 0;
        }
    }

    @Override
    public void Wait() {
        // TODO 待機
        if(++this.frameCnt >= this.boundTime)
        {
            this.state = FADE_DG_ENUM.FADE_DG_FADEOUT;
            this.frameCnt = 0;
            this.bound = 0;
        }
    }

    @Override
    public void FadeOut() {
        // TODO フェードアウト
        this.parts.color.FadeOutAlpha(0.05f);
        this.parts.pos.y += this.bound;
        this.bound++;
        if(++this.frameCnt >= this.boundCnt)
            this.delFlag = true;
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.divX = this.divY = this.wordNumber = 0;
        this.boundCnt = this.boundSpd = this.boundTime = this.frameCnt = 0;
        this.parts.color.SetColor(new Vector4(1,1,0,0));
        this.parts.color.SetFadeAlpha();
    }

    @Override
    public void Step() {
        // TODO アクション
        switch(this.state)
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
        this.DialogAnimationDraw(this.divX, this.divY, this.wordNumber);
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
