package com.example.ballgameprojects.MainGame;

import com.example.ballgameprojects.ChangeScene.ChangeScene;
import com.example.ballgameprojects.R;
import com.example.ballgameprojects.TittleScene.TitleScene;
import com.example.ballgameprojects.library.Dialog.BackgroundDialog;
import com.example.ballgameprojects.library.Dialog.Detail.YesNoDialog;
import com.example.ballgameprojects.library.Dialog.DialogList;
import com.example.ballgameprojects.library.Dialog.Effect.SpeechEffect;
import com.example.ballgameprojects.library.Dialog.EffectDialog;
import com.example.ballgameprojects.library.Dialog.EffectDialogList;
import com.example.ballgameprojects.library.Dialog.NameParts.ChainName;
import com.example.ballgameprojects.library.Dialog.NameParts.PlusNumber;
import com.example.ballgameprojects.library.Dialog.Parts.Ball;
import com.example.ballgameprojects.library.Dialog.Parts.ChildrenBall;
import com.example.ballgameprojects.library.Dialog.Parts.Coin;
import com.example.ballgameprojects.library.Dialog.Parts.GameTimer;
import com.example.ballgameprojects.library.Dialog.Parts.MainScore;
import com.example.ballgameprojects.library.Dialog.Parts.OutSide;
import com.example.ballgameprojects.library.Dialog.Words.FinishWord;
import com.example.ballgameprojects.library.Dialog.Words.GameOverWord;
import com.example.ballgameprojects.library.Dialog.Words.GameStartWord;
import com.example.ballgameprojects.library.Music.GameMusic;
import com.example.ballgameprojects.library.Music.MusicList;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Vector.Vector2;
import com.example.ballgameprojects.library.scene.SceneManager;
import com.example.ballgameprojects.library.scene.SuperScene;

/* ゲームメインシーン */
public class MainGame  extends SuperScene {
    /* field */
    public enum MAINGAME_ENUM
    {
        MAINGAME_GAMESTARTWAIT, MAINGAME_GAME, MAINGAME_SCORE, MAINGAME_GAMECLEAR,
        MAINGAME_GAMEOVER, MAINGAME_CONTINUE, MAINGAME_WAIT,
    }

    private BackgroundDialog        background;
    private OutSide                 outsideDialog;
    private Ball                    ball;
    private MAINGAME_ENUM			state;
    private YesNoDialog             yesno;
    private MainScore               mainScore;
    private ChainName               childrenBall;

    public GameTimer                timer;
    public EffectDialogList         effectList;
    public DialogList               dialogList, fukidasiDialogList;
    public EffectDialogList		    coinList;
    public GameMusic                music, touchMusic, scoreMusic;
    public MusicList                musicList;

    public MainGame(SceneManager in) {
        // TODO コンストラクタ
        super(in);
        this.Init();
    }

    @Override
    public void Init() {
        // TODO 初期化
        //背景
        this.background = new BackgroundDialog(this.GetRender());
        this.background.SetDialogName("stoneback");
        this.background.SetColor(new GameColor(1,1,0,1));

        //枠ダイアログ
        this.outsideDialog = new OutSide(this.GetRender());

        //ボールダイアログ
        this.ball = new Ball(this.GetRender());

        //タイマー
        this.timer = new GameTimer(this.GetRender());
        this.timer.SetMinute(1);
        this.timer.SetScore(100);

        //サウンド
        this.music = new GameMusic();
        SetMusic();

        //タッチするときの音楽
        this.touchMusic = new GameMusic();
        this.touchMusic.LoadMusic(this.GetRender().getActivity(), R.raw.yossha);
        this.touchMusic.SetVolume(1.0f);
        this.touchMusic.Play();
        this.scoreMusic = null;

        this.childrenBall = new ChainName(this.GetRender());
        this.fukidasiDialogList = new DialogList();
        this.coinList = new EffectDialogList();
        this.effectList = new EffectDialogList();
        this.musicList = new MusicList();
        this.dialogList = new DialogList();
        this.yesno = null;
        this.mainScore = null;
        this.state = MAINGAME_ENUM.MAINGAME_GAMESTARTWAIT;

        this.Input_childrenBall();
    }

