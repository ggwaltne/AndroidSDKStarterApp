package com.example.edjd.testapplicaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    Intent GameOver;
    int differencesFound = 0;
    int maxDifferences = 3;
    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameOver = new Intent(getApplicationContext(), GameOverActivity.class);
        differencesFound = 0;
        score = (TextView)findViewById(R.id.score);
    }


    public void onClickAddFound (View view) {
        differencesFound++;

        score.setText(Integer.toString(differencesFound));
        if(differencesFound >= maxDifferences)
        {
            goToGameOver();
            differencesFound = 0;
            score.setText(Integer.toString(differencesFound));
        }
    }

    public void goToGameOver () {
        startActivity(GameOver);
    }
}
