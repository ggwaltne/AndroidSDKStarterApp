package com.example.edjd.testapplicaton;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent LeaderBoard;
    Intent Game;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LeaderBoard = new Intent(getApplicationContext(), LeaderBoard.class);
        Game = new Intent(getApplicationContext(), GameActivity.class);

    }
    public void OnClickLeaderBoard (View view) {
        startActivity(LeaderBoard);
    }

    public void OnClickGame(View view) {
        startActivity(Game);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
