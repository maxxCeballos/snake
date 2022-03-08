package laboratorio.programacion.snake;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.app.Activity;

public class SnakeActivity extends Activity {

    GameEngine mGameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Get the pixel dimensions of the screen
        Display display = getWindowManager().getDefaultDisplay();

        // Initialize the result into a Point object
        Point size = new Point();
        display.getSize(size);

        // Create a new instance of the SnakeGame class
        mGameEngine = new GameEngine(this, size);

        // Make snakeGame the view of the Activity
        setContentView(mGameEngine);
        //setContentView(R.layout.activity_main);
    }

    // Start the thread in snakeGame
    @Override
    protected void onResume() {
        super.onResume();
        mGameEngine.resume();
    }

    // Stop the thread in snakeGame
    @Override
    protected void onPause() {
        super.onPause();
        mGameEngine.pause();
    }
}