    public void GameContinue()
    {
        // TODO コンテニュー
        this.effectList.Clear();
        this.coinList.Clear();

        this.timer.SetMinute(1);
        this.timer.SetScore(100);
    }

    private void Input_childrenBall()
    {
        // TODO 子ボール追加
        ChildrenBall dg;
        for(int i=0; i<1; i++)
        {
            dg = new ChildrenBall(this.GetRender());
            this.childrenBall.Add(dg);
        }
    }


    public void Input_kirariEffect()
    {
        // TODO キラキラエフェクト追加
        float wh = GetRender().GetScreenWidth(), ht = GetRender().GetScreenHeight();
        //きらりエフェクト
        EffectDialog ef = new EffectDialog(this.GetRender());
        ef.SetObject(new Vector2(this.ball.GetParts().pos), 0.0f, new Vector2(wh * 0.2f, ht * 0.2f));
        ef.SetDialogName("kirariEffect2");
        ef.SetAnimation(8, 1, 8, 2, false);
        this.effectList.Add(ef);
    }

    public void Input_fireEffect()
    {
        // TODO 炎のエフェクト追加
        float wh = GetRender().GetScreenWidth(), ht = GetRender().GetScreenHeight();
        //きらりエフェクト
        EffectDialog ef = new EffectDialog(this.GetRender());
        ef.SetObject(new Vector2(this.ball.GetParts().pos), 0.0f, new Vector2(wh * 0.3f, ht * 0.3f));
        ef.SetDialogName("fireEffect");
        ef.SetAnimation(8, 1, 8, 3, false);
        this.effectList.Add(ef);
    }

    public void Input_coinDialog()
    {
        // TODO コインのエフェクト追加
        Coin coins = new Coin(this.GetRender());
        coins.SetVectorRandom();
        this.coinList.Add(coins);
    }

    public void Input_plusNumberDialog()
    {
        // TODO 番号追加
        float wh = GetRender().GetScreenWidth(), ht = GetRender().GetScreenHeight();
        PlusNumber dg = new PlusNumber(this.GetRender());
        dg.SetObject(new Vector2(wh * 0.7f, ht * 0.1f), 0.0f,
                new Vector2(wh * 0.1f, ht * 0.05f));
        dg.SetScore(-2);
        this.dialogList.Add(dg);
    }

    public void Input_speechEffect(String str)
    {
        // TODO 吹き出しエフェクト追加
        float wh = GetRender().GetScreenWidth(), ht = GetRender().GetScreenHeight();
        SpeechEffect ef = new SpeechEffect(this.GetRender());
        ef.SetPos(new Vector2(wh * 0.25f, ht * 0.2f));
        ef.SetName(str);
        this.fukidasiDialogList.Add(ef);
    }

    public void Play_seMusic(int pass)
    {
        // TODO SE開始
        GameMusic mu = new GameMusic();
        mu.LoadMusic(this.GetRender().getActivity(), pass);
        mu.SetVolume(1.0f);
        mu.Play();
        this.musicList.Add(mu);
    }

    public void Play_scoreMusic(int pass)
    {
        // TODO スコア音楽開始
        this.scoreMusic = new GameMusic();
        this.scoreMusic.LoadMusic(this.GetRender().getActivity(), pass);
        this.scoreMusic.SetVolume(1.0f);
        this.scoreMusic.Play();
    }

    @Override
    public void Step() {
        // TODO アクション
        this.outsideDialog.Step();
        this.ball.Step();
        this.timer.Step();
        this.musicList.Step();

        switch(this.state)
        {
            case MAINGAME_GAMESTARTWAIT:
                this.action_gamestart();
                break;
            case MAINGAME_GAME:
                this.action_mainGame();
                break;
            case MAINGAME_GAMECLEAR:
                this.action_gameclear();
                break;
            case MAINGAME_SCORE:
                this.action_score();
                break;
            case MAINGAME_GAMEOVER:
                this.action_gameover();
                break;
            case MAINGAME_CONTINUE:
                this.action_gameContinue();
                break;
        }

        this.effectList.Step();
        this.dialogList.Step();
        this.coinList.Step();
        this.fukidasiDialogList.Step();

        //this.childrenBall.Step();
        this.childrenBall.GetListBegin().SetPos( new Vector2(this.ball.GetParts().pos.x, this.ball.GetParts().pos.y));

        this.fukidasiDialogList.Del();
        this.effectList.Del();
        this.dialogList.Del();
        this.coinList.Del();
    }

