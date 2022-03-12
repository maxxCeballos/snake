package laboratorio.programacion.snake;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.ArrayList;

// This will be the game engine
public class GameEngine extends SurfaceView implements Runnable, GameStarter, GameEngineBroadcaster {

    // Objects for the game loop/thread
    private Thread mThread = null;

    // Control pausing between updates
    private long mNextFrameTime;

    private ArrayList<InputObserver> inputObservers = new ArrayList();
    UIController mUIController;

    private GameState mGameState;
    private SoundEngine mSoundEngine;
    HUD mHUD;
    Renderer mRenderer;

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int mNumBlocksHigh;


    // A snake ssss
    private Snake mSnake;
    // And an apple
    private Apple mApple;

    public GameEngine(Context context, Point size) {
        super(context);

        mUIController = new UIController(this);
        mGameState = new GameState(this, context);
        mSoundEngine = new SoundEngine(context);
        mHUD = new HUD(size);
        mRenderer = new Renderer(this);

        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

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

            mRenderer.draw(mGameState, mHUD);
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


    @Override
    // Called by Android every time the player interacts with the screen
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // Handle the player's input here
        // But in a new way
        for (InputObserver o : inputObservers) {
            o.handleInput(motionEvent, mGameState, mHUD.getControls());
        }

        return true;


//        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_UP:
//
//                if (mGameState.getPaused()) {
//                    mGameState.resume();
//                    newGame();
//                    // Don't want to process snake direction for this tap
//                    return true;
//                }
//
//                // Let the Snake class handle the input
//                mSnake.switchHeading(motionEvent);
//                break;
//            default:
//                break;
//        }
//        return true;
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

    // For the game engine broadcaster interface
    public void addObserver(InputObserver o) {
        inputObservers.add(o);
    }
}
