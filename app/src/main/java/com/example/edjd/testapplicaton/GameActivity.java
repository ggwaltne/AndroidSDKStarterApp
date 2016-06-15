package com.example.edjd.testapplicaton;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class GameActivity extends AppCompatActivity {
    Intent GameOver;
    int differencesFound = 0;
    int maxDifferences = 3;
    Calendar calendar;
    int seconds;
    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameOver = new Intent(getApplicationContext(), GameOverActivity.class);
        differencesFound = 0;
        score = (TextView)findViewById(R.id.score);
        calendar = Calendar.getInstance();
        seconds = calendar.get(Calendar.SECOND);
    }


    public void onClickAddFound (View view) {
        differencesFound++;

        score.setText(Integer.toString(differencesFound));
        if(differencesFound >= maxDifferences)
        {
            goToGameOver();
            differencesFound = 0;
            seconds = calendar.getInstance().get(Calendar.SECOND) - seconds;
            GameOverActivity.Score = 5000 - seconds * 100;
            setScore();
            score.setText(Integer.toString(differencesFound));
        }
    }

    public void setScore () {
       // addScore(db, score_value, user_id);
    };
    public void goToGameOver () {
        startActivity(GameOver);
    }
}
