package laboratorio.programacion.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DAL extends SQLiteOpenHelper {

    public DAL(Context context){
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_USERNAME + " TEXT UNIQUE,"
                + Util.KEY_PASSWORD + " TEXT,"
                + Util.KEY_HIGHSCORE + " INTEGER"
                + ")";

        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }

    /**
     * CRUD OPERATIONS
     */

    // CREATE ACCOUNT
    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(Util.KEY_USERNAME, account.getUsername());
        value.put(Util.KEY_PASSWORD, account.getPassword());
        value.put(Util.KEY_HIGHSCORE, account.getHighscore()); // score start from 0.

        db.insert(Util.TABLE_NAME, null, value); // insert row
        db.close(); // close db connection
    }

    // GET ACCOUNT
    public Account getAccount(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                Util.TABLE_NAME,
                new String[]{ Util.KEY_USERNAME, Util.KEY_PASSWORD, Util.KEY_HIGHSCORE},
                Util.KEY_USERNAME + "=?", new String[] {username},
                null, null, null, null
        );

        if (cursor != null ) {
            cursor.moveToFirst();
        }

        // index columns pass to cursor
        Account account = new Account(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));

        return account;
    }

    // GET ALL ACCOUNTS
    public List<Account> getAllAccounts() {

        SQLiteDatabase db = this.getReadableDatabase();

        List<Account> accounts = new ArrayList<>();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        // Loop through accounts
        if (cursor.moveToFirst()) {
            do {
                // only list username & highscore
                Account account = new Account();
                account.setUsername(cursor.getString(1));
                account.setHighscore(Integer.parseInt(cursor.getString(3)));

                accounts.add(account);
            } while(cursor.moveToNext());
        }

        return accounts;
    }
}
