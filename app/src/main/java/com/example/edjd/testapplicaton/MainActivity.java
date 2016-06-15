package com.example.edjd.testapplicaton;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent LeaderBoard;
    Intent Game;
    Intent AdPage;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseWrapper mDbHelper = new DataBaseWrapper(getBaseContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        LeaderBoard = new Intent(getApplicationContext(), LeaderBoard.class);
        Game = new Intent(getApplicationContext(), GameActivity.class);
        AdPage = new Intent(getApplicationContext(), LeaderBoard.class);

    }
    public void OnClickLeaderBoard (View view) {
        startActivity(LeaderBoard);
    }

    public void OnClickGame(View view) {
        startActivity(Game);
    }

    public void OnClickAd(View view) {
        startActivity(AdPage);
    }


}
