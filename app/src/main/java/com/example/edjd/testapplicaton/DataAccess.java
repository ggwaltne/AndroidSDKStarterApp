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

    private long nextUserID(SQLiteDatabase db) {
        long user_id;
        String query = "SELECT MAX(CAST(user_id AS integer)) + 1 FROM user;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        user_id = results.getLong(0);
        return user_id;
    }
    public void addUser(SQLiteDatabase db, String user_name) {
        ContentValues user = new ContentValues();
        user.put("user_id", this.nextListingID(db));
        user.put("user_name", user_name);
        user.put("user_email", user_name+"@fake.it");
        user.put("user_create_date", "2016-06-15");
        user.put("user_verified_date", "2016-06-15");
        user.put("user_deactivated_date", "");
        db.insert("user", null, user);
    }
    private long nextListingID(SQLiteDatabase db) {
        long listing_id;
        String query = "SELECT MAX(CAST(listing_id AS integer)) + 1 FROM listing;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        listing_id = results.getLong(0);
        return listing_id;
    }
    public void addListing(SQLiteDatabase db, long advertisement_id) {
        ContentValues listing = new ContentValues();
        listing.put("listing_id", this.nextListingID(db));
        listing.put("advertisement_id", advertisement_id);
        listing.put("listing_date", "2016-06-15");
        db.insert("listing", null, listing);
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
        ContentValues click = new ContentValues();
        click.put("click_id", this.nextClickID(db));
        click.put("advertisement_id", listing_id);
        click.put("listing_date", "2016-06-15");
        db.insert("click", null, click);
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
        long rank=1;
        Map map = new HashMap();
        SortedMap scoreboard = new TreeMap(map);

        String query = "SELECT user.user_name, score.score_value FROM score JOIN user ON user.user_id = score.user_id WHERE score.game_id = '101' ORDER BY score.score_value DESC, user.user_name";
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
