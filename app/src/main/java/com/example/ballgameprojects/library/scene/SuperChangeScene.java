package com.example.ballgameprojects.library.scene;

import com.example.ballgameprojects.library.Dialog.BackgroundDialog;
import com.example.ballgameprojects.library.Resource.GameColor;

/* シーンのチェンジクラス */
public abstract class SuperChangeScene extends SuperScene {
    /* field */
    protected enum CHANGING_ACTION
    {
        CHANGE_FADEIN, CHANGE_WAIT, CHANGE_FADEOUT
    }

    protected BackgroundDialog 	background;
    protected CHANGING_ACTION	state;
    protected SuperScene		newScene, oldScene;
    protected int				intervalSpd;
    protected float				fadeInSpd, fadeOutSpd;

    public SuperChangeScene(SceneManager in)
    {
        // TODO コンストラクタ
        super(in);

        this.fadeInSpd = 0.1f;
        this.fadeOutSpd = 0.2f;
        this.intervalSpd = 20;

        background = new BackgroundDialog(this.GetRender());
        background.SetDialogName("background");
        background.SetColor(new GameColor(1,1,1,1));
        background.SetFade();

        state = CHANGING_ACTION.CHANGE_FADEIN;
    }

    public void SetChangeScene(SuperScene news, SuperScene olds)
    {
        // TODO 次のシーンを受け取る
        this.newScene = news;
        this.oldScene = olds;
    }

    public void ChangeAction()
    {
        // TODO 次のシーンへ遷移
        if( this.newScene != null && this.oldScene != null)
        {
            this.GetManager().Insert(this.newScene, this.oldScene );
            this.GetManager().Dispose(this.oldScene);
        }
    }

    /* 継承する時は以下を実装 */
    abstract public void Init();
    abstract public void Step();
    abstract public void Dispose();
    abstract public void Del();
    abstract public void Draw2D();
    abstract public void StopMusic();

    abstract public void FadeIn();
    abstract public void Wait();
    abstract public void FadeOut();
}
