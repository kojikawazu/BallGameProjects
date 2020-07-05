package com.example.ballgameprojects.library.Dialog.Words;

import com.example.ballgameprojects.library.Dialog.NameParts.ChainName;
import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;

/* 終了文字ダイアログ */
public class FinishWord extends NameTagDialog {
    /* field */
    public enum GAMEFINISH_DG_ENUM
    {
        GAMEFINISH_DG_FADEIN, GAMEFINISH_DG_WAIT, GAMEFINISH_DG_FADEOUT
    }

    private GAMEFINISH_DG_ENUM	state;
    private int					frameCnt;
    private ChainName           chainDialog;

    public FinishWord(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        float wd = this.render.GetScreenWidth(), ht = this.render.GetScreenHeight();
        this.SetObject(new Vector2(wd * 0.2f, ht * 0.5f), 0.0f,
                new Vector2(wd * 20, ht * 0.2f));
        this.SetColor(new GameColor(0.5f, 0, 0, 0));
        this.parts.color.SetFadeAlpha();

        this.state = GAMEFINISH_DG_ENUM.GAMEFINISH_DG_FADEIN;
        this.frameCnt = 0;

        this.chainDialog = new ChainName(render);
        BoundingWord dg;
        for(int i=0; i<9; i++)
        {
            dg = new BoundingWord(render);
            dg.SetDialogName(new String("finish"));
            dg.SetObject(new Vector2(wd * 0.5f - (wd * 0.3f) + i * (wd * 0.075f), ht * 0.5f), 0.0f,
                    new Vector2(wd * 0.1f, ht * 0.1f));
            dg.SetBoundingWord(6, 1, i);
            dg.SetBounding((int)(ht * 0.02f), (int)(ht * 0.02f), 20);

            this.chainDialog.Add(dg);
        }
    }

    @Override
    public void Step() {
        // TODO アクション
        this.chainDialog.Step();
        switch(this.state)
        {
            case GAMEFINISH_DG_FADEIN:
                this.FadeIn();
                break;
            case GAMEFINISH_DG_WAIT:
                this.Wait();
                break;
            case GAMEFINISH_DG_FADEOUT:
                this.FadeOut();
                break;
        }
        this.chainDialog.Del();
    }

    private void FadeIn()
    {
        // TODO フェードイン
        this.parts.pos.x += (this.render.GetScreenWidth() * 0.025f);
        if( this.parts.color.FadeInAlpha(0.1f) )
            this.state = GAMEFINISH_DG_ENUM.GAMEFINISH_DG_WAIT;
    }

    private void Wait()
    {
        // TODO 待機
        this.parts.pos.x += (this.render.GetScreenWidth() * 0.001f);
        if( ++this.frameCnt >= 120)
        {
            this.frameCnt = 0;
            this.state = GAMEFINISH_DG_ENUM.GAMEFINISH_DG_FADEOUT;
        }
    }

    private void FadeOut()
    {
        // TODO フェードアウト
        this.parts.pos.x += (this.render.GetScreenWidth() * 0.025f);
        if( this.parts.color.FadeOutAlpha(0.1f) )
        {
            this.delFlag = true;
            this.chainDialog.Clear();
        }
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogDraw();
        this.chainDialog.Draw();
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
