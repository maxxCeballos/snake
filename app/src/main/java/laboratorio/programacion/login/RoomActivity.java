package laboratorio.programacion.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import laboratorio.programacion.snake.R;
import laboratorio.programacion.snake.SnakeActivity;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnPlay, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnPlay.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.btnPlay:
                intent = new Intent(RoomActivity.this, SnakeActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
}