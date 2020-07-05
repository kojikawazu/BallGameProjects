package com.example.ballgameprojects.library;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import com.example.ballgameprojects.Loading.LoadingScene;
import com.example.ballgameprojects.MainActivity;
import com.example.ballgameprojects.library.Resource.GameColor;
import com.example.ballgameprojects.library.Resource.Graph2D;
import com.example.ballgameprojects.library.scene.SceneManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* レンダリングクラス */
public class GLRenderer implements GLSurfaceView.Renderer{

    /* システム */
    private MainActivity activity;
    private	GL10			_gl10;
    private int				screenW, screenH;
    private SceneManager sceneManager;
    private int 			frame = 0;
    private long 			lastTime = 0;
    private HashMap<String, Graph2D> textureMap;
    public Random random;

    /* 2Dバッファ */
    private FloatBuffer vertexBuffer;
    private FloatBuffer uvBuffer;

    /*--------------------------------------------------------------------*/

    public GLRenderer(MainActivity _activity){
        // TODO コンストラクタ
        super();
        activity = _activity;
        this.textureMap = new HashMap<String, Graph2D>();
        this.sceneManager = new SceneManager(this);


    }

    /*--------------------------------------------------------------------*/
    /* getter */
    public int          GetScreenWidth(){		return this.screenW;	}
    public int          GetScreenHeight(){		return this.screenH;	}
    public MainActivity getActivity(){	        return this.activity;	}

    public Graph2D GetGraph(String word) {
        // TODO 画像クラスの取得
        return this.textureMap.get(word);
    }

    /*--------------------------------------------------------------------*/

