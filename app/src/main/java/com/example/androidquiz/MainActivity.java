package com.example.androidquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.firstPlayer)
    TextView firstPlayerTextView;
    @BindView(R.id.secondPlayer)
    TextView secondPlayerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.playBtn)
    public void playBtn() {
        Log.i("MyActivity", "test");

        Intent i = new Intent(this, TicTacToe.class);
        Bundle bundle = new Bundle();
        bundle.putString("firstPlayerName", firstPlayerTextView.getText().toString());
        bundle.putString("secondPlayerName", secondPlayerTextView.getText().toString());
        i.putExtras(bundle);
        startActivity(i);
    }
}