package com.example.ballgameprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.example.ballgameprojects.library.GLRenderer;

/* startクラス */
public class MainActivity extends AppCompatActivity {

    /* field */
    private GLSurfaceView glView;
    private GLRenderer render;
    private int screenW, screenH;

    public int 		touchCount;
    public float[]	trgX, trgY;

    /*--------------------------------------------------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("oncreate");
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        glView = new GLSurfaceView(this);
        render = new GLRenderer(this);
        glView.setRenderer(render);
        setContentView(glView);

        //ウィンドウマネージャのインスタンス取得
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        //ディスプレイのインスタンス生成
        Display disp = wm.getDefaultDisplay();
        this.screenW = disp.getWidth();
        this.screenH = disp.getHeight();

        this.touchCount = 0;
        this.trgX = null;
        this.trgY = null;
    }

    public void onResume()
    {
        super.onResume();
        this.render.PauseMusic();
    }

    public void onPause()
    {
        super.onPause();
        this.render.StopMusic();
    }

    public void onDestroy(){
        super.onDestroy();
        this.render.StopMusic();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        //タッチアクション情報の取得
        int action = event.getAction();

        //タッチ座標点数の取得
        int count = event.getPointerCount();

        switch(action & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:	//１点タッチ
            case MotionEvent.ACTION_POINTER_DOWN:	//１点以上タッチ
                this.touchCount = count;
                trgX = new float[count];
                trgY = new float[count];
                for(int i=0; i<count; i++) {
                    trgX[i] = event.getX(i);
                    trgY[i] = event.getY(i);
                }
                return true;
            case MotionEvent.ACTION_UP:	//１点指を離す
            case MotionEvent.ACTION_POINTER_UP:	//２点以上指をタッチした状態で１つ離した
            case MotionEvent.ACTION_MOVE:	//繰り返し中
                this.touchCount = 0;
                trgX = null;
                trgY = null;
                return true;
        }
        return false;
    }

    /* getter */
    public int GetScreenWidth(){	return this.screenW;	}
    public int GetScreenHeight(){	return this.screenH;	}
}