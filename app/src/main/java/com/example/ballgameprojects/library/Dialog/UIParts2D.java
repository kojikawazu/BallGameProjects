package com.example.ballgameprojects.library.Dialog;

import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;

/* UPパーツクラス */
public class UIParts2D {
    /* field */
    public Vector2 pos, scale;
    public float angle;
    public GameColor color;
    public String dialogName;

    public UIParts2D()
    {
        // TODO コンストラクタ
        pos = new Vector2(0,0);
        scale = new Vector2(0,0);
        angle = 0.0f;
        color = new GameColor(1,1,1,1);
        dialogName = new String();
    }
}
