package com.example.androidquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicTacToe extends AppCompatActivity {
    @BindView(R.id.firstPlayer)
    TextView firstPlayer;
    @BindView(R.id.secondPlayer)
    TextView secondPlayer;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;

    private String firstPlayerName;
    private String secondPlayerName;
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;
    private boolean isX = true;
    private List<Integer> firstPlayerMoves = new ArrayList<>();
    private List<Integer> secondPlayerMoves = new ArrayList<>();
    private Integer[][] winningMoves = {
            {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
            {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
            {1, 5, 9}, {3, 5, 7}};

    private View.OnClickListener onBtnClickListener = view -> {
        TextView current = (TextView) view;
        current.setText(isX ? "X" : "O");
        current.setClickable(false);
        int position = Integer.parseInt(current.getTag().toString());

        if (isX) {
            firstPlayerMoves.add(position);
        } else {
            secondPlayerMoves.add(position);
        }


        if (checkWinner()) {
            reset();
        } else {
            isX = !isX;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        ButterKnife.bind(this);

        this.init();
    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        firstPlayerName = extras.getString("firstPlayerName");
        secondPlayerName = extras.getString("secondPlayerName");
        firstPlayer.setText(firstPlayerName + " : " + firstPlayerScore);
        secondPlayer.setText(secondPlayerName + " : " + secondPlayerScore);

        btn1.setOnClickListener(onBtnClickListener);
        btn2.setOnClickListener(onBtnClickListener);
        btn3.setOnClickListener(onBtnClickListener);
        btn4.setOnClickListener(onBtnClickListener);
        btn5.setOnClickListener(onBtnClickListener);
        btn6.setOnClickListener(onBtnClickListener);
        btn7.setOnClickListener(onBtnClickListener);
        btn8.setOnClickListener(onBtnClickListener);
        btn9.setOnClickListener(onBtnClickListener);
    }

    private void reset() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        btn1.setClickable(true);
        btn2.setClickable(true);
        btn3.setClickable(true);
        btn4.setClickable(true);
        btn5.setClickable(true);
        btn6.setClickable(true);
        btn7.setClickable(true);
        btn8.setClickable(true);
        btn9.setClickable(true);

        firstPlayerMoves = new ArrayList<>();
        secondPlayerMoves = new ArrayList<>();

        isX = true;
    }

    private boolean checkWinner() {
        for (Integer[] winPosition : winningMoves) {
            if (isX) {
                if (firstPlayerMoves.containsAll(Arrays.asList(winPosition))) {
                    Toast.makeText(getApplicationContext(), "Winner " + firstPlayerName, Toast.LENGTH_SHORT).show();
                    firstPlayerScore++;
                    firstPlayer.setText(firstPlayerName + " : " + firstPlayerScore);
                    return true;
                }
            } else {
                if (secondPlayerMoves.containsAll(Arrays.asList(winPosition))) {
                    Toast.makeText(getApplicationContext(), "Winner " + secondPlayerName, Toast.LENGTH_SHORT).show();
                    secondPlayerScore++;
                    secondPlayer.setText(secondPlayerName + " : " + secondPlayerScore);
                    return true;
                }
            }
        }

        if (firstPlayerMoves.size() >= 5) {
            Toast.makeText(getApplicationContext(), "Drow", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

}