package com.example.ballgameprojects.library.Dialog.Parts;

import com.example.ballgameprojects.library.Dialog.BackgroundDialog;
import com.example.ballgameprojects.library.Dialog.EffectDialog;
import com.example.ballgameprojects.library.Dialog.UIParts2D;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;

/* 外壁クラス */
public class OutSide extends BackgroundDialog {
    /* field */
    public Vector2 outsidePos, offset;
    public EffectDialog[] jibaEffects;

    public OutSide(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    /* getter */
    public UIParts2D getParts(){	return this.parts;	}

    @Override
    public void Init() {
        // TODO 初期化
        this.parts.pos.SetVector(render.GetScreenWidth() * 0.5f, render.GetScreenHeight() * 0.5f);
        this.parts.scale.SetVector(render.GetScreenWidth(), render.GetScreenHeight() * 0.8f);
        this.SetDialogName("checkbox");

        this.outsidePos = new Vector2(this.parts.scale.x * 0.45f, this.parts.scale.y * 0.45f);
        this.offset = new Vector2(5,10);

        this.Input_jibaEffect();
    }

    public void Input_jibaEffect()
    {
        // TODO エフェクト追加
        float wh = render.GetScreenWidth(), ht = render.GetScreenHeight();
        this.jibaEffects = new EffectDialog[4];
        for(int i=0; i<4; i++)
        {
            this.jibaEffects[i] = new EffectDialog(render);
            this.jibaEffects[i].SetRender(render);
            this.jibaEffects[i].SetDialogName("jibaEffect");
            this.jibaEffects[i].SetAnimation(1, 10, 10, 3, true);
            this.jibaEffects[i].SetColor(new GameColor(1,1,1,0));
            switch(i)
            {
                case 0:	this.jibaEffects[i].SetObject(new Vector2(wh * 0.5f, ht * 0.125f), 0.0f, new Vector2(wh, ht * 0.1f));	break;
                case 1:	this.jibaEffects[i].SetObject(new Vector2(wh * 0.95f, ht * 0.5f), 90.0f, new Vector2(wh * 0.1f, ht * 0.8f));	break;
                case 2:	this.jibaEffects[i].SetObject(new Vector2(wh * 0.5f, ht * 0.875f), 0.0f, new Vector2(wh, ht * 0.1f));	break;
                case 3:	this.jibaEffects[i].SetObject(new Vector2(wh * 0.05f, ht * 0.5f), 90.0f, new Vector2(wh * 0.1f, ht * 0.8f));	break;
            }
        }
    }

    @Override
    public void Step() {
        // TODO アクション
        for(int i=0; i<4; i++)
            this.jibaEffects[i].Step();
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogDraw();

        for(int i=0; i<4; i++)
            this.jibaEffects[i].Draw();
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
