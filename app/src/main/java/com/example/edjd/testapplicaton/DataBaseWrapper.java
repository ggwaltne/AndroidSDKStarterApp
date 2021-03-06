/**
 * Created by robertmatthewcook on 6/15/2016.
 */

package com.example.edjd.testapplicaton;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//DataBaseWrapper mDbHelper = new DataBaseWrapper(getContext());
//SQLiteDatabase db = mDbHelper.getWritableDatabase();
//SQLiteDatabase db = mDbHelper.getReadableDatabase();

public class DataBaseWrapper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "GagesJuiceBar.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_DROP = "DROP TABLE IF EXISTS user; DROP TABLE IF EXISTS game; DROP TABLE IF EXISTS score; DROP TABLE IF EXISTS market; DROP TABLE IF EXISTS advertisement; DROP TABLE IF EXISTS advertisement_market; DROP TABLE IF EXISTS listing; DROP TABLE IF EXISTS click;";

    private static final String SQL_CREATE_USER = "CREATE TABLE IF NOT EXISTS user(user_id TEXT, user_name TEXT, user_email TEXT, user_create_date TEXT, user_verified_date TEXT, user_deactivated_date TEXT);";
    private static final String SQL_CREATE_GAME = "CREATE TABLE IF NOT EXISTS game(game_id TEXT, game_name TEXT, game_start_date TEXT, game_end_date TEXT);";
    private static final String SQL_CREATE_SCORE = "CREATE TABLE IF NOT EXISTS score(score_id TEXT, game_id TEXT, user_id TEXT, score_value INTEGER);";
    private static final String SQL_CREATE_MARKET = "CREATE TABLE IF NOT EXISTS market(market_id TEXT, market_name TEXT);";
    private static final String SQL_CREATE_ADVERTISEMENT = "CREATE TABLE IF NOT EXISTS advertisement(advertisement_id TEXT, advertisement_url);";
    private static final String SQL_CREATE_ADVERTISEMENT_MARKET = "CREATE TABLE IF NOT EXISTS advertisement_market(advertisement_id TEXT, market_id TEXT);";
    private static final String SQL_CREATE_LISTING = "CREATE TABLE IF NOT EXISTS listing(listing_id TEXT, advertisement_id TEXT, listing_date TEXT);";
    private static final String SQL_CREATE_CLICK = "CREATE TABLE IF NOT EXISTS click(click_id TEXT, listing_id TEXT, click_date TEXT);";

    private static final String SQL_POPULATE_MARKET = "INSERT INTO market SELECT data.* FROM (select 'A' AS market_id, 'Auto' AS market_name UNION ALL SELECT 'H', 'Home' UNION ALL SELECT 'L', 'Life') as data LEFT JOIN market ON market.market_id = data.market_id WHERE market.market_id IS NULL;";
    private static final String SQL_POPULATE_ADVERTISEMENT = "INSERT INTO advertisement SELECT data.* FROM (select '9' AS advertisement_id, 'http://s.f/Auto' AS advertisement_url UNION ALL SELECT '8', 'http://s.f/Home' UNION ALL SELECT '7', 'http://s.f/Life') as data LEFT JOIN advertisement ON advertisement.advertisement_id = data.advertisement_id WHERE advertisement.advertisement_id IS NULL;";
    private static final String SQL_POPULATE_ADVERTISEMENT_MARKET = "INSERT INTO advertisement_market SELECT data.* FROM (select '9' AS advertisement_id, 'A' as market_id UNION ALL SELECT '8', 'H' UNION ALL SELECT '7', 'L') as data LEFT JOIN advertisement_market ON advertisement_market.advertisement_id = data.advertisement_id AND advertisement_market.market_id = data.market_id WHERE advertisement_market.market_id IS NULL;";

    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        this.dbDestroy(db);
        this.dbBuild(db);
    }

    private void dbDestroy(SQLiteDatabase db){
        db.execSQL(SQL_DROP, null);
    }
    private void dbBuild(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_GAME);
        db.execSQL(SQL_CREATE_SCORE);
        db.execSQL(SQL_CREATE_MARKET);
        db.execSQL(SQL_CREATE_ADVERTISEMENT);
        db.execSQL(SQL_CREATE_ADVERTISEMENT_MARKET);
        db.execSQL(SQL_CREATE_CLICK);
        db.execSQL(SQL_CREATE_LISTING);
        this.doPopulateData(db);
    }

    public void doPopulateData(SQLiteDatabase db) {
        db.execSQL(SQL_POPULATE_MARKET);
        db.execSQL(SQL_POPULATE_ADVERTISEMENT);
        db.execSQL(SQL_POPULATE_ADVERTISEMENT_MARKET);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}