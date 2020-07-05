package com.example.ballgameprojects.library.Vector;

/* 2次元ベクトルクラス */
public class Vector2 {
    /* field */
    public float x,y;

    public Vector2() {
        // TODO コンストラクタ
        x=y=0;
    }

    public Vector2(float dx, float dy) {
        // TODO コンストラクタ(float型)
        x=dx;
        y=dy;
    }

    public Vector2 Vector2(float f, float g) {
        // TODO コンストラクタ return Vector2型
        x = f;
        y = g;
        return this;
    }

    public Vector2(Vector2 vec) {
        // TODO コンストラクタ(Vector2型)
        x=vec.x;
        y=vec.y;
    }

    public Vector2 Plus(float val) {
        // TODO 加算(float型)
        x += val;
        y += val;
        return this;
    }

    public Vector2 Plus(Vector2 val) {
        // TODO 加算(Vector2型)
        x += val.x;
        y += val.y;
        return this;
    }

    public Vector2 Sub(float val) {
        // TODO 減算(float型)
        x -= val;
        y -= val;
        return this;
    }

    public Vector2 Sub(Vector2 val) {
        // TODO 減算(Vector2型)
        x -= val.x;
        y -= val.y;
        return this;
    }

    public float Dot(Vector2 val) {
        // TODO 内積(Vector2型)
        return (x*val.x + y*val.y);
    }

    public float Closs(Vector2 val) {
        // TODO 外積(Vector2型)
        return ( x * val.y - val.x * y);
    }

    public float Length() {
        // TODO ベクトルの長さ
        return (float)( Math.sqrt(x*x+y*y) );
    }

    public float Length2th() {
        // TODO (ベクトルの長さの2倍)
        return (x*x+y*y);
    }

    public Vector2 Normalize() {
        // TODO ベクトルの正規化
        float len = this.Length();
        x /= len;
        y /= len;
        return this;
    }

    public Vector2 SetVector(float dx, float dy) {
        // TODO ベクトルの設定
        x = dx;
        y = dy;
        return this;
    }

    public Vector2 SetVector(Vector2 vec) {
        // TODO ベクトルの設定
        x = vec.x;
        y = vec.y;
        return this;
    }

    public Vector2 Init() {
        // TODO ベクトルの初期化
        x = 0;
        y = 0;
        return this;
    }
}
