package com.example.ballgameprojects.TittleScene;

import com.example.ballgameprojects.ChangeScene.ChangeScene;
import com.example.ballgameprojects.MainActivity;
import com.example.ballgameprojects.MainGame.MainGame;
import com.example.ballgameprojects.R;
import com.example.ballgameprojects.library.Dialog.BackgroundDialog;
import com.example.ballgameprojects.library.Dialog.EffectDialog;
import com.example.ballgameprojects.library.Dialog.EffectDialogList;
import com.example.ballgameprojects.library.Dialog.NameParts.NameTagMoveScale;
import com.example.ballgameprojects.library.Dialog.NameTagDialog;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Music.GameMusic;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;
import com.example.ballgameprojects.library.scene.SceneManager;
import com.example.ballgameprojects.library.scene.SuperScene;

/* タイトルシーン */
public class TitleScene extends SuperScene {
    /* field */
    private enum TITTLE_SCENE
    {
        TITTLE_FADEIN, TITTLE_MAINSTEP, TITTLE_FADEOUT,
    };

    private BackgroundDialog            background;
    private NameTagDialog               nameTag;
    private NameTagMoveScale            touchDialog;
    private TITTLE_SCENE				state;
    private EffectDialogList            effectList;
    private GameMusic                   music;

    public TitleScene(SceneManager in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        state = TITTLE_SCENE.TITTLE_FADEIN;

        GLRenderer gl = this.GetManager().getRender();
        int wd = gl.GetScreenWidth();
        int ht = gl.GetScreenHeight();

        //背景
        background = new BackgroundDialog(gl);
        background.SetDialogName("titleground");
        background.SetColor(new GameColor(1,1,1,1));

        //タイトル名
        nameTag = new NameTagDialog(gl);
        nameTag.SetDialogName(new String(""));
        nameTag.SetName(new String("tittlename"));
        nameTag.SetObject(new Vector2(wd * 0.5f, ht * 0.2f), 0.0f,
                new Vector2(wd * 0.8f, ht * 0.2f));

        //タッチダイアログ
        touchDialog = new NameTagMoveScale(gl);
        touchDialog.SetDialogName(new String(""));
        touchDialog.SetName(new String("pleasetouchscreen"));
        touchDialog.SetObject(new Vector2(wd * 0.5f, ht * 0.8f), 0.0f,
                new Vector2(wd * 0.6f, ht * 0.2f));
        touchDialog.SetInterval(new Vector2(wd * 0.2f, ht * 0.1f), 5);
        touchDialog.SetColor(new GameColor(1, 1, 1, 0));

        //エフェクトリスト
        effectList = new EffectDialogList();

        //サウンド
        this.music = new GameMusic();
        PlayMusic();
    }

    @Override
    public void Step() {
        // TODO アクション
        this.touchDialog.Step();
        this.effectList.Step();

        switch( this.state )
        {
            case TITTLE_FADEIN:
                this.Action_FadeIn();
                break;
            case TITTLE_MAINSTEP:
                this.Action_MainStep();
                break;
            case TITTLE_FADEOUT:
                this.Action_FadeOut();
                break;
        }
    }

    @Override
    public void Dispose() {
        // TODO アンリンク処理
        if(this.music != null)
            this.music.Stop();

        this.music = null;
    }

    @Override
    public void Del() {
        // TODO 削除
    }

    @Override
    public void Draw2D() {
        // TODO 描画
        this.background.Draw();
        this.nameTag.Draw();
        this.touchDialog.Draw();

        this.effectList.Draw();
    }

    @Override
    public void PauseMusic() {
        // TODO 音楽の再開
        PlayMusic();
    }

    @Override
    public void StopMusic() {
        // TODO 音楽の停止
        if(this.music != null)
            this.music.Stop();
    }

    public void PlayMusic(){
        // TODO 音楽の生成、開始
        this.music.LoadMusic(this.GetRender().getActivity(), R.raw.titlemusic);
        this.music.SetLoop(true);
        this.music.Play();
    }

    public void Action_FadeIn()
    {
        // TODO フェードインアクション
        if( this.IncrementFrameCnt(30))
        {
            this.frameCnt = 0;
            this.SetState(TITTLE_SCENE.TITTLE_MAINSTEP);
        }
    }

    public void Action_MainStep()
    {
        // TODO メインアクション
        //タッチエフェクト処理
        MainActivity ac = this.GetRender().getActivity();
        if( ac.touchCount > 0)
        {
            for(int i=0; i<ac.touchCount; i++)
                this.AddTouchEffect(ac.trgX[i], ac.trgY[i]);
        }

        if( this.touchDialog.IstouchScreen() )
        {
            this.SetState(TITTLE_SCENE.TITTLE_FADEOUT);
            this.ChangeScene_MainGame();
        }
    }

    public void Action_FadeOut()
    {
        // TODO フェードアウトアクション
    }

    public void ChangeScene_MainGame()
    {
        // TODO メインゲーム画面へチェンジ
        ChangeScene sc = new ChangeScene(this.GetManager());
        sc.SetChangeScene(new MainGame(this.GetManager()), this);
        this.GetManager().Add( sc );
    }

    public void SetState(TITTLE_SCENE in){	this.state = in;	}

    public void AddTouchEffect(float posX,float posY)
    {
        // TODO タッチエフェクト追加
        MainActivity ac = this.GetRender().getActivity();
        EffectDialog ef = new EffectDialog(this.GetRender());

        ef.SetObject(new Vector2(posX, posY), 0.0f, new Vector2(50, 50));
        ef.SetDialogName("kirariEffect");
        ef.SetAnimation(10, 1, 10, 2, false);
        this.effectList.Add(ef);
    }
}
