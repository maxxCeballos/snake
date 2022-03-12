package laboratorio.programacion.snake;

import android.content.Context;
import android.content.SharedPreferences;

final class GameState {

    private static volatile boolean mPlaying = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGameOver = true;
//    private static volatile boolean mDrawing = false;

    // This object will have access to the deSpawnReSpawn method in GameEngine once it is initialized
    private GameStarter gameStarter;

    private int mScore;
    private int mHighScore;

    // This is how we will make all the high scores persist
    private SharedPreferences.Editor mEditor;

    GameState(GameStarter gs, Context context){
        // This initializes the gameStarter reference
        gameStarter = gs;

        // Get the current high score
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("HiScore", Context.MODE_PRIVATE);

        // Whenever we want to edit the HiScore file, we will need to use mEditor, and whenever we need to read the HiScore file, we will use prefs.
        mEditor = prefs.edit();

        // Load high score from a entry in the file labeled "hiscore". If not available highscore set to zero 0
        mHighScore = prefs.getInt("hi_score", 0);
    }


//    private void endGame(){
//        mGameOver = true;
//        mPaused = true;

//        if(mScore > mHighScore){
//            mHighScore = mScore;
//
//            // Save high score
//            mEditor.putInt("hi_score", mHighScore);
//            mEditor.commit();
//        }
//    }

    void startNewGame(){
        mScore = 0;

        // Don't want to be drawing objects while deSpawnReSpawn is clearing them and spawning them again
//        stopDrawing();
//        gameStarter.deSpawnReSpawn();
//        resume();
        // Now we can draw again
//        startDrawing();
    }

    // aca deberia ir la logica de chocarse con las paredes o comerse a si misma
//    void loseLife(SoundEngine se){
//        mNumShips--;
//        se.playPlayerExplode();
//        if(mNumShips == 0){
//            pause();
//            endGame();
//        }
//    }


    void increaseScore(){
        mScore++;
    }

    int getScore(){
        return mScore;
    }

    int getHighScore(){
        return mHighScore;
    }

    void pause(){
        mPaused = true;
    }

    boolean getPaused(){
        return mPaused;
    }

    void resume(){
        mGameOver = false;
        mPaused = false;
    }

    void stopEverything(){
        mPaused = true;
        mGameOver = true;
        mPlaying = false;
    }

//    boolean getGameOver(){
//        return mGameOver;
//    }

    boolean getThreadRunning(){
        return mPlaying;
    }

    void startThread(){
        mPlaying = true;
    }

//    private void stopDrawing(){
//        mDrawing = false;
//    }
//
//    private void startDrawing(){
//        mDrawing = true;
//    }
//
//    boolean getDrawing() {
//        return mDrawing;
//    }



}