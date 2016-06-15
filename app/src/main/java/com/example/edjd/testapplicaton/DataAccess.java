package com.example.edjd.testapplicaton;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertmatthewcook on 6/15/2016.
 */

public class DataAccess {

    private long nextScoreID(SQLiteDatabase db) {
        long scoreID;
        String query = "SELECT MAX(CAST(score_id as integer)) + 1 FROM score;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        scoreID = results.getLong(0);
        return scoreID;
    }
    public void addScore(SQLiteDatabase db, long score_value, long user_id) {
        ContentValues score = new ContentValues();
        score.put("score_id", this.nextScoreID(db));
        score.put("game_id", "101");
        score.put("user_id", user_id);
        score.put("score_value", score_value);
        score.put("score_date", "2016-06-15");
    }
    public Map readScoreboard(SQLiteDatabase db) {
        Map scoreboard = new HashMap();
        String query = "SELECT user.user_name, score.score_value FROM score JOIN user ON user.user_id = score.user_id WHERE score.game_id = '101' AND score.score_date = '2016-06-14' ORDER BY score.score_value DESC, user.user_name";
        Cursor results = db.rawQuery(query, null);
        if (results.moveToFirst()) {
            do {
                scoreboard.put(results.getString(0), results.getLong(1));;
            } while (results.moveToNext());
        }
        return scoreboard;
    }
}