    public void action_gamestart()
    {
        // TODO ゲーム開始アクション
        if(this.yesno != null)
        {
            this.yesno.Step();
            if(this.yesno.delFlag)
                this.yesno = null;
        }

        this.ball.IsAtackWall(this.outsideDialog);
        if(++this.frameCnt == 30)
        {
            GameStartWord dg = new GameStartWord(this.GetRender());
            this.dialogList.Add(dg);

            this.Input_speechEffect(new String("pleaseFuki"));
        }
        else if(this.frameCnt >= 180)
        {
            this.frameCnt = 0;
            this.state = MAINGAME_ENUM.MAINGAME_GAME;
            this.timer.alarm = false;
        }
    }

    public void action_mainGame()
    {
        // TODO メインゲームアクション
        //ボールが壁にぶつかったときのアクション
        float wh = GetRender().GetScreenWidth(), ht = GetRender().GetScreenHeight();
        if(this.ball.IsAtackWall(this.outsideDialog))
        {
            this.timer.SubScore(2);
            this.timer.damageCnt++;
            this.Input_fireEffect();
            this.Play_seMusic(R.raw.shot);

            if(this.GetRender().random.nextInt() % 5 == 0)
                this.Input_speechEffect(new String("wallFuki"));
        }

        //ボールをタッチしたときのアクション
        if(this.ball.IsTouchBall())
        {
            this.Input_kirariEffect();
            this.Play_seMusic(R.raw.card);
        }

        //コインとボールの当たり判定＆アクション
        this.coinList.IsCoin_BallAtack(ball, this);
        //コインの生成
        if(++this.frameCnt % 30 == 0)
        {
            this.Input_coinDialog();
            if(this.GetRender().random.nextInt() % 5 == 0)
                this.Input_speechEffect(new String("coinFuki"));
        }

        if(this.timer.second == 0 && this.timer.minutes == 1 && this.timer.frameCnt == 0)
        {
            this.Input_speechEffect(new String("onceFuki"));
        }

        //スコアが０になったときのアクション
        if(this.timer.GetScore() <= 0)
        {
            this.state = MAINGAME_ENUM.MAINGAME_GAMEOVER;
            this.timer.alarm = true;
            this.frameCnt = 0;

            //失敗アラーム
            this.Play_scoreMusic(R.raw.ganbare);
            this.Input_speechEffect(new String("motoFuki"));
        }
        else if(this.timer.alarm)
        {
            this.state = MAINGAME_ENUM.MAINGAME_GAMECLEAR;
            this.Input_speechEffect(new String("perfectFuki"));
            this.frameCnt = 0;
        }
    }

    public void action_gameover()
    {
        // TODO ゲームオーバアクション
        this.ball.IsAtackWall(this.outsideDialog);
        this.ball.IsTouchBall();

        if(++this.frameCnt == 30)
        {
            GameOverWord dg = new GameOverWord(this.GetRender());
            this.dialogList.Add(dg);
        }
        else if(this.frameCnt >= 120)
        {
            yesno = new YesNoDialog(GetRender());
            this.state = MAINGAME_ENUM.MAINGAME_CONTINUE;
        }
    }

