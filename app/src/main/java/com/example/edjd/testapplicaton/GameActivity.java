package com.example.edjd.testapplicaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    Intent LeaderBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        LeaderBoard = new Intent(getApplicationContext(), LeaderBoard.class);
    }

    public void OnClickGameOver (View view) {
        startActivity(LeaderBoard);
    }
}
