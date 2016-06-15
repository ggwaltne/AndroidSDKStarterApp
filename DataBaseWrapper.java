/**
 * Created by robertmatthewcook on 6/15/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//DataBaseWrapper mDbHelper = new DataBaseWrapper(getContext());
//SQLiteDatabase db = mDbHelper.getWritableDatabase();
//SQLiteDatabase db = mDbHelper.getReadableDatabase();

public class DataBaseWrapper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Android.db";

    private static final String SQL_CREATE_USER = "CREATE TABLE IF NOT EXISTS user(user_id TEXT, user_name TEXT, user_email TEXT, user_create_date TEXT, user_verified_date TEXT, user_deactivated_date TEXT);";
    private static final String SQL_CREATE_GAME = "CREATE TABLE IF NOT EXISTS game(game_id TEXT, game_name TEXT, game_start_date TEXT, game_end_date TEXT);";
    private static final String SQL_CREATE_SCORE = "CREATE TABLE IF NOT EXISTS score(score_id TEXT, game_id TEXT, user_id TEXT, score_value INTEGER, score_date TEXT);";
    private static final String SQL_CREATE_MARKET = "CREATE TABLE IF NOT EXISTS market(market_id TEXT, market_name TEXT);";
    private static final String SQL_CREATE_ADVERTISEMENT = "CREATE TABLE IF NOT EXISTS advertisement(advertisement_id TEXT, advertisement_url);";
    private static final String SQL_CREATE_ADVERTISEMENT_MARKET = "CREATE TABLE IF NOT EXISTS advertisement_market(advertisement_id TEXT, market_id TEXT);";
    private static final String SQL_CREATE_LISTING = "CREATE TABLE IF NOT EXISTS listing(listing_id TEXT, advertisement_id TEXT, listing_date TEXT);";
    private static final String SQL_CREATE_CLICK = "CREATE TABLE IF NOT EXISTS click(click_id TEXT, listing_id TEXT, click_date TEXT);";

    private static final String SQL_POPULATE_MARKET = "INSERT INTO market select * from (SELECT 'A' AS market_id, 'Auto' AS market_name UNION ALL SELECT 'H', 'Home' UNION ALL SELECT 'L', 'Life') as data LEFT JOIN market ON market.market_id = data.market_id WHERE market.market_id IS NULL);";
    private static final String SQL_POPULATE_ADVERTISEMENT = "INSERT INTO advertisement select * from (SELECT '9' AS advertisement_id, 'http://s.f/Auto' AS advertisement_url UNION ALL SELECT '8', 'http://s.f/Home' UNION ALL SELECT '7', 'http://s.f/Life') as data LEFT JOIN advertisement ON advertisement.advertisement_id = data.advertisement_id WHERE advertisement.advertisement_id IS NULL);";
    private static final String SQL_POPULATE_ADVERTISEMENT_MARKET = "INSERT INTO advertisement_market select * from (SELECT '9' AS advertisement_id, 'A' as market_id UNION ALL SELECT '8', 'H' UNION ALL SELECT '7', 'L') as data LEFT JOIN advertisement_market ON advertisement_market.advertisement_id = data.advertisement_id AND advertisement_market.market_id = data.market_id WHERE advertisement_market.market_id IS NULL);";

    private static final String SQL_GENERATE_GAME = "INSERT INTO game select * from (SELECT '101' AS game_id, 'ExGame', '2016-06-14', '2016-06-14' UNION ALL SELECT '102', 'WhyGame', '2016-06-15', NULL) as data LEFT JOIN game ON game.game_id = data.game_id WHERE game.game_id IS NULL);";
    private static final String SQL_GENERATE_USER = "INSERT INTO user select * from (SELECT '1' AS user_id, 'UserOne' AS user_name, 'one@fake.it' AS user_email, '2016-01-01' AS user_create_date, '2016-01-01' AS user_verified_date, NULL AS user_deactivated_date UNION ALL SELECT '2', 'UserTwo', 'two@fake.it', '2016-01-02', '2016-01-02', NULL UNION ALL SELECT '3', 'UserThree', 'three@fake.it', '2016-01-03', '2016-01-03', NULL) as data LEFT JOIN user ON user.user_id = data.user_id WHERE user.user_id IS NULL);";
    private static final String SQL_GENERATE_SCORE = "INSERT INTO score select * from (SELECT '1001' AS score_id, 101 AS game_id, 3 AS user_id, 54321 AS score_value, '2016-06-14' AS score_date NULL UNION ALL SELECT 1002, 101, 2, 2468, '2016-06-14' UNION ALL SELECT 1003, 101, 1, 11111, '2016-06-14') as data LEFT JOIN score ON score.score_id = data.score_id WHERE score.score_id IS NULL);";

    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_GAME);
        db.execSQL(SQL_CREATE_SCORE);
        db.execSQL(SQL_CREATE_MARKET);
        db.execSQL(SQL_CREATE_ADVERTISEMENT);
        db.execSQL(SQL_CREATE_ADVERTISEMENT_MARKET);
        db.execSQL(SQL_CREATE_LISTING);
        db.execSQL(SQL_CREATE_CLICK);
        this.doPopulateData(db);
        this.doGenerateData(db);
    }
    public void doPopulateData(SQLiteDatabase db) {
        db.execSQL(SQL_POPULATE_MARKET);
        db.execSQL(SQL_POPULATE_ADVERTISEMENT);
        db.execSQL(SQL_POPULATE_ADVERTISEMENT_MARKET);
    }
    public void doGenerateData(SQLiteDatabase db) {
        db.execSQL(SQL_GENERATE_GAME);
        db.execSQL(SQL_GENERATE_USER);
        db.execSQL(SQL_GENERATE_SCORE);
    }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}