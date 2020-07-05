package com.example.ballgameprojects.library.Dialog;

import com.example.ballgameprojects.library.GLRenderer;

/* 背景型ダイアログ */
public class BackgroundDialog extends SuperDialog {

    public BackgroundDialog(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.parts.pos.SetVector(0,0);
        this.parts.scale.SetVector(render.GetScreenWidth(), render.GetScreenHeight());
        this.SetDialogName("test");
    }

    @Override
    public void Step() {
        // TODO アクション
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogBackGroundDraw();
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
