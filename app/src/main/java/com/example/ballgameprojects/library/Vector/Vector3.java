package com.example.ballgameprojects.library.Vector;

/* 3次元ベクトルクラス */
public class Vector3 {
    /* field */
    public float x,y,z;

    public Vector3() {
        // TODO コンストラクタ
        x=y=z=0;
    }

    public Vector3(float dx, float dy, float dz) {
        // TODO コンストラクタ(float型)
        x=dx;
        y=dy;
        z=dz;
    }

    public Vector3 Vector3(float f, float g, float h) {
        // TODO コンストラクタ(float型) return Vector3型
        x = f;
        y = g;
        z = h;
        return this;
    }

    public Vector3(Vector3 vec) {
        // TODO コンストラクタ(Vector3型)
        x=vec.x;
        y=vec.y;
        z=vec.z;
    }

    public Vector3 Plus(float val) {
        // TODO 加算(float型)
        x += val;
        y += val;
        z += val;
        return this;
    }

    public Vector3 Plus(Vector3 val) {
        // TODO 加算(Vector3型)
        x += val.x;
        y += val.y;
        z += val.z;
        return this;
    }

    public Vector3 Sub(float val) {
        // TODO 減算(float型)
        x -= val;
        y -= val;
        z -= val;
        return this;
    }

    public Vector3 Sub(Vector3 val) {
        // TODO 減算(Vector3型)
        x -= val.x;
        y -= val.y;
        z -= val.z;
        return this;
    }

    public float Dot(Vector3 val) {
        // TODO 内積
        return (x*val.x + y*val.y + z*val.z);
    }

    public Vector3 Closs(Vector3 val) {
        // TODO 外積
        return Vector3( z * val.y - val.z * y,
                z * val.x - val.z * x,
                x * val.y - val.x * y);
    }

    public float Length() {
        // TODO ベクトルの長さ
        return (float)( Math.sqrt(x*x+y*y+z*z) );
    }

    public float Length2th() {
        // TODO ベクトルの長さの2倍
        return (x*x+y*y+z*z);
    }

    public Vector3 Normalize() {
        // TODO ベクトルの正規化
        float len = this.Length();
        x /= len;
        y /= len;
        z /= len;
        return this;
    }

    public Vector3 SetVector(float dx, float dy, float dz) {
        // TODO ベクトルの設定(float型)
        x = dx;
        y = dy;
        z = dz;
        return this;
    }

    public Vector3 SetVector(Vector3 vec) {
        // TODO ベクトルの設定(Vector3型)
        x = vec.x;
        y = vec.y;
        z = vec.z;
        return this;
    }

    public Vector3 Init() {
        // TODO ベクトルの初期化
        x = 0;
        y = 0;
        z = 0;
        return this;
    }
}
