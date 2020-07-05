package com.example.ballgameprojects.library.Dialog.NameParts;

import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.Dialog.SuperDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.Graph2D;
import com.example.ballgameprojects.library.Vector.Vector2;

/* スコアタグ */
public class ScoreTag extends SuperDialog {
    /* field */
    public int score, keta;
    public float numberWid;

    public ScoreTag(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.score = 0;
        this.keta = 1;
        this.numberWid = this.render.GetScreenWidth() * 0.05f;
    }

    public void SetScore(int in)
    {
        // TODO スコアの設定
        this.score = in;
        this.keta = this.GetKeta();
    }

    public int GetKeta()
    {
        // TODO 結果の設定
        if(this.score == 0)	return 1;
        int temp = 1, count = 0, dis;
        do
        {
            dis = (int)(this.score / temp);
            if( dis > 0 )
            {
                count++;
                temp *= 10;
            }else			break;
        }while(true);

        return count;
    }

    public void NumbersDraw()
    {
        // TODO 番号描画
        if( this.keta <= 0 )	return ;

        Graph2D gh = render.GetGraph("numbers");
        if( gh == null)	return ;
        for(int i=0, num = this.score, wod; i<this.keta; i++, num /= 10)
        {
            if( num == 0 )	wod = 0;
            else			wod = num % 10;

            gh.SetColor(parts.color);
            gh.SetDivision(5, 2);
            gh.DrawDiv2D(new Vector2(parts.pos.x -  this.numberWid * i, parts.pos.y), parts.angle,
                    parts.scale, wod);
        }
    }

    @Override
    public void Step() {
        // TODO アクション

    }

    @Override
    public void Draw() {
        // TODO 描画
        this.NumbersDraw();
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
