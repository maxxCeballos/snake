package laboratorio.programacion.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import laboratorio.programacion.snake.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText usuario, password;
    Button btnRegistrar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usuario = (EditText) findViewById(R.id.userRegister);
        password = (EditText) findViewById(R.id.passRegister);

        btnRegistrar = (Button) findViewById(R.id.btnCreateAccount);
        btnCancelar = (Button) findViewById(R.id.btnCancel);

        btnRegistrar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnCreateAccount:
                createAccount();
                break;
            case R.id.btnCancel:
                finish(); // close activity Register.
                break;
        }

    }


    public void createAccount() {
        if ( accountAlreadyExists(usuario.getText().toString())) {
            Toast.makeText(this, R.string.account_already_exists, Toast.LENGTH_LONG).show();
        } else {
            Util.db.addAccount(new Account(usuario.getText().toString(), password.getText().toString(), 0));

            // Get 1 account
            Account account = Util.db.getAccount(usuario.getText().toString());

            if(account != null) {
                Toast.makeText(this, R.string.account_created, Toast.LENGTH_LONG).show();
                finish(); // close activity Register.
            } else {
                Toast.makeText(this, R.string.account_created_error, Toast.LENGTH_LONG).show();
                usuario.getText().clear();
                password.getText().clear();
            }
        }
    }


    public boolean accountAlreadyExists(String username) {

        boolean accountExists = false;

        if ( Util.db.getAccount(username) != null ) {
            accountExists = true;
        }

        return accountExists;
    }
}