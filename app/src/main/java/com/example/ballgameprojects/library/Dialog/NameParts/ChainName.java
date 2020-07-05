package com.example.ballgameprojects.library.Dialog.NameParts;

import com.example.ballgameprojects.library.Dialog.FadeNameDialog;
import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.Dialog.UIParts2D;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Vector.Vector2;

import java.util.ArrayList;

/* チェインネイム */
public class ChainName extends NameTagDialog {
    /* field */
    public enum CHAIN_ENUM
    {
        CHAIN_XY, CHAIN_X, CHAIN_Y,
    }
    private ArrayList<FadeNameDialog> dialog;
    private int							dialogSum;
    private CHAIN_ENUM 					chainNum;

    public ChainName(GLRenderer in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    public void Add(FadeNameDialog object)
    {
        // TODO 追加
        this.dialog.add(object);
        this.dialogSum++;
    }

    public void Clear()
    {
        // TODO 初期化
        this.dialog.clear();
        this.dialogSum = 0;
    }

    public FadeNameDialog GetListBegin()
    {
        // TODO 1番目の名前のダイアログを取得
        return this.dialog.get(0);
    }

    @Override
    public void Init() {
        // TODO 初期化
        this.dialog = new ArrayList<FadeNameDialog>();
        this.dialogSum = 0;
        this.chainNum = CHAIN_ENUM.CHAIN_XY;
        this.Clear();
    }

    @Override
    public void Step()
    {
        // TODO アクション
        FadeNameDialog sc;
        UIParts2D pt, old = new UIParts2D(), old2 = new UIParts2D();
        for(int i=0; i<this.dialogSum; i++)
        {
            sc = this.dialog.get(i);
            pt = sc.GetParts();
            if(i == 0)
            {
                switch(chainNum)
                {
                    case CHAIN_X:
                        old.pos.x = pt.pos.x;
                        break;
                    case CHAIN_Y:
                        old.pos.y = pt.pos.y;
                        break;
                    case CHAIN_XY:
                        old.pos.x = pt.pos.x;
                        old.pos.y = pt.pos.y;
                }
                old.scale.SetVector(pt.scale);
                old.angle = pt.angle;
                sc.Step();
            }
            else
            {
                switch(chainNum)
                {
                    case CHAIN_X:
                        old2.pos.x = pt.pos.x;
                        break;
                    case CHAIN_Y:
                        old2.pos.y = pt.pos.y;
                        break;
                    case CHAIN_XY:
                        old2.pos.x = pt.pos.x;
                        old2.pos.y = pt.pos.y;
                }
                old2.angle = pt.angle;
                old2.scale.SetVector(pt.scale);

                sc.Step();
                switch(chainNum)
                {
                    case CHAIN_X:
                        sc.SetPos(new Vector2(old.pos.x, pt.pos.y));
                        break;
                    case CHAIN_Y:
                        sc.SetPos(new Vector2(pt.pos.x, old.pos.y));
                        break;
                    case CHAIN_XY:
                        sc.SetPos(new Vector2(pt.pos.x, pt.pos.y));
                }
                sc.SetPos(new Vector2(pt.pos.x, old.pos.y));
                sc.SetScale(old.scale);
                sc.SetAngle(old.angle);

                old.pos.y = old2.pos.y;
                old.angle = old2.angle;
                old.scale.SetVector(old2.scale);
            }
        }
    }

    @Override
    public void Draw() {
        // TODO 描画
        FadeNameDialog sc;
        for(int i=0; i<this.dialogSum; i++)
        {
            sc = this.dialog.get(i);
            sc.Draw();
        }
    }

    @Override
    public void Del() {
        // TODO 削除
        FadeNameDialog sc;
        for(int i=0; i<this.dialogSum; i++)
        {
            sc = this.dialog.get(i);
            if(sc.delFlag)
            {
                this.dialog.remove(sc);
                this.dialogSum--;
                i--;
            }
        }
    }
}