    public void action_gameclear()
    {
        // TODO ゲームクリアアクション
        this.ball.IsAtackWall(this.outsideDialog);
        this.ball.IsTouchBall();

        if(++this.frameCnt == 30)
        {
            FinishWord dg = new FinishWord(this.GetRender());
            this.dialogList.Add(dg);
        }
        else if(this.frameCnt >= 120)
        {
            this.mainScore = new MainScore(this.GetRender());
            this.mainScore.SetScore(this.timer.GetScore(), this.timer.damageCnt);
            this.state = MAINGAME_ENUM.MAINGAME_SCORE;
            this.frameCnt = 0;

            this.Play_scoreMusic( (this.timer.GetScore() <= 60 ? R.raw.ganbare : R.raw.yossha) );
        }
    }

    public void action_score()
    {
        // TODO スコアアクション
        this.ball.IsAtackWall(this.outsideDialog);
        this.ball.IsTouchBall();

        this.mainScore.Step();
        if(++this.frameCnt >= 60)
        {
            if( this.mainScore.delFlag )
            {
                this.mainScore = null;
                yesno = new YesNoDialog(GetRender());
                this.state = MAINGAME_ENUM.MAINGAME_CONTINUE;
                this.frameCnt = 0;
                this.Input_speechEffect(new String("continueFuki"));
                return ;
            }
            else
            {
                if( this.mainScore.IstouchScreen() )
                    this.mainScore.SetFadeOut();
            }
        }
    }

    public void action_gameContinue()
    {
        // TODO コンテニューアクション
        this.ball.IsAtackWall(this.outsideDialog);
        this.ball.IsTouchBall();

        if(this.yesno != null)
        {
            this.yesno.Step();
            if(this.yesno.score == YesNoDialog.YESNO_SCORE.YESNO_YES)
            {
                this.frameCnt = 0;
                this.timer.SetMinute(3);
                this.timer.SetScore(10);
                this.state = MAINGAME_ENUM.MAINGAME_GAMESTARTWAIT;
            }
            else if(this.yesno.score == YesNoDialog.YESNO_SCORE.YESNO_NO)
            {
                ChangeScene sc = new ChangeScene(this.GetManager());
                sc.SetChangeScene(new TitleScene(this.GetManager()), this);
                this.GetManager().Add( sc );
                this.yesno = null;

                this.state = MAINGAME_ENUM.MAINGAME_WAIT;
            }
        }
    }

    public void action_wait(){}

    @Override
    public void Dispose() {
        // TODO アンリンク
        this.effectList.Clear();
        this.dialogList.Clear();
        this.coinList.Clear();
        this.musicList.Clear();
        this.fukidasiDialogList.Clear();
        this.childrenBall.Clear();
        this.childrenBall = null;

        this.mainScore = null;
        this.yesno = null;
        this.music.Stop();
        this.music = null;

        if(this.touchMusic != null)
            this.touchMusic.Stop();
        this.touchMusic = null;

        if(this.scoreMusic != null)
            this.scoreMusic.Stop();
        this.scoreMusic = null;

        this.background = null;
        this.outsideDialog = null;
        this.ball = null;
        this.timer = null;
        this.effectList = null;
        this.dialogList = null;
        this.coinList = null;
        this.musicList = null;
    }

    @Override
    public void Del() {
        // TODO 削除

    }

    @Override
    public void Draw2D() {
        // TODO 描画
        this.background.Draw();

        this.childrenBall.Draw();
        this.ball.Draw();

        this.outsideDialog.Draw();

        this.timer.Draw();
        this.coinList.Draw();

        if( this.mainScore != null)
            this.mainScore.Draw();
        if(this.yesno != null)
            this.yesno.Draw();

        this.fukidasiDialogList.Draw();
        this.dialogList.Draw();
        this.effectList.Draw();
    }

    @Override
    public void PauseMusic() {
        // TODO 音楽の再開
        SetMusic();
    }

    @Override
    public void StopMusic() {
        // TODO 音楽の停止
        if(this.music != null)
            this.music.Stop();
        if(this.touchMusic != null)
            this.touchMusic.Stop();
        if(this.scoreMusic != null)
            this.scoreMusic.Stop();
        this.musicList.Clear();
    }

    public void SetMusic(){
        // TODO 音楽の設定
        this.music.LoadMusic(this.GetRender().getActivity(), R.raw.gamemusic);
        this.music.SetLoop(true);
        this.music.Play();
    }
}
