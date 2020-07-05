package com.example.ballgameprojects.ChangeScene;

import com.example.ballgameprojects.library.scene.SceneManager;
import com.example.ballgameprojects.library.scene.SuperChangeScene;

/* シーンを入れ替えクラス */
public class ChangeScene  extends SuperChangeScene {
    public ChangeScene(SceneManager in)
    {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化

    }

    @Override
    public void Step() {
        // TODO アクション
        switch( this.state )
        {
            case CHANGE_FADEIN:
                this.FadeIn();
                break;
            case CHANGE_WAIT:
                this.Wait();
                break;
            case CHANGE_FADEOUT:
                this.FadeOut();
                break;
        }
    }

    @Override
    public void Dispose() {
        // TODO アンリンク

    }

    @Override
    public void Del() {
        // TODO 削除

    }

    @Override
    public void Draw2D() {
        // TODO 描画
        this.background.Draw();
    }

    @Override
    public void PauseMusic() {
        // TODO 音楽の再開
    }

    @Override
    public void FadeIn() {
        // TODO フェードイン
        if( this.background.FadeIn( this.fadeInSpd ) )
            this.state = CHANGING_ACTION.CHANGE_WAIT;
    }

    @Override
    public void Wait() {
        // TODO 待機
        if( this.IncrementFrameCnt(this.intervalSpd) )
        {
            this.ChangeAction();
            this.state = CHANGING_ACTION.CHANGE_FADEOUT;
        }
    }

    @Override
    public void FadeOut() {
        // TODO フェードアウト
        if( this.background.FadeOut(this.fadeOutSpd) )
            this.GetManager().Dispose(this);
    }

    @Override
    public void StopMusic() {
        // TODO 音楽の停止

    }
}
