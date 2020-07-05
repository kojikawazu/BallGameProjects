package com.example.ballgameprojects.library.Dialog;

import com.example.ballgameprojects.library.Dialog.SuperDialog;
import com.example.ballgameprojects.library.GLRenderer;

/* エフェクト用ダイアログ */
public class EffectDialog extends SuperDialog {
    /* field */
    private boolean continueFlag;
    private int		animeCnt, animeMax, interval, divisionX, divisionY;
    public int		frameCnt;

    public EffectDialog(GLRenderer in) {
        super(in);
        // TODO コンストラクタ
        this.continueFlag = false;
        this.frameCnt = this.animeCnt = this.interval = this.divisionX = this.divisionY = 0;
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.parts.pos.SetVector(0,0);
        this.parts.scale.SetVector(render.GetScreenWidth(), render.GetScreenHeight());
        this.SetDialogName("background");
    }

    public void SetAnimation(int divX, int divY, int danimeMax, int dinter, boolean isContinue)
    {
        // TODO アニメーションの設定
        this.divisionX = divX;
        this.divisionY = divY;
        this.animeMax = danimeMax;
        this.interval = dinter;
        this.continueFlag = isContinue;
    }

    public void SetAnimationNumber(int anime)
    {
        // TODO アニメーション番号の設定
        this.animeCnt = anime;
    }

    public void Animation()
    {
        // アニメーションアクション
        if(this.delFlag)	return;

        if(++this.frameCnt % this.interval == 0)
        {
            if( ++this.animeCnt >= this.animeMax )
            {
                if( this.continueFlag )	this.animeCnt = 0;
                else
                {
                    this.animeCnt--;
                    this.delFlag = true;
                }
            }
        }

    }

    @Override
    public void Step() {
        // TODO 自動生成されたメソッド・スタブ
        this.Animation();
    }

    public void AnimationDraw()
    {
        this.DialogAnimationDraw(this.divisionX, this.divisionY, this.animeCnt);
    }

    @Override
    public void Draw() {
        // TODO 自動生成されたメソッド・スタブ
        this.render.setAddBlend();
        this.AnimationDraw();
        this.render.backtoBlend();
    }

    @Override
    public void Del() {
        // TODO 自動生成されたメソッド・スタブ

    }
}
