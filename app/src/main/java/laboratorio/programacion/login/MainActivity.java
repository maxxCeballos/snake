package laboratorio.programacion.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import laboratorio.programacion.snake.R;
import laboratorio.programacion.snake.SnakeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText usuario, password;
    Button btnEntrar, btnRegistrar, btnSalir;
//    DAL db;

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

        Util.db = new DAL(this);
    }


    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){
            case R.id.btnEntrar:
                if(login()) {
                    intent = new Intent(MainActivity.this, RoomActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.account_not_found, Toast.LENGTH_LONG).show();
                }

                usuario.getText().clear();
                password.getText().clear();

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


    public boolean login() {
        boolean isRegistered = false;
        Account accountDB;
        String username = this.usuario.getText().toString();
        String password = this.password.getText().toString();

        accountDB = Util.db.getAccount(username);


        if (accountDB != null && accountDB.getPassword().equals(password)) {
            isRegistered = true;
            Util.accountPlayed = accountDB;
        }

        return isRegistered;
    }
}