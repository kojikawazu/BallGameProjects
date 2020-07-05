package com.example.ballgameprojects.library.Vector;

/* 4次元ベクトルクラス */
public class Vector4 {
    /* field */
    public float x,y,z,w;

    public Vector4() {
        // TODO コンストラクタ
        x=y=z=w=0;
    }

    public Vector4(float dx, float dy, float dz, float dw) {
        // TODO コンストラクタ(float型)
        x=dx;
        y=dy;
        z=dz;
        w=dw;
    }

    public Vector4(Vector4 vec) {
        // TODO コンストラクタ(Vector4型)
        x=vec.x;
        y=vec.y;
        z=vec.z;
        w=vec.w;
    }

    public Vector4 Plus(float val) {
        // TODO 加算(float型)
        x += val;
        y += val;
        z += val;
        w += val;
        return this;
    }

    public Vector4 Plus(Vector4 val) {
        // TODO 加算(vector4型)
        x += val.x;
        y += val.y;
        z += val.z;
        w += val.w;
        return this;
    }

    public Vector4 Sub(float val) {
        // TODO 減算(float型)
        x -= val;
        y -= val;
        z -= val;
        w -= val;
        return this;
    }

    public Vector4 Sub(Vector4 val) {
        // TODO 減算(Vector4型)
        x -= val.x;
        y -= val.y;
        z -= val.z;
        w -= val.w;
        return this;
    }

    public Vector4 Dot(float val) {
        // TODO 内積(float型)
        x *= val;
        y *= val;
        z *= val;
        w *= val;
        return this;
    }

    public Vector4 Dot(Vector4 val) {
        // TODO 内積(Vector4型)
        x *= val.x;
        y *= val.y;
        z *= val.z;
        w *= val.w;
        return this;
    }

    public Vector4 Closs(float val) {
        // TODO 外積(float型)
        x /= val;
        y /= val;
        z /= val;
        w /= val;
        return this;
    }

    public Vector4 Closs(Vector4 val) {
        // TODO 外積(Vector4型)
        x /= val.x;
        y /= val.y;
        z /= val.z;
        w /= val.w;
        return this;
    }

    public float Length() {
        // TODO ベクトルの長さ
        return (float)( Math.sqrt(x*x+y*y+z*z+w*w) );
    }

    public float Length2th() {
        // TODO ベクトルの長さの2倍
        return (x*x+y*y+z*z+w*w);
    }

    public Vector4 Normalize() {
        // TODO ベクトルの正規化
        float len = this.Length();
        x /= len;
        y /= len;
        z /= len;
        w /= len;
        return this;
    }

    public Vector4 SetVector(float dx, float dy, float dz, float dw) {
        // TODO ベクトルの設定(float型)
        x = dx;
        y = dy;
        z = dz;
        w = dw;
        return this;
    }

    public Vector4 SetVector(Vector4 vec) {
        // TODO ベクトルの設定(Vector4型)
        x = vec.x;
        y = vec.y;
        z = vec.z;
        w = vec.w;
        return this;
    }

    public Vector4 SetZeroVector() {
        // TODO ベクトルの初期化
        x = 0;
        y = 0;
        z = 0;
        w = 0;
        return this;
    }
}
