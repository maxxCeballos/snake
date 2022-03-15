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
    DAL db;

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

        db = new DAL(this);

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
                db.addAccount(new Account(usuario.getText().toString(), password.getText().toString(), 0));

                // Get 1 account
                Account aAccount = db.getAccount(usuario.getText().toString());
                Log.d("CREATE-ACCOUNT ", "USERNAME: " + aAccount.getUsername() + " PASS: " + aAccount.getPassword() + " HIGHSCORE: " + aAccount.getHighscore());
//                if ( account.isNull()){}
//                if( dalAccount.insertAccount(account) ) {
                Toast.makeText(this, "CUENTA REGISTRADA", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(this, "ERROR EN REGISTRO", Toast.LENGTH_LONG).show();
//                }
                break;
            case R.id.btnCancel:
                break;
        }

        finish(); // close activity Register.
    }
}