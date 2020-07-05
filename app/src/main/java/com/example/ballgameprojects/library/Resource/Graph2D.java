package com.example.ballgameprojects.library.Resource;

import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Vector.Vector2;

/* グラフィック2Dクラス */
public class Graph2D {

    /* field */
    private GLRenderer render;
    private int key;
    private int width, height, divX, divY, oneW, oneH;

    public Graph2D(GLRenderer _inrender, int inputkey, int inwidth, int inheight) {
        // TODO コンストラクタ
        render = _inrender;
        width = inwidth;
        height = inheight;
        this.key = render.loadGraph(inputkey);
    }

    public void setWidHei(int inwidth, int inheight) {
        // TODO 幅と高さの設定
        width = inwidth;
        height = inheight;
    }

    public void SetDivision(int dX, int dY)
    {
        // TODO 幅と高さの設定
        divX = dX;
        divY = dY;
        oneW = (int)(width / divX);
        oneH = (int)(height / divY);
    }

    public void SetColor(GameColor incolor)
    {
        // TODO カラー設定
        render.SetColor(incolor);
    }

    public void Draw2D(Vector2 pos, float ang, Vector2 scl) {
        // TODO 2D画像の描画(基準が画像の左上)
        /*
         * pos : 描画位置
         * ang : 描画の角度
         * scl : 描画の倍率
         */
        render.SetTexture(this.key);
        render.setTextureArea(width, height, 0, 0, width, height);
        render.DrawRect((int)pos.x, (int)pos.y, (int)scl.x, (int)scl.y, ang);
    }

    public void DrawCenter2D(Vector2 pos, float ang, Vector2 scl) {
        // TODO 2D画像の描画(基準が画像の中心)
        /*
         * pos : 描画位置
         * ang : 描画の角度
         * scl : 描画の倍率
         */
        render.SetTexture(this.key);
        render.setTextureArea(width, height, 0, 0, width, height);
        render.DrawCenterRect((int)pos.x, (int)pos.y, (int)scl.x, (int)scl.y, ang);
    }

    public void DrawDiv2D(Vector2 pos, float ang, Vector2 scl, int number)
    {
        // TODO 画像の描画(アニメ画像分割用)
        /*
         * pos : 描画位置
         * ang : 描画の角度
         * scl : 描画の倍率
         */
        int numX = (int)(number % divX);
        int numY = (int)(number / divX);

        render.SetTexture(this.key);
        render.setTextureArea(width, height, numX * oneW, numY * oneH, oneW, oneH);
        render.DrawCenterRect((int)pos.x, (int)pos.y, (int)scl.x, (int)scl.y, ang);
    }
}
