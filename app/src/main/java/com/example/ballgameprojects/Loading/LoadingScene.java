package com.example.ballgameprojects.Loading;

import com.example.ballgameprojects.ChangeScene.ChangeScene;
import com.example.ballgameprojects.R;
import com.example.ballgameprojects.TittleScene.TitleScene;
import com.example.ballgameprojects.library.GLRenderer;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.scene.SceneManager;
import com.example.ballgameprojects.library.scene.SuperLoading;

/* ロードシーン */
public class LoadingScene extends SuperLoading {
    /* field */
    private LOADING_SCENE state;

    public enum LOADING_SCENE
    {
        LOADING_ACTION, LOADING_WAIT,
    }

    public LoadingScene(SceneManager sceneManager) {
        // TODO コンストラクタ
        super(sceneManager);
        this.Init();
    }

    void SetState(LOADING_SCENE in){	this.state = in;	}

    @Override
    public void Init() {
        // TODO 初期化

        this.Load();

        //背景
        background.SetDialogName("loadground");
        background.SetColor(new GameColor(1,1,1,1));

        this.state = LOADING_SCENE.LOADING_ACTION;
    }

    @Override
    public void Step() {
        // TODO アクション
        switch(state)
        {
            case LOADING_ACTION:
                this.Action_loading();
                break;
            case LOADING_WAIT:
                this.Action_wait();
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
    public void StopMusic() {
        // TODO 音楽の停止

    }

    @Override
    public void Load() {
        // TODO リソースのロード
        GLRenderer rd = this.GetRender();
        rd.InputGraph(R.drawable.loadground, 		"loadground", 			512, 512);
        rd.InputGraph(R.drawable.titleground, 		"titleground", 			512, 512);
        rd.InputGraph(R.drawable.background, 		"background", 			256, 256);
        rd.InputGraph(R.drawable.tittlename, 		"tittlename", 			256, 128);
        rd.InputGraph(R.drawable.pleasetouchscreen, "pleasetouchscreen", 	256, 128);
        rd.InputGraph(R.drawable.checkbox, 			"checkbox", 			256, 256);
        rd.InputGraph(R.drawable.ball, 				"ball", 				128, 128);
        rd.InputGraph(R.drawable.waku2, 			"waku", 				256, 128);
        rd.InputGraph(R.drawable.stoneback, 		"stoneback", 			256, 256);
        rd.InputGraph(R.drawable.timers, 			"timer", 				128, 64);
        rd.InputGraph(R.drawable.semi, 				"semi", 				64, 64);
        rd.InputGraph(R.drawable.manuword, 			"sub", 				128, 128);
        rd.InputGraph(R.drawable.plusword, 			"plus", 				128, 128);
        rd.InputGraph(R.drawable.gamestart, 		"gamestart", 				128, 64);
        rd.InputGraph(R.drawable.plate, 			"plate", 				512, 128);
        rd.InputGraph(R.drawable.gameover, 			"gameover", 512, 128);
        rd.InputGraph(R.drawable.finish, 			"finish", 512, 128);
        rd.InputGraph(R.drawable.scorebox, 			"scorebox", 512, 512);
        rd.InputGraph(R.drawable.yesno, 			"yesno", 512, 512);
        rd.InputGraph(R.drawable.scorepin, 			"scorepin", 256, 256);
        rd.InputGraph(R.drawable.star, 				"star", 128, 128);
        rd.InputGraph(R.drawable.runk, 				"runk", 256, 64);
        rd.InputGraph(R.drawable.face1, 			"face1", 256, 256);
        rd.InputGraph(R.drawable.face2, 			"face2", 256, 256);
        rd.InputGraph(R.drawable.face3, 			"face3", 256, 256);
        rd.InputGraph(R.drawable.bubbleface1, 		"bubbleface1", 256, 256);
        rd.InputGraph(R.drawable.bubbleface2, 		"bubbleface2", 256, 256);
        rd.InputGraph(R.drawable.bubbleface3, 		"bubbleface3", 256, 256);
        rd.InputGraph(R.drawable.fukidasi, 			"fukidasi", 512, 256);
        rd.InputGraph(R.drawable.continuefuki, 		"continueFuki", 512, 256);
        rd.InputGraph(R.drawable.coinfuki, 			"coinFuki", 512, 256);
        rd.InputGraph(R.drawable.pleasefuki, 		"pleaseFuki", 512, 256);
        rd.InputGraph(R.drawable.perfectfuki, 		"perfectFuki", 512, 256);
        rd.InputGraph(R.drawable.wallfuki, 			"wallFuki", 512, 256);
        rd.InputGraph(R.drawable.oncefuki, 			"onceFuki", 512, 256);
        rd.InputGraph(R.drawable.motofuki, 			"motoFuki", 512, 256);


        rd.InputGraph(R.drawable.kirari2, 			"kirariEffect2", 1024, 128);
        rd.InputGraph(R.drawable.kira3, 			"kirariEffect3", 1024, 128);
        rd.InputGraph(R.drawable.kirarieffect, 		"kirariEffect", 1280, 128);
        rd.InputGraph(R.drawable.coin, 				"coin", 128, 64);
        rd.InputGraph(R.drawable.numbers, 			"numbers", 512, 128);
        rd.InputGraph(R.drawable.fireeffect, 		"fireEffect", 1024, 128);
        rd.InputGraph(R.drawable.jibaeffect, 			"jibaEffect", 256, 1024);
    }

    public void Action_loading()
    {
        // TODO ロードアクション
        if( this.IncrementFrameCnt( 30 ) )
        {
            this.SetState(LOADING_SCENE.LOADING_WAIT);
            this.ChangeScene_TittleScene();
        }
    }

    public void Action_wait()
    {
        // TODO ロード待機
    }

    public void ChangeScene_TittleScene()
    {
        // TODO タイトルシーンへチェンジ
        ChangeScene sc = new ChangeScene(this.GetManager());
        sc.SetChangeScene(new TitleScene(this.GetManager()), this);
        this.GetManager().Add( sc );
    }

}
