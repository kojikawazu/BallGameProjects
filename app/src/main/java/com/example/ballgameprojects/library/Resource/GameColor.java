package com.example.ballgameprojects.library.Resource;

import com.example.ballgameprojects.library.Vector.Vector3;
import com.example.ballgameprojects.library.Vector.Vector4;

/*
 * カラークラス
 * x : alpha
 * y : red
 * z : green
 * w : blue
 */
public class GameColor extends Vector4 {
    /* field */
    private Vector4 TargetColor;

    public GameColor()
    {
        // TODO コンストラクタ
        x=y=z=w=0;
        TargetColor = new Vector4();
        TargetColor.SetZeroVector();
    }

    public GameColor(float dx, float dy, float dz, float dw)
    {
        // TODO コンストラクタ(float型)
        x=dx;
        y=dy;
        z=dz;
        w=dw;
        TargetColor = new Vector4();
        TargetColor.SetZeroVector();
    }

    public GameColor(Vector4 vec)
    {
        // TODO コンストラクタ(Vector4型)
        x=vec.x;
        y=vec.y;
        z=vec.z;
        w=vec.w;
        TargetColor = new Vector4();
        TargetColor.SetZeroVector();
    }

    public GameColor(GameColor vec)
    {
        x=vec.x;
        y=vec.y;
        z=vec.z;
        w=vec.w;
        TargetColor = new Vector4();
        TargetColor.SetZeroVector();
    }

    public void SetColor(float dx, float dy, float dz, float dw)
    {
        // TODO setter(float型)
        x=dx;
        y=dy;
        z=dz;
        w=dw;
    }

    public void SetColor(Vector4 vec)
    {
        // TODO setter(Vector4型)
        x=vec.x;
        y=vec.y;
        z=vec.z;
        w=vec.w;
    }

    public void SetColor(GameColor vec)
    {
        // TODO setter(GameColor型)
        x=vec.x;
        y=vec.y;
        z=vec.z;
        w=vec.w;
    }

    public void SetColor(Vector3 col)
    {
        // TODO setter(Vector3型)
        // RGB用
        y = col.x;
        z = col.y;
        w = col.z;
    }

    public void SetAlpha(float al)
    {
        // TODO アルファ値の設定
        x = al;
    }

    public void SetFadeAlpha()
    {
        // TODO フェード用アルファ値 : 0へ変換
        Vector4 vec = new Vector4(x,y,z,w);
        TargetColor.SetVector( vec );
        x = 0.0f;
    }

    public void SetFadeColor()
    {
        // TODO フェード用アルファ値 : 0 色(0)へ変換
        Vector4 vec = new Vector4(x,y,z,w);
        TargetColor.SetVector( vec );
        this.SetZeroVector();
    }

    public boolean FadeInAlpha(float spd)
    {
        // TODO フェードイン処理
        x += spd;
        if( x >=  this.TargetColor.x )
        {
            x = this.TargetColor.x;
            return true;
        }
        return false;
    }

    public boolean FadeInColor(float spd)
    {
        // TODO フェードイン処理
        boolean hantei = true;
        x += spd;
        y += spd;
        z += spd;
        w += spd;

        if( x >= TargetColor.x )	x = TargetColor.x;
        else				        hantei = false;

        if( y >= TargetColor.y )	y = TargetColor.y;
        else				        hantei = false;

        if( z >= TargetColor.z )	z = TargetColor.z;
        else				        hantei = false;

        if( w >= TargetColor.w )	w = TargetColor.w;
        else				        hantei = false;

        return hantei;
    }

    public boolean FadeOutAlpha(float spd)
    {
        // TODO フェードアウト処理
        x -= spd;
        if( x <=  0.0f )
        {
            x = 0.0f;
            return true;
        }
        return false;
    }

    public boolean FadeOutColor(float spd)
    {
        // TODO フェードアウト処理
        boolean hantei = true;
        x -= spd;
        y -= spd;
        z -= spd;
        w -= spd;

        if( x <= 0.0f )	    x = 0.0f;
        else				hantei = false;

        if( y <= 0.0f )	    y = 0.0f;
        else				hantei = false;

        if( z <= 0.0f )	    z = 0.0f;
        else				hantei = false;

        if( w <= 0.0f )	    w = 0.0f;
        else				hantei = false;

        return hantei;
    }
}
