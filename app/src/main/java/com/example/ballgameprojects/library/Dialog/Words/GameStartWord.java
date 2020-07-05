package com.example.ballgameprojects.library.Dialog.Words;

import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;

/* ゲームスタートタグ */
public class GameStartWord  extends NameTagDialog {
    /* field */
    public enum GAMESTART_DG_ENUM
    {
        GAMESTART_DG_FADEIN, GAMESTART_DG_WAIT, GAMESTART_DG_FADEOUT
    }

    private GAMESTART_DG_ENUM	state;
    private int					frameCnt;
    private GameColor backcolor;

    public GameStartWord(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.SetObject(new Vector2(this.render.GetScreenWidth() * 0.2f, this.render.GetScreenHeight() * 0.5f), 0.0f,
                new Vector2(this.render.GetScreenWidth() * 0.6f, this.render.GetScreenHeight() * 0.3f));
        this.SetColor(new GameColor(1, 1, 1, 1));
        this.SetName(new String("gamestart"));
        this.parts.color.SetFadeAlpha();

        this.backcolor = new GameColor(0.5f,0,0,0);
        this.backcolor.SetFadeAlpha();
        this.state = GAMESTART_DG_ENUM.GAMESTART_DG_FADEIN;
        this.frameCnt = 0;
    }

    @Override
    public void Step() {
        // TODO アクション
        switch(this.state)
        {
            case GAMESTART_DG_FADEIN:
                this.FadeIn();
                break;
            case GAMESTART_DG_WAIT:
                this.Wait();
                break;
            case GAMESTART_DG_FADEOUT:
                this.FadeOut();
                break;
        }
    }

    private void FadeIn()
    {
        // TODO フェードイン
        this.parts.pos.x += (this.render.GetScreenWidth() * 0.025f);
        this.backcolor.FadeInAlpha(0.1f);
        if( this.parts.color.FadeInAlpha(0.1f) )
            this.state = GAMESTART_DG_ENUM.GAMESTART_DG_WAIT;
    }

    private void Wait()
    {
        // TODO 待機
        this.parts.pos.x += (this.render.GetScreenWidth() * 0.001f);
        if( ++this.frameCnt >= 120)
        {
            this.frameCnt = 0;
            this.state = GAMESTART_DG_ENUM.GAMESTART_DG_FADEOUT;
        }

    }

    private void FadeOut()
    {
        // TODO フェードアウト
        this.parts.pos.x += (this.render.GetScreenWidth() * 0.025f);
        this.backcolor.FadeOutAlpha(0.1f);
        if( this.parts.color.FadeOutAlpha(0.1f) )
            this.delFlag = true;
    }

    @Override
    public void Draw() {
        // TODO 描画
        Vector2 dp = new Vector2(this.parts.pos.x, this.parts.pos.y);
        Vector2 ds = new Vector2(this.parts.scale.x, this.parts.scale.y);
        GameColor col = new GameColor(this.parts.color);

        this.parts.scale.SetVector(this.render.GetScreenWidth() * 20, this.parts.scale.y * 0.2f);
        this.parts.color.SetColor(backcolor.x, backcolor.y, backcolor.z, backcolor.w);
        this.DialogDraw();

        this.parts.pos.SetVector(dp);
        this.parts.scale.SetVector(ds);
        this.parts.color.SetColor(col.x, col.y, col.z, col.w);
        this.NameTagDraw();
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
