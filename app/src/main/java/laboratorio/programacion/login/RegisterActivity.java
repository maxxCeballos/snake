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

//        DAL db = new DAL(this);

        // Insert accounts
//        Log.d("Insert: ", "Inserting...");
//        db.addAccount(new Account("bob123", "123456", 0));
//        db.addAccount(new Account("bob456", "123456", 100));
//        db.addAccount(new Account("bob789", "123456", 80));

        // Read accounts
//        Log.d("Reading: ", "Reading all accounts...");
//        List<Account> accountList = db.getAllAccounts();

        // Get 1 account
//        Account aAccount = db.getAccount("bob456");
//
//        Log.d("One Account: ", "USERNAME: " + aAccount.getUsername() + " HIGHSCORE: " + aAccount.getHighscore());
//
//        aAccount.setHighscore(222);

//        int numberRow = db.updateHighScoreAccount(new Account("maxigil", "123456", 80));
//
//        Log.d("ROW FROM UPDATE", "ROW: " + numberRow);

//        Account otherAccount = db.getAccount("maxigil");
//
//        Log.d("Updated Account: ", "ROW: " + numberRow + "USERNAME: " + otherAccount.getUsername() + " HIGHSCORE: " + otherAccount.getHighscore());


//        for (Account account : accountList ) {
//            String log = "USERNAME: " +  account.getUsername() + " HIGHSCORE: " + account.getHighscore();
//            Log.d("Account: ", log);
//
//            db.deleteAccount(account);
//        }
//
//        Log.d("AFTER DELETING", "+++++++++++++++++++++++");
//
//        accountList = db.getAllAccounts();
//
//        for (Account account : accountList ) {
//            String log = "USERNAME: " +  account.getUsername() + " HIGHSCORE: " + account.getHighscore();
//            Log.d("Account: ", log);
//        }


    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.btnCreateAccount:
//                Account account = new Account(usuario.getText().toString(), password.getText().toString(), 0);
//                if ( account.isNull()){}
//                if( dalAccount.insertAccount(account) ) {
//                    Toast.makeText(this, "CUENTA REGISTRADA", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(this, "ERROR EN REGISTRO", Toast.LENGTH_LONG).show();
//                }
                break;
            case R.id.btnCancel:
                break;
        }

        finish(); // para que cierre la activity Register.
    }
}