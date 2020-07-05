package com.example.ballgameprojects.library.Dialog.NameParts;

import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Vector.Vector2;

/* 移動拡大ネイムクラス */
public class NameTagMoveScale extends NameTagDialog {
    /* field */
    protected enum MOVESCALE_ACTIONS
    {
        TAG_MOVESCALE_PLUS, TAG_MOVESCALE_SUB,
    };

    public MOVESCALE_ACTIONS	state;
    public int					speedX, speedY, interCnt, interval;

    public NameTagMoveScale(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.speedX = 0;
        this.speedY = 0;
        this.interval = 0;
        this.state = MOVESCALE_ACTIONS.TAG_MOVESCALE_PLUS;
    }

    public void SetInterval(Vector2 end, int inter)
    {
        // TODO インターバル設定
        this.speedX  = (int)((end.x) / inter);
        this.speedY  = (int)((end.y) / inter);
        this.interCnt = this.interval = inter;
    }

    @Override
    public void Step() {
        // TODO アクション
        switch(state)
        {
            case TAG_MOVESCALE_PLUS:
                this.action_ScalePlus();
                break;
            case TAG_MOVESCALE_SUB:
                this.action_scaleSub();
                break;
        }
    }

    public void action_ScalePlus()
    {
        // TODO 移動、拡大アクション
        this.offsetSclX += this.speedX;
        this.offsetSclY += this.speedY;

        if( --this.interCnt <= 0 )
            this.state = MOVESCALE_ACTIONS.TAG_MOVESCALE_SUB;
    }

    public void action_scaleSub()
    {
        // TODO 縮小アクション
        this.offsetSclX -= this.speedX;
        this.offsetSclY -= this.speedY;

        if( ++this.interCnt >= this.interval )
            this.state = MOVESCALE_ACTIONS.TAG_MOVESCALE_PLUS;
    }


    @Override
    public void Draw() {
        // TODO 描画
        this.DialogBackGroundDraw();
        this.NameTagDraw();
    }
}
