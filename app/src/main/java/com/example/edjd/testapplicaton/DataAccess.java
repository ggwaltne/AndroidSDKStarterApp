package com.example.edjd.testapplicaton;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by robertmatthewcook on 6/15/2016.
 */

public class DataAccess {

    private long nextListingID(SQLiteDatabase db) {
        long listing_id;
        String query = "SELECT MAX(CAST(listing_id AS integer)) + 1 FROM listing;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        listing_id = results.getLong(0);
        return listing_id;
    }
    public void addListing(SQLiteDatabase db, long advertisement_id) {
        ContentValues score = new ContentValues();
        score.put("listing_id", this.nextListingID(db));
        score.put("advertisement_id", advertisement_id);
        score.put("listing_date", "2016-06-15");
    }
    private long nextClickID(SQLiteDatabase db) {
        long click_id;
        String query = "SELECT MAX(CAST(click_id AS integer)) + 1 FROM click;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        click_id = results.getLong(0);
        return click_id;
    }
    public void addClick(SQLiteDatabase db, long listing_id) {
        ContentValues score = new ContentValues();
        score.put("click_id", this.nextClickID(db));
        score.put("advertisement_id", listing_id);
        score.put("listing_date", "2016-06-15");
    }
    private long nextScoreID(SQLiteDatabase db) {
        long score_id;
        String query = "SELECT MAX(CAST(score_id AS integer)) + 1 FROM score;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        score_id = results.getLong(0);
        return score_id;
    }
    public void addScore(SQLiteDatabase db, long score_value, long user_id) {
        ContentValues score = new ContentValues();
        score.put("score_id", this.nextScoreID(db));
        score.put("game_id", "101");
        score.put("user_id", user_id);
        score.put("score_value", score_value);
        score.put("score_date", "2016-06-15");

        db.insert("score", null, score);
    }
    public Map readScoreboard(SQLiteDatabase db) {
        return this.readScoreboard(db,"2016-06-14");
    }
    public Map readScoreboard(SQLiteDatabase db, String score_date) {
        long rank=1;
        Map map = new HashMap();
        SortedMap scoreboard = new TreeMap(map);

        String query = "SELECT user.user_name, score.score_value FROM score JOIN user ON user.user_id = score.user_id WHERE score.game_id = '101' AND score.score_date = '" + score_date + "' ORDER BY score.score_value DESC, user.user_name";
        Cursor results = db.rawQuery(query, null);
        if (results.moveToFirst()) {
            do {
                String[] arrValues = {results.getString(0), results.getString(1)};
                scoreboard.put(rank++, arrValues);

                //scoreboard.put(results.getString(0), results.getLong(1));;
            } while (results.moveToNext());
        }
        return scoreboard;
    }
}
