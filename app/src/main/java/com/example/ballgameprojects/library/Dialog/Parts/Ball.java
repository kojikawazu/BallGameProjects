package com.example.ballgameprojects.library.Dialog.Parts;

import com.example.ballgameprojects.MainActivity;
import com.example.ballgameprojects.library.Dialog.BackgroundDialog;
import com.example.ballgameprojects.library.Dialog.UIParts2D;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Vector.Vector2;

import java.util.Random;

/* ボール */
public class Ball extends BackgroundDialog {
    /* field */
    private boolean touched;
    private float ballSpd;
    private Vector2 speed, oldPos, wallOffset;

    public Ball(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        float wh = render.GetScreenWidth(), ht = render.GetScreenHeight();

        this.parts.pos.SetVector(wh * 0.5f, ht * 0.5f);
        this.parts.scale.SetVector(wh * 0.1f, ht * 0.1f);
        this.SetDialogName("bubbleface1");

        Random rd = new Random();
        int random = rd.nextInt(360);
        this.speed = new Vector2( (float)Math.cos(random), (float)Math.sin(random) );

        this.touched = false;
        this.oldPos = new Vector2();
        this.wallOffset = new Vector2(wh * 0.05f, ht * 0.05f);
        this.ballSpd = (int)Math.sqrt((double)((wh * 0.0075f) * (wh * 0.0075f) + (ht * 0.0075f) * (ht * 0.0075f)));
    }

    @Override
    public void Step() {
        // TODO アクション
        this.oldPos.SetVector(this.parts.pos);

        this.parts.pos.x += this.speed.x * this.ballSpd;
        this.parts.pos.y += this.speed.y * this.ballSpd;
    }

    public boolean IsAtackWall(OutSide dg)
    {
        // TODO 壁との当たり判定
        UIParts2D ui = dg.getParts();
        if( this.parts.pos.x + this.parts.scale.x - this.wallOffset.x> (ui.pos.x + dg.outsidePos.x - dg.offset.x ) )
        {
            this.parts.pos.x = (ui.pos.x + dg.outsidePos.x - dg.offset.x ) - (this.parts.scale.x - this.wallOffset.x);
            this.speed.x = -this.speed.x;
            return true;
        }

        if( (ui.pos.x - dg.outsidePos.x + dg.offset.x ) > this.parts.pos.x - this.parts.scale.x + this.wallOffset.x)
        {
            this.parts.pos.x = (ui.pos.x - dg.outsidePos.x + dg.offset.x ) + (this.parts.scale.x - this.wallOffset.x);
            this.speed.x = -this.speed.x;
            return true;
        }

        if( this.parts.pos.y + this.parts.scale.y - this.wallOffset.y > (ui.pos.y + dg.outsidePos.y - dg.offset.y ) )
        {
            this.parts.pos.y = (ui.pos.y + dg.outsidePos.y - dg.offset.y ) - (this.parts.scale.y - this.wallOffset.y);
            this.speed.y = -this.speed.y;
            return true;
        }

        if( (ui.pos.y - dg.outsidePos.y + dg.offset.y ) > this.parts.pos.y - this.parts.scale.y + this.wallOffset.y)
        {
            this.parts.pos.y = (ui.pos.y - dg.outsidePos.y + dg.offset.y ) + (this.parts.scale.y - this.wallOffset.y);
            this.speed.y = -this.speed.y;
            return true;
        }

        return false;
    }

    public boolean IsTouchBall()
    {
        // TODO ボールとの当たり判定
        MainActivity ac = this.render.getActivity();
        if( ac.touchCount <= 0 )	return (this.touched = false);

        Vector2 vecA = new Vector2(), vecB = new Vector2();
        for(int i=0; i<ac.touchCount; i++)
        {
            vecA.SetVector(ac.trgX[i] - this.parts.pos.x, ac.trgY[i] - this.parts.pos.y);
            if( vecA.Length() < this.parts.scale.x + (this.touched ? 50.0f : 100.0f) )
            {
                //if( this.touched )	return false;

                vecA.SetVector(ac.trgX[i] - this.oldPos.x, ac.trgY[i] - this.oldPos.y);
                vecA.Normalize();
                this.speed.x = -vecA.x;
                this.speed.y = -vecA.y;
                return (this.touched = true);
            }

            vecA.SetVector(ac.trgX[i] - this.oldPos.x, ac.trgY[i] - this.oldPos.y);
            vecB.SetVector(this.parts.pos.x - this.oldPos.x, this.parts.pos.y - this.oldPos.y);
            float vecLong = vecB.Length();
            vecB.Normalize();

            float Closs = vecA.Closs(vecB);
            if( Math.abs(Closs) < this.parts.scale.x )
            {
                float Dot = vecA.Dot(vecB);
                if( Dot > 0.0f && Dot < vecLong)
                {
                    if( this.touched )	return false;

                    this.parts.pos.SetVector(new Vector2(this.oldPos.x + vecB.x * Dot, this.oldPos.y + vecB.y * Dot));
                    this.speed.x = -this.speed.x;
                    this.speed.y = -this.speed.y;
                    this.touched = true;
                    return (this.touched = true);
                }
            }
        }
        return (this.touched = false);
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
