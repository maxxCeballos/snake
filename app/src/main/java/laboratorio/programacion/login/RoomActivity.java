package laboratorio.programacion.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import laboratorio.programacion.snake.R;
import laboratorio.programacion.snake.SnakeActivity;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnPlay, btnBack;
    Switch switchSound;
    TableLayout ranking;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnBack = (Button) findViewById(R.id.btnBack);
        switchSound = (Switch) findViewById(R.id.switchSound);
        ranking = (TableLayout) findViewById(R.id.rankingList);

        loadRankingList();

        btnPlay.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Util.SOUND_ENABLE  = true;
                } else {
                    Util.SOUND_ENABLE = false;
                }
            }
        });
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


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void loadRankingList() {

        List<Account> accountList = Util.db.getAllAccounts();

        Collections.sort(accountList, Comparator.comparingInt(Account ::getHighscore));
        Collections.reverse(accountList);

        for (Account account : accountList ) {

            TableRow row = new TableRow(this);

            TextView vRow1 = new TextView(this);
            vRow1.setText(account.getUsername() + "  " + account.getHighscore());
            vRow1.setTextColor(Color.BLACK);

            row.addView(vRow1);
            ranking.addView(row);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();

        this.ranking.removeAllViews();

        loadRankingList();
    }

}