package com.example.ballgameprojects.library.Dialog.Parts;

import com.example.ballgameprojects.library.Dialog.FadeNameDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Vector.Vector2;

/* チェーンボールクラス */
public class ChildrenBall extends FadeNameDialog {
    public ChildrenBall(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
    }

    @Override
    public void Init() {
        // TODO 初期化
        float wh = render.GetScreenWidth(), ht = render.GetScreenHeight();
        this.SetScale(new Vector2(wh * 0.1f, ht * 0.1f));
        this.SetDialogName(new String("ball"));

    }

    @Override
    public void FadeIn() {
        // TODO フェードイン

    }

    @Override
    public void Wait() {
        // TODO 待機

    }

    @Override
    public void FadeOut() {
        // TODO フェードアウト

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
        this.DialogDraw();
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
