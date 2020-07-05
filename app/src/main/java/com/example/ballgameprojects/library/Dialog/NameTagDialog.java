package com.example.ballgameprojects.library.Dialog;

import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.Graph2D;
import com.example.ballgameprojects.library.Vector.Vector2;

/* 継承ダイアログ */
public class NameTagDialog extends SuperDialog {
    /* field */
    private String nameTagName;
    protected Vector2 nameScalePercent;

    public NameTagDialog(GLRenderer in) {
        // TODO コンストラクタ
        super(in);

        this.parts.pos.SetVector(0,0);
        this.parts.scale.SetVector(render.GetScreenWidth(), render.GetScreenHeight());
        this.nameScalePercent = new Vector2(0.8f, 0.8f);
        this.SetDialogName("background");
    }

    @Override
    public void Init() {
        // TODO 初期化
    }

    public void SetName(String in)
    {
        // TODO 名前の設定
        nameTagName = in;
    }

    @Override
    public void Step() {
        // TODO アクション

    }

    public void NameTagDraw()
    {
        // TODO 詳細描画
        Graph2D gh = render.GetGraph(this.nameTagName);
        if( gh != null)
        {
            gh.SetColor(parts.color);
            gh.DrawCenter2D(new Vector2(parts.pos.x + offsetPosX, parts.pos.y + offsetPosY), parts.angle,
                    new Vector2(this.parts.scale.x * this.nameScalePercent.x + offsetSclX,
                            this.parts.scale.y * this.nameScalePercent.y + offsetSclY) );
        }
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogBackGroundDraw();
        this.NameTagDraw();
    }

    @Override
    public void Del() {
        // TODO 削除
    }
}
