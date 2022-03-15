package laboratorio.programacion.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import laboratorio.programacion.snake.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DAL db = new DAL(this);

        // Insert accounts
//        Log.d("Insert: ", "Inserting...");
//        db.addAccount(new Account("bob123", "123456", 0));
//        db.addAccount(new Account("bob456", "123456", 100));
//        db.addAccount(new Account("bob789", "123456", 80));

        // Read accounts
        Log.d("Reading: ", "Reading all accounts...");
        List<Account> accountList = db.getAllAccounts();

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
}