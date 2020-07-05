package com.example.ballgameprojects.library.Dialog.Parts;

import com.example.ballgameprojects.library.Dialog.NameParts.ScoreTag;
import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.Dialog.SuperDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.Graph2D;
import com.example.ballgameprojects.library.Vector.Vector2;

/* ゲームタイマークラス */
public class GameTimer extends SuperDialog {
    /* field */
    public int              second, minutes, frameCnt, damageCnt;
    public boolean          alarm;
    private NameTagDialog   semi;
    private NameTagDialog   face;
    private ScoreTag	    score;

    public GameTimer(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.second = this.minutes = this.frameCnt = this.damageCnt = 0;
        this.alarm = true;

        int wd = this.render.GetScreenWidth(), ht = this.render.GetScreenHeight();

        this.SetObject(new Vector2(wd * 0.5f, ht * 0.05f), 0.0f,
                new Vector2(wd, ht * 0.1f));
        this.SetDialogName("waku");

        //セミコロン
        this.semi = new NameTagDialog(this.render);
        this.semi.SetObject(new Vector2(wd * 0.5f, ht * 0.05f), 0.0f,
                new Vector2(wd * 0.1f, ht * 0.05f + ht * 0.01f));
        this.semi.SetDialogName(new String(""));
        this.semi.SetName(new String("semi"));

        //数字
        this.score = new ScoreTag(render);
        this.score.SetObject(new Vector2(wd * 0.93f, ht * 0.05f + ht * 0.01f), 0.0f, new Vector2(wd * 0.05f, ht * 0.05f + ht * 0.01f));
        this.score.SetDialogName(new String(""));

        //顔写真
        this.face = new NameTagDialog(render);
        this.face.SetObject(new Vector2(wd * 0.17f, ht * 0.05f), 0.0f, new Vector2(wd * 0.1f, ht * 0.1f));
        this.face.SetDialogName(new String("face1"));
    }

    public int GetScore()
    {
        // TODO スコアの設定
        return this.score.score;
    }

    public void SetScore(int dscore)
    {
        // TODO スコアの設定
        this.score.SetScore(dscore);
        this.damageCnt = 0;
    }

    public void PlusScore(int dscore)
    {
        // TODO スコアの追加
        this.score.SetScore(this.score.score + dscore);

        if(this.score.score >= 50)	this.face.SetDialogName(new String("face1"));
        else if(this.score.score > 0)		this.face.SetDialogName(new String("face2"));
    }

    public void SubScore(int dscore)
    {
        // TODO スコアの減算
        int sc = (this.score.score - dscore <= 0 ? 0 : this.score.score - dscore);
        this.score.SetScore(sc);

        if(this.score.score >= 50)	this.face.SetDialogName(new String("face1"));
        else if(this.score.score > 0)		this.face.SetDialogName(new String("face2"));
        else						this.face.SetDialogName(new String("face3"));
    }

    public void SetMinute(int target)
    {
        // TODO 制限時間の設定
        this.minutes= target;
        this.second = 0;
    }

    public void DecrementMinute()
    {
        // TODO スコアのアクション
        if( this.alarm )	return ;

        if( ++this.frameCnt % 60 == 0)
        {
            if(--this.second < 0)
            {
                this.second = 0;

                if( --this.minutes < 0 )
                {
                    this.minutes = 0;
                    this.alarm = true;
                }
                else
                    this.second = 59;
            }
        }
    }

    @Override
    public void Step() {
        // TODO アクション
        this.DecrementMinute();
    }

    @Override
    public void Draw() {
        // TODO 描画
        this.DialogDraw();
        this.semi.Draw();
        this.score.Draw();
        this.face.DialogDraw();
        float wh = this.render.GetScreenWidth(), ht = this.render.GetScreenHeight();

        Graph2D gh = render.GetGraph("numbers");
        if( gh != null)
        {
            for(int i=0, numb; i<4; i++)
            {
                if( i>=0 && i<2)
                    numb = (i==0 ? this.second % 10 : this.second / 10);
                else
                    numb = (i==2 ? this.minutes % 10 : this.minutes / 10);

                gh.SetColor(parts.color);
                gh.SetDivision(5, 2);
                gh.DrawDiv2D(new Vector2(parts.pos.x + (wh * 0.1f) - i * (wh * 0.05f) - (i>=2 ? (wh * 0.05f) : 0.0f), parts.pos.y + ht * 0.005f), parts.angle,
                        new Vector2(this.render.GetScreenWidth() * 0.05f, this.render.GetScreenHeight() * 0.05f), numb);
            }
        }
    }

    @Override
    public void Del() {
        // TODO 削除

    }
}
