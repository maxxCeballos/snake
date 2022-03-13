package laboratorio.programacion.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


// This will be the game engine
public class GameEngine extends SurfaceView implements Runnable, GameStarter {

    // Objects for the game loop/thread
    private Thread mThread = null;

    // Control pausing between updates
    private long mNextFrameTime;

    private GameState mGameState;
    private SoundEngine mSoundEngine;

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 50;
    private int mNumBlocksHigh;

    // Objects for drawing
    // se pasan referencias de estos objetos a las clases que representan los objetos del juego, para que puedan dibujarse a sí mismos en lugar de hacerlo en esta clase.
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    // Game Objects
    private Snake mSnake;
    private Apple mApple;

    // scaling text
    private int mTextFormatting;

    public GameEngine(Context context, Point size) {
        super(context);

        mGameState = new GameState(this, context);
        mSoundEngine = new SoundEngine(context);

        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

        mTextFormatting = size.x / 50; // value 50 is a little arbitrary, seemed to work well whit varying screen sizes.

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

        // Call the constructors of our two game objects
        mApple = new Apple(context, new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh), blockSize);
        mSnake = new Snake(context, new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh), blockSize);

    }

    // Called to start a new game
    public void newGame() {

        // reset the snake
        mSnake.reset(NUM_BLOCKS_WIDE, mNumBlocksHigh);

        // Get the apple ready for dinner
        mApple.spawn();

        // Reset the mScore
        mGameState.startNewGame(); // a esto falta laburarlo, quedan cosas comentadas en el metodo


        // Setup mNextFrameTime so an update can triggered
        // Configurar mNextFrameTime a la hora actual, produce que se active una actualización de inmediato
        mNextFrameTime = System.currentTimeMillis();
    }

    // Handles the game loop
    // Called by Android repeatedly while the thread is running
    @Override
    public void run() {

        while (mGameState.getThreadRunning()) {

            if(!mGameState.getPaused()) {
                // Update 10 times a second
                if (updateRequired()) {
                    update();
                }
            }
            draw();
        }
    }

    // Check to see if it is time for an update
    public boolean updateRequired() {
        // Run at 10 frames per second
        final long TARGET_FPS = 10;
        // There are 1000 milliseconds in a second
        final long MILLIS_PER_SECOND = 1000;

        // Are we due to update the frame
        if(mNextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed
            // Setup when the next update will be triggered
            mNextFrameTime = System.currentTimeMillis() + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw
            // methods are executed
            return true;
        }

        return false;
    }

    // Update all the game objects
    public void update() {
        // Move the snake
        mSnake.move();

        // Did the head of the snake eat the apple?
        if(mSnake.checkDinner(mApple.getLocation())){
            // This reminds me of Edge of Tomorrow.
            // One day the apple will be ready!
            mApple.spawn();

            // Add to  mScore
            mGameState.increaseScore();

            // Play a sound
            mSoundEngine.eatSound();
        }

        // Did the snake die?
        if (mSnake.detectDeath()) {
            // Pause the game ready to start again
            mSoundEngine.crashSound();
            mGameState.endGame();
        }
    }

    // Do all the drawing
    public void draw() {
        // Get a lock on the mCanvas
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(mTextFormatting);

            // Draw the score
            mCanvas.drawText("HiScore: " + mGameState.getHighScore(), mTextFormatting,mTextFormatting, mPaint);
            mCanvas.drawText("Score: " + mGameState.getScore(), mTextFormatting, mTextFormatting * 2, mPaint);

            // Draw the apple and the snake
            mApple.draw(mCanvas, mPaint);
            mSnake.draw(mCanvas, mPaint);

            // Draw some text while paused
            if(mGameState.getPaused()){
                // Set the size and color of mPaint for the text
                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(50);

                // Draw the message
                // We will give this an international upgrade soon
                mCanvas.drawText(getResources().getString(R.string.tap_to_play), 200, 400,mPaint);
            }

            // Unlock the Canvas to show graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    // Called by Android every time the player interacts with the screen
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:

                if (mGameState.getPaused()) {
                    mGameState.resume();
                    newGame();
                    // Don't want to process snake direction for this tap
                    return true;
                }

                // Let the Snake class handle the input
                mSnake.switchHeading(motionEvent);
                break;
            default:
                break;
        }
        return true;
    }

    // Stop the thread
    public void stopEverything() {
        mGameState.stopEverything();

        try {
            mThread.join();
        } catch (InterruptedException e) {
            Log.e("Exception", "pauseThread()" + e.getMessage());
        }
    }

    // Start the thread
    public void startThread() {
        mGameState.startThread();

        // pass this because the class implements Runnable and this is exactly what is required by de Thread class.
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void deSpawnReSpawn() {
        // Eventually this will despawn
        // and then respawn all the game objects
    }
}
