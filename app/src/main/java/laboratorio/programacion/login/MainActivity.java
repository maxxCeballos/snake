package laboratorio.programacion.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import laboratorio.programacion.snake.R;
import laboratorio.programacion.snake.SnakeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText usuario, password;
    Button btnEntrar, btnRegistrar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){
            case R.id.btnEntrar:
                intent = new Intent(MainActivity.this, SnakeActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRegistrar:
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSalir:
                finish();
                System.exit(0);
                break;
        }
    }
}