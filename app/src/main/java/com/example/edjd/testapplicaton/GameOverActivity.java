package com.example.edjd.testapplicaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    static public int Score;
    Intent Video;

    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        score = (TextView)findViewById(R.id.score);
        score.setText(Integer.toString(Score));

        Video = new Intent(getApplicationContext(), VideoAdActivity.class);

    }

    public void OnClickSFAd(View view) {
        startActivity(Video);
    }
    public void OnClickHockey(View view) {
        startActivity(Video);
    }
    public void OnClickAutoCare(View view) {
        startActivity(Video);
    }
}
