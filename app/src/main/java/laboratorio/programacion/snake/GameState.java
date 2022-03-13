package laboratorio.programacion.snake;

import android.content.Context;
import android.content.SharedPreferences;

final class GameState {

    private static volatile boolean mPlaying = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGameOver = true;

    private int mScore;
    private int mHighScore;

    // This is how we will make all the high scores persist
    private SharedPreferences.Editor mEditor;

    GameState(Context context){

        // Get the current high score
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("HiScore", Context.MODE_PRIVATE);

        // Whenever we want to edit the HiScore file, we will need to use mEditor, and whenever we need to read the HiScore file, we will use prefs.
        mEditor = prefs.edit();

        // Load high score from a entry in the file labeled "hiscore". If not available highscore set to zero 0
        mHighScore = prefs.getInt("hi_score", 0);
    }


    void endGame(){
        mGameOver = true;
        mPaused = true;

        if(mScore > mHighScore){
            mHighScore = mScore;

            // Save high score
            mEditor.putInt("hi_score", mHighScore);
            mEditor.commit();
        }
    }

    void startNewGame(){
        mScore = 0;
    }

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

    boolean getGameOver(){
        return mGameOver;
    }

    boolean getThreadRunning(){
        return mPlaying;
    }

    void startThread(){
        mPlaying = true;
    }

}