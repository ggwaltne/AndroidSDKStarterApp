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

    /* Inner class that defines the table contents */
    public static abstract class MarketRow implements BaseColumns {
        public static final String MARKET = "market";
        public static final String MARKET_ID = "market_id";
        public static final String MARKET_NAME = "market_name";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseWrapper mDbHelper = new DataBaseWrapper(getBaseContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] marketRecord = {
                MarketRow.MARKET_ID,
                MarketRow.MARKET_NAME
        };
        Cursor cMarket = db.query(
                MarketRow.MARKET,       // The table to query
                marketRecord,           // The columns to return
                null,                   // The columns for the WHERE clause
                null,                   // The values for the mDbHelperWHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null                    // The sort order
        );

        cMarket.moveToFirst();
        long marketID = cMarket.getLong(cMarket.getColumnIndexOrThrow(MarketRow.MARKET_ID));

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
