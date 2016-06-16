package com.example.edjd.testapplicaton;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent LeaderBoard;
    Intent Game;
    Intent AdPage;
    TextView text;
    public static final String USER_NAME = "UserTwo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseWrapper mDbHelper = new DataBaseWrapper(getBaseContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.onUpgrade(db,0,0);
        DataAccess da = new DataAccess();
        da.dbInsertTestData(db);

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
