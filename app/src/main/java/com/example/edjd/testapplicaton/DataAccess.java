package com.example.edjd.testapplicaton;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertmatthewcook on 6/15/2016.
 */

/* Inner class that defines the table contents
public static abstract class MarketRow implements BaseColumns {
    public static final String MARKET = "market";
    public static final String MARKET_ID = "market_id";
    public static final String MARKET_NAME = "market_name";
}

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

*/

public class DataAccess {

    public Map readScoreboard(SQLiteDatabase db) {
        Map scoreboard = new HashMap();
        String selectQuery = "SELECT user.username, score.score_value FROM score JOIN user ON user.user_id = score.user_id WHERE score.game_id = '101' AND score.score_date = '2016-06-15' ORDER BY score.score_value DESC, user.username";

        Cursor results = db.rawQuery(selectQuery, null);

        if (results.moveToFirst()) {
            do {
                scoreboard.put(results.getString(0), results.getLong(1));;
            } while (results.moveToNext());
        }
        return scoreboard;
    }
}
