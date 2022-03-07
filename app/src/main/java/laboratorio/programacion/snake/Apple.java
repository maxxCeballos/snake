package laboratorio.programacion.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

public class Apple {

    // Apple position on our virtual grid and not a specific pixel position
    private Point mLocation = new Point();

    // The range of values we can choose from to spawn an apple
    private Point mSpawnRange;

    // Hold the size in pixels of an apple. It will correspond to a single block on the grid.
    private int mSize;

    // An image to represent the apple
    private Bitmap mBitmapApple;

    public Apple(Context context, Point sr, int s) {
        // Make a note of the passed in spawn range
        mSpawnRange = sr;

        // Make a note of the size of an apple
        mSize = s;

        // Hide the apple off-screen until the game starts
        mLocation.x = -10;

        // Load the image to the bitmap
        mBitmapApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);

        // Resize the bitmap
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, s, s, false);
    }

    // This is called every time an apple is eaten
    void spawn(){
        // Choose two random values and place the apple
        Random random = new Random();
        mLocation.x = random.nextInt(mSpawnRange.x) + 1;
        mLocation.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    // Let SnakeGame know where the apple is
    // SnakeGame can share this with the snake
    Point getLocation(){
        return mLocation;
    }

    // Draw the apple, the game objects will handle drawing themselves.
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapApple, mLocation.x * mSize, mLocation.y * mSize, paint);
    }


}
