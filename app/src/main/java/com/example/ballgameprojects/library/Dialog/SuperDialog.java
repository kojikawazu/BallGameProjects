package com.example.ballgameprojects.library.Dialog;

import com.example.ballgameprojects.MainActivity;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Resource.Graph2D;
import com.example.ballgameprojects.library.Vector.Vector2;

/* ダイアログ基本クラス */
public abstract class SuperDialog {
    /* field */
    protected GLRenderer render;
    protected 	UIParts2D	parts;

    public boolean			touchFlag, delFlag;
    public float			touchMainX, touchMainY;
    protected int			offsetPosX, offsetPosY, offsetSclX, offsetSclY;

    public SuperDialog(GLRenderer in)
    {
        // TODO コンストラクタ
        render = in;
        this.SameInit();
    }

    public UIParts2D GetParts()
    {
        // TODO UIパーツの取得
        return this.parts;
    }

    public void SetRender(GLRenderer in)
    {
        // TODO レンダリングクラスの設定
        render = in;
    }

    private void SameInit()
    {
        // TODO 初期化
        parts = new UIParts2D();

        this.touchFlag = this.delFlag = false;
        this.touchMainX = this.touchMainY = 0;
        this.offsetPosX = this.offsetPosY = this.offsetSclX = this.offsetSclY = 0;
    }

    public void SetObject(Vector2 dpos, float dang, Vector2 dscl)
    {
        // TODO オブジェクトの設定
        parts.pos = dpos;
        parts.angle = dang;
        parts.scale = dscl;
    }

    public void SetColor(GameColor incolor)
    {
        // TODO カラーの設定
        parts.color = incolor;
    }

    public void SetPos(Vector2 dpos)
    {
        // TODO 位置の設定
        this.parts.pos = dpos;
    }

    public void SetScale(Vector2 dscl)
    {
        // TODO 倍率の設定
        this.parts.scale = dscl;
    }

    public void SetAngle(float dan)
    {
        // TODO 角度の設定
        this.parts.angle = dan;
    }

    public void AddPos(Vector2 dpos)
    {
        // TODO 位置の加算
        this.parts.pos.Plus(dpos);
    }

    public void SubPos(Vector2 dpos)
    {
        // TODO 位置の減算
        this.parts.pos.Sub(dpos);
    }

    public void AddScl(Vector2 dpos)
    {
        // TODO 倍率の加算
        this.parts.scale.Plus(dpos);
    }
    public void SubScl(Vector2 dpos)
    {
        // TODO 倍率の減算
        this.parts.scale.Sub(dpos);

        if(this.parts.scale.x < 0.0f)	this.parts.scale.x = 0.0f;
        if(this.parts.scale.y < 0.0f)	this.parts.scale.y = 0.0f;
    }

    public void SetFadeAlpha()
    {
        // TODO フェードの設定
        this.parts.color.SetFadeAlpha();
    }


    public void SetDialogName(String word)
    {
        // TODO ダイアログ名の設定
        parts.dialogName = word;
    }

    public void SetFade()
    {
        // TODO フェードの設定
        this.parts.color.SetFadeAlpha();
    }

    public boolean FadeIn(float spd)
    {
        // TODO フェードイン処理
        return this.parts.color.FadeInAlpha(spd);
    }

    public boolean FadeOut(float spd)
    {
        // TODO フェードアウト処理
        return this.parts.color.FadeOutAlpha(spd);
    }

    public boolean IsTouchBoundingbox()
    {
        // TODO タッチ処理の当たり判定処理
        MainActivity ac = render.getActivity();
        if( ac.touchCount <= 0 )	return (this.touchFlag = false);

        for(int i=0; i<ac.touchCount; i++)
        {
            if( ac.trgX[i] > this.parts.pos.x - this.parts.scale.x && ac.trgX[i] < this.parts.pos.x + this.parts.scale.x &&
                    ac.trgY[i] > this.parts.pos.y - this.parts.scale.y && ac.trgY[i] < this.parts.pos.y + this.parts.scale.y )
            {
                this.touchMainX = ac.trgX[i];
                this.touchMainY = ac.trgY[i];
                return (this.touchFlag = true);
            }
        }
        return (this.touchFlag = false);
    }

    public boolean IsTouchBoundingbox_ofObject(float dx, float dy, float scPercent)
    {
        // TODO オブジェクトとの当たり判定処理
        if( dx > this.parts.pos.x - this.parts.scale.x * scPercent && dx < this.parts.pos.x + this.parts.scale.x * scPercent &&
                dy> this.parts.pos.y - this.parts.scale.y * scPercent && dy < this.parts.pos.y + this.parts.scale.y * scPercent )
        {
            this.touchMainX = dx;
            this.touchMainY = dy;
            return (this.touchFlag = true);
        }
        return (this.touchFlag = false);
    }

    public boolean IstouchScreen()
    {
        // スクリーンタッチしたか判定
        MainActivity ac = render.getActivity();
        if( ac.touchCount <= 0 )	return (this.touchFlag = false);

        for(int i=0; i<ac.touchCount; i++)
        {
            if( ac.trgX[i] > 0.0f && ac.trgX[i] < ac.GetScreenWidth() &&
                    ac.trgY[i] > 0.0f && ac.trgX[i] < ac.GetScreenHeight() )
            {
                this.touchMainX = ac.trgX[i];
                this.touchMainY = ac.trgY[i];
                return (this.touchFlag = true);
            }
        }
        return (this.touchFlag = false);
    }

    public void DialogDraw()
    {
        // TODO ダイアログの描画
        Graph2D gh = render.GetGraph(parts.dialogName);
        if( gh != null)
        {
            gh.SetColor(parts.color);
            gh.DrawCenter2D(new Vector2(parts.pos.x + offsetPosX, parts.pos.y + offsetPosY), parts.angle,
                    new Vector2(parts.scale.x + offsetSclX, parts.scale.y + offsetSclY) );
        }
    }

    public void DialogBackGroundDraw()
    {
        // TODO ダイアログの背景描画
        Graph2D gh = render.GetGraph(parts.dialogName);
        if( gh != null)
        {
            gh.SetColor(parts.color);
            gh.Draw2D(parts.pos, parts.angle, parts.scale);
        }
    }

    public void DialogAnimationDraw(int divX, int divY, int animeNum)
    {
        // TODO ダイアログのアニメーション描画
        Graph2D gh = render.GetGraph(parts.dialogName);
        if( gh != null)
        {
            gh.SetColor(parts.color);
            gh.SetDivision(divX, divY);
            gh.DrawDiv2D(parts.pos, parts.angle, parts.scale, animeNum);
        }
    }

    /* 継承する時は以下を実装 */
    public abstract void Init();
    public abstract void Step();
    public abstract void Draw();
    public abstract void Del();
}