    /* オーバーライド */
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig config) {
        _gl10 = gl10;

        // TODO サーフェイス生成
        //頂点配列の有効化
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl10.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        //テクスチャの有効化
        gl10.glEnable(GL10.GL_TEXTURE_2D);

        //ブレンドの指定
        gl10.glEnable(GL10.GL_BLEND);
        gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        //頂点バッファの生成
        vertexBuffer = makeVertexBuffer();

        //UVバッファの生成
        uvBuffer = makeUVBuffer();

        //現在時刻の取得
        Calendar calender = Calendar.getInstance();
        Date dps = calender.getTime();
        this.lastTime = dps.getTime();

        sceneManager = new SceneManager(this);
        this.random = new Random();

        System.out.println("onSurfaceCreated success");
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        // TODO サーフェイス変更

        gl10.glViewport(0, 0, width, height);
        screenW = width;
        screenH = height;

        this.screenW = ((MainActivity)activity).GetScreenWidth();
        this.screenH = ((MainActivity)activity).GetScreenHeight();

        System.out.println("onSurfaceChanged isScene:" + sceneManager.IsScene());
        if( debug == 0 && !sceneManager.IsScene() ) {
            debug++;
            sceneManager.Add(new LoadingScene(sceneManager));
        }
    }

    private int debug = 0;

    @Override
    public void onDrawFrame(GL10 gl10) {
        // TODO フレーム描画
        sceneManager.Step();

        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl10.glActiveTexture(GL10.GL_TEXTURE0);
        gl10.glTexCoordPointer(2, GL10.GL_FLOAT, 0, uvBuffer);

        this.sceneManager.Draw();
    }

    /*--------------------------------------------------------------------*/

    private int makeTexture(GL10 gl10, Bitmap bmp)
    {
        // TODO テクスチャの生成

        //テクスチャのメモリ確保
        int[] textureIds = new int[1];
        gl10.glGenTextures(1, textureIds, 0);

        //テクスチャへのビットマップ指定
        gl10.glActiveTexture(GL10.GL_TEXTURE0);
        gl10.glBindTexture(GL10.GL_TEXTURE_2D, textureIds[0] );
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0 );

        //テクスチャのフィルタ指定
        gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);

        return textureIds[0];
    }

    private FloatBuffer makeUVBuffer() {
        // TODO UVバッファの生成
        float[] uv = {
                0.0f, 0.0f,	//左上
                0.0f, 1.0f,	//左下
                1.0f, 0.0f,	//右上
                1.0f, 1.0f,	//右下
        };
        return makeFloatBuffer(uv);
    }

    private FloatBuffer makeVertexBuffer() {
        // TODO 頂点バッファの生成
        float[] vertexs =
                {
                        -1.0f,  1.0f, 0.0f,
                        -1.0f, -1.0f, 0.0f,
                        1.0f,  1.0f, 0.0f,
                        1.0f, -1.0f, 0.0f,
                };

        return makeFloatBuffer(vertexs);
    }

    private FloatBuffer makeFloatBuffer(float[] array) {
        // TODO float配列をFloatBufferに変換
        FloatBuffer fb = ByteBuffer.allocateDirect(array.length * 4).order(
                ByteOrder.nativeOrder()).asFloatBuffer();
        fb.put(array).position(0);
        return fb;
    }

    public void InputGraph(int key, String newWord, int width, int height) {
        // TODO 画像クラスのセッティング
        Graph2D gh = new Graph2D(this, key, width, height);
        this.textureMap.put(newWord, gh);
    }

    public int loadGraph(int id) {
        // TODO 画像のロード
        Bitmap bmp = BitmapFactory.decodeResource(activity.getResources(), id );
        return makeTexture(_gl10, bmp );
    }

    public void SetTexture(int checkkey) {
        // TODO テクスチャのセッティング
        _gl10.glBindTexture(GL10.GL_TEXTURE_2D, checkkey );
    }

    public void setTextureArea(int textureW, int textureH, int x, int y, int w, int h)
    {
        // TODO テクスチャエリアの設定

        //ピクセル座標をＵＶ座標に変換
        float tw = (float)w/(float)textureW;
        float th = (float)h/(float)textureH;
        float tx = (float)x/(float)textureW;
        float ty = (float)y/(float)textureH;

        //テクスチャ行列の移動・拡大
        _gl10.glMatrixMode(GL10.GL_TEXTURE);
        _gl10.glLoadIdentity();
        _gl10.glTranslatef(tx, ty, 0.0f);
        _gl10.glScalef(tw, th, 1.0f);
    }

    public void SetColor(GameColor incolor) {
        // TODO 色の設定
        _gl10.glColor4f(incolor.y, incolor.z, incolor.w, incolor.x );
    }

    public void DrawRect(int x, int y, int w, int h, float ang) {
        // TODO 矩形描画

        float sx = ((float)x/(float)screenW)*2.0f-1.0f;
        float sy = ((float)y/(float)screenH)*2.0f-1.0f;
        float sw = ((float)w/(float)screenW);
        float sh = ((float)h/(float)screenH);

        _gl10.glMatrixMode(GL10.GL_MODELVIEW);
        _gl10.glLoadIdentity();
        _gl10.glTranslatef(sx+sw, -(sy+sh), 0.0f);
        _gl10.glScalef(sw, sh, 1.0f);
        _gl10.glRotatef(ang, 0, 0, 1);

        _gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        _gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    }

    public void DrawCenterRect(int x, int y, int w, int h, float ang) {
        // TODO 座標中心描画

        float sx = ((float)x/(float)screenW)*2.0f-1.0f;
        float sy = ((float)y/(float)screenH)*2.0f-1.0f;
        float sw = ((float)w/(float)screenW);
        float sh = ((float)h/(float)screenH);

        _gl10.glMatrixMode(GL10.GL_MODELVIEW);
        _gl10.glLoadIdentity();
        _gl10.glTranslatef(sx, -(sy), 0.0f);
        _gl10.glScalef(sw, sh, 1.0f);
        _gl10.glRotatef(ang, 0, 0, 1);

        _gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        _gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    }

    public void setAddBlend() {
        // TODO アルファ描画の設定
        _gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
        _gl10.glEnable(GL10.GL_BLEND);
    }

    public void backtoBlend() {
        // TODO 背景ブレンド設定
        _gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        _gl10.glEnable(GL10.GL_BLEND);
    }

    public void StopMusic() {
        // TODO 音楽停止
        this.sceneManager.StopMusic();
    }

    public void PauseMusic() {
        // TODO 音楽の再開
        this.sceneManager.PauseMusic();
    }

    //― アルファブレンド ―
    //glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    //―加算合成 ―
    //glBlendFunc(GL_SRC_ALPHA, GL_ONE);

    //― 乗算合成 ―
    //glBlendFunc(GL_ZERO, GL_SRC_COLOR);

    //― 反転合成 ―
    //glBlendFunc(GL_ONE_MINUS_DST_COLOR, GL_ZERO);

    //― スクリーン合成 ―
    //glBlendFunc(GL_ONE_MINUS_DST_COLOR, GL_ONE);

    //― 排他的論理和合成 ―
    //glBlendFunc(GL_ONE_MINUS_DST_COLOR, GL_ONE_MINUS_SRC_COLOR);

    //― 上書き(デフォルトはこれ) ―
    //glBlendFunc(GL_ONE, GL_ZERO)
}
