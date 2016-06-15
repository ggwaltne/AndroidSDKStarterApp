package com.example.edjd.testapplicaton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.Map;
import java.util.TreeMap;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LeaderBoard extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
//            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leader_board);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);

        loadGrid();

//        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        findViewById(R.id.btnBack).setOnTouchListener(mDelayHideTouchListener);
    }

    private void loadGrid() {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grdScores);

        String[] players = {"Jeff","Gage","Tessa","Robert","John","Kevin","Nick","Angie","Wilma"};
        String[] scores = {"1344","4334","3223","4645","0","5844","234","33","44"};


        DataBaseWrapper mDbHelper = new DataBaseWrapper(getBaseContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        DataAccess da = new DataAccess();
        Map<Long, String[]> scoreBoard = da.readScoreboard(db);

        gridLayout.removeAllViews();

        int rowCount = players.length;
        rowCount = scoreBoard.size();

        int column = 3;
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(rowCount + 1);
        int rankNum = 0;
        int row = 0;

        String ourHiScore = "0";
        String ourRank = "0";

        //sort the scoreboard by running it through
        Map<Long, String[]> treeMap = new TreeMap<Long, String[]>(scoreBoard);

        for (Map.Entry<Long, String[]> entry : treeMap.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
//        }
//        for (int i = 0; i < rowCount; i++) {
            rankNum++;
            String rank = String.valueOf(rankNum);
            String userName = entry.getValue()[0];
            boolean isThisUs = false;
            if (MainActivity.USER_NAME.equals(userName)) {
                ourHiScore = String.valueOf(entry.getValue()[1]);
                ourRank = rank;
                isThisUs=true;
            }

            TextView txtName = new TextView(this);
            txtName.setText(entry.getKey() + ") " + userName);
//            txtName.setText(rank + ") " + scoreBoard.key);

            txtName.setTextColor(Color.WHITE);
            if (isThisUs) {
                txtName.setTextColor(Color.RED);
                txtName.setBackgroundColor(Color.WHITE);
            }

            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams();
            gridParam.rowSpec = GridLayout.spec(row);
            gridParam.columnSpec = GridLayout.spec(0);
            gridParam.setGravity(Gravity.LEFT);
            txtName.setLayoutParams(gridParam);
            txtName.setTextSize(30);
            gridLayout.addView(txtName);

            Space spacer = new Space(this);
            gridParam = new GridLayout.LayoutParams();
            gridParam.rowSpec = GridLayout.spec(row);
            gridParam.columnSpec = GridLayout.spec(1);
            spacer.setLayoutParams(gridParam);
            gridLayout.addView(spacer);

            TextView txtScore = new TextView(this);
            txtScore.setText(String.valueOf(entry.getValue()[1]));
            txtScore.setTextColor(Color.WHITE);
            if (isThisUs) {
                txtScore.setTextColor(Color.RED);
                txtScore.setBackgroundColor(Color.WHITE);
            }

            gridParam = new GridLayout.LayoutParams();
            gridParam.rowSpec = GridLayout.spec(row);
            gridParam.columnSpec = GridLayout.spec(2);
            gridParam.setGravity(Gravity.RIGHT);
            txtScore.setLayoutParams(gridParam);
            txtScore.setTextSize(30);
            gridLayout.addView(txtScore);

            row++;
        }

        TextView hiScore = (TextView) findViewById(R.id.lblHiScore);
        hiScore.setText("Your High: " + ourHiScore);
        TextView oRank = (TextView) findViewById(R.id.lblRank);
        oRank.setText("Your Rank: " + ourRank);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void OnClickBack(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
