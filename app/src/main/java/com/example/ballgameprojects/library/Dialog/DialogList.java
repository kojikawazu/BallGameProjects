package com.example.ballgameprojects.library.Dialog;

import java.util.ArrayList;

/* ダイアログリストクラス */
public class DialogList {
    /* field */
    private ArrayList<SuperDialog> effectList;
    private int						effectSum;

    public DialogList() {
        // TODO コンストラクタ
        this.effectList = new ArrayList<SuperDialog>();
        this.Clear();
    }

    public void Add(SuperDialog in)
    {
        // TODO ダイアログの追加
        this.effectList.add(in);
        this.effectSum++;
    }

    public void Step()
    {
        // TODO ダイアログの処理
        SuperDialog ef;
        for(int i=0; i<this.effectSum; i++)
        {
            ef = this.effectList.get(i);
            ef.Step();
        }
    }

    public void Del()
    {
        // TODO ダイアログの削除
        SuperDialog ef;
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
        // TODO ダイアログリストの初期化
        this.effectList.clear();
        this.effectSum = 0;
    }

    public void Draw()
    {
        // TODO ダイアログの描画
        SuperDialog ef;
        for(int i=0; i<this.effectSum; i++)
        {
            ef = this.effectList.get(i);
            ef.Draw();
        }
    }
}
