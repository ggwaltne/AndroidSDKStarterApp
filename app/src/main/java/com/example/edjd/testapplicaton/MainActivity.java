package com.example.edjd.testapplicaton;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=new TextView(this);
        text=(TextView)findViewById(R.id.text);

    }

    public void OnClick (View view) {
            text.setText("It's Alive!!!");

    }

}
