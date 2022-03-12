package laboratorio.programacion.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;

// controlling the drawing
public class Renderer {

    // Objects for drawing
    // se pasan referencias de estos objetos a las clases que representan los objetos del juego,
    // para que puedan dibujarse a sí mismos en lugar de hacerlo en esta clase.
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    Renderer(SurfaceView sh){
        mSurfaceHolder = sh.getHolder();
        mPaint = new Paint();
    }


    void draw(GameState gs, HUD hud) {


        // Get a lock on the mCanvas
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw the apple and the snake
//            mApple.draw(mCanvas, mPaint);
//            mSnake.draw(mCanvas, mPaint);

            // Draw some text while paused
            if(gs.getPaused()){
                // Set the size and color of mPaint for the text
                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(50);

                // Draw the message
                // We will give this an international upgrade soon
                // mCanvas.drawText(getResources().getString(R.string.tap_to_play), 200, 400,mPaint);
            }

            // Now we draw the HUD on top of everything else
            hud.draw(mCanvas, mPaint, gs);

            // Unlock the Canvas to show graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }




//        if (mSurfaceHolder.getSurface().isValid()) {
//            mCanvas = mSurfaceHolder.lockCanvas();
//            mCanvas.drawColor(Color.argb(255, 0, 0, 0));

//            if (gs.getDrawing()) {
//                // Draw all the game objects here
//            }
//
//            if(gs.getGameOver()) {
//                // Draw a background graphic here
//            }

            // Draw a particle system explosion here
            // Now we draw the HUD on top of everything else
//            hud.draw(mCanvas, mPaint, gs);
//            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
//        }
    }
}
