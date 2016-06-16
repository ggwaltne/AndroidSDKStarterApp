package com.example.edjd.testapplicaton;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by robertmatthewcook on 6/15/2016.
 */

public class DataAccess {

    private static final String SQL_DELETE_DATA = "DELETE FROM user;DELETE FROM game; DELETE FROM score; DELETE FROM listing; DELETE FROM click;";

    private static final String SQL_GENERATE_GAME = "INSERT INTO game SELECT data.* FROM (select '101' AS game_id, 'ExGame', '2016-06-14', '2016-06-14' UNION ALL SELECT '102', 'WhyGame', '2016-06-15', NULL) as data LEFT JOIN game ON game.game_id = data.game_id WHERE game.game_id IS NULL;";
    private static final String SQL_GENERATE_USER = "INSERT INTO user SELECT data.* FROM (select '1' AS user_id, 'Jeff' AS user_name, 'one@fake.it' AS user_email, '2016-01-01' AS user_create_date, '2016-01-01' AS user_verified_date, NULL AS user_deactivated_date UNION ALL SELECT '2', 'Tessa', 'two@fake.it', '2016-01-02', '2016-01-02', NULL UNION ALL SELECT '3', 'Robert', 'three@fake.it', '2016-01-03', '2016-01-03', NULL) as data LEFT JOIN user ON user.user_id = data.user_id WHERE user.user_id IS NULL;";
    private static final String SQL_GENERATE_SCORE = "INSERT INTO score SELECT data.* FROM (select '1001' AS score_id, 101 AS game_id, 3 AS user_id, 4321 AS score_value UNION ALL SELECT 1002, 101, 2, 2468 UNION ALL SELECT 1003, 101, 1, 1111) as data LEFT JOIN score ON score.score_id = data.score_id WHERE score.score_id IS NULL;";

    private long nextUserID(SQLiteDatabase db) {
        long user_id;
        String query = "SELECT MAX(CAST(user_id AS integer)) + 1 FROM user;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        user_id = results.getLong(0);
        return user_id;
    }
    public long addUser(SQLiteDatabase db, String user_name) {
        long user_id = this.nextUserID(db);
        ContentValues user = new ContentValues();
        user.put("user_id", user_id);
        user.put("user_name", user_name);
        user.put("user_email", user_name+"@fake.it");
        user.put("user_create_date", "2016-06-15");
        user.put("user_verified_date", "2016-06-15");
        user.put("user_deactivated_date", "");
        db.insert("user", null, user);
        return user_id;
    }
    private long nextListingID(SQLiteDatabase db) {
        long listing_id;
        String query = "SELECT MAX(CAST(listing_id AS integer)) + 1 FROM listing;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        listing_id = results.getLong(0);
        return listing_id;
    }
    public long addListing(SQLiteDatabase db, long advertisement_id) {
        long listing_id = this.nextListingID(db);
        ContentValues listing = new ContentValues();
        listing.put("listing_id", listing_id);
        listing.put("advertisement_id", advertisement_id);
        listing.put("listing_date", "2016-06-15");
        db.insert("listing", null, listing);
        return listing_id;
    }
    private long nextClickID(SQLiteDatabase db) {
        long click_id;
        String query = "SELECT MAX(CAST(click_id AS integer)) + 1 FROM click;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        click_id = results.getLong(0);
        return click_id;
    }
    public long addClick(SQLiteDatabase db, long listing_id) {
        long click_id = this.nextClickID(db);
        ContentValues click = new ContentValues();
        click.put("click_id", click_id);
        click.put("advertisement_id", listing_id);
        click.put("listing_date", "2016-06-15");
        db.insert("click", null, click);
        return click_id;
    }
    private long nextScoreID(SQLiteDatabase db) {
        long score_id;
        String query = "SELECT MAX(CAST(score_id AS integer)) + 1 FROM score;";
        Cursor results = db.rawQuery(query, null);
        results.moveToFirst();
        score_id = results.getLong(0);
        return score_id;
    }
    public long addScore(SQLiteDatabase db, long score_value, long user_id) {
        long score_id = this.nextScoreID(db);
        ContentValues score = new ContentValues();
        score.put("score_id", score_id);
        score.put("game_id", "101");
        score.put("user_id", user_id);
        score.put("score_value", score_value);
        db.insert("score", null, score);
        return score_id;
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
    public void dbDeleteUserData(SQLiteDatabase db){
            db.execSQL(SQL_DELETE_DATA);
    }
    public void dbInsertTestData(SQLiteDatabase db) {
        db.execSQL(SQL_GENERATE_USER);
        db.execSQL(SQL_GENERATE_GAME);
        db.execSQL(SQL_GENERATE_SCORE);
    }
}
