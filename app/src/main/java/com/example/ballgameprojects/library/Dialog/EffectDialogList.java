package com.example.ballgameprojects.library.Dialog;

import com.example.ballgameprojects.MainGame.MainGame;
import com.example.ballgameprojects.R;
import com.example.ballgameprojects.library.Dialog.EffectDialog;
import com.example.ballgameprojects.library.Dialog.NameParts.PlusNumber;
import com.example.ballgameprojects.library.Dialog.Parts.Ball;
import com.example.ballgameprojects.library.Dialog.Parts.Coin;
import com.example.ballgameprojects.library.Dialog.UIParts2D;
import com.example.ballgameprojects.library.Music.GameMusic;
import com.example.ballgameprojects.library.Vector.Vector2;

import java.util.ArrayList;

/* エフェクトダイアログリストクラス */
public class EffectDialogList {
    /* field */
    private ArrayList<EffectDialog> effectList;
    private int						effectSum;
    public static int BORNAS_PLUS = 7;
    public static int NORMAL_PLUS = 5;
    public static int NORMAL_SUB = -10;

    public EffectDialogList()
    {
        // TODO コンストラクタ
        this.effectList = new ArrayList<EffectDialog>();
        this.Clear();
    }

    public void Add(EffectDialog in)
    {
        // TODO エフェクトの追加
        this.effectList.add(in);
        this.effectSum++;
    }

    public void Step()
    {
        // TODO エフェクトのアクション
        EffectDialog ef;
        for(int i=0; i<this.effectSum; i++)
        {
            ef = this.effectList.get(i);
            ef.Step();
        }
    }

    public void IsCoin_BallAtack(Ball ball, MainGame game)
    {
        // TODO
        EffectDialog ef;
        UIParts2D pt = ball.GetParts();
        int plus = 0;
        float wh = game.GetRender().GetScreenWidth(), ht = game.GetRender().GetScreenHeight();
        for(int i=0; i<this.effectSum; i++)
        {
            ef = this.effectList.get(i);
            if(ef.IsTouchBoundingbox_ofObject(pt.pos.x, pt.pos.y, 0.5f))
            {
                //コインは消滅
                ef.delFlag = true;

                //当たったときのエフェクト
                EffectDialog input = new EffectDialog(game.GetRender());
                input.SetObject(new Vector2(ef.GetParts().pos.x, ef.GetParts().pos.y), 0.0f,
                        new Vector2(wh * 0.2f, ht * 0.2f));
                input.SetDialogName("kirariEffect3");
                input.SetAnimation(10, 1, 10, 2, false);
                game.effectList.Add(input);

                //当たったときの音
                GameMusic mu = new GameMusic();
                mu.LoadMusic(game.GetRender().getActivity(), R.raw.kira);
                mu.Play();
                game.musicList.Add(mu);

                //スコア加算
                switch(((Coin)(ef)).coinKnd)
                {
                    case COLOR_NORMAL:
                        plus = NORMAL_PLUS;
                        break;
                    case COLOR_RED:
                        plus = BORNAS_PLUS;
                        break;
                    case COLOR_GLEEN:
                        plus = BORNAS_PLUS;
                        break;
                    case COLOR_BLUE:
                        plus = BORNAS_PLUS;
                        break;
                    case COLOR_BLACK:
                        plus = NORMAL_SUB;
                        break;
                }

                if(plus > 0)	game.timer.PlusScore(plus);
                else			game.timer.SubScore(-plus);

                //スコア加算エフェクト
                PlusNumber dg = new PlusNumber(game.GetRender());
                dg.SetObject(new Vector2(wh * 0.7f, ht * 0.1f), 0.0f,
                        new Vector2(wh * 0.1f, ht * 0.05f));
                dg.SetScore(plus);
                game.dialogList.Add(dg);
            }
        }
    }

    public void Del()
    {
        // TODO エフェクトの削除
        EffectDialog ef;
        for(int i=0; i<this.effectSum; i++)
        {
            ef = this.effectList.get(i);
            if( ef.delFlag )
            {
                this.effectList.remove(ef);
                i--;
                this.effectSum--;
            }
        }
    }

    public void Clear()
    {
        // TODO エフェクトリスト初期化
        this.effectList.clear();
        this.effectSum = 0;
    }

    public void Draw()
    {
        // TODO エフェクトの描画
        EffectDialog ef;
        for(int i=0; i<this.effectSum; i++)
        {
            ef = this.effectList.get(i);
            ef.Draw();
        }
    }
}
