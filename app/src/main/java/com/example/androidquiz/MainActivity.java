package com.example.androidquiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView)
    TextView textView;
    private static String TAG = "MyApp";
    private static String TEXT_VAL_NAME = "TextVal";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editorSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences(TAG, Context.MODE_PRIVATE);
        editorSharedPreferences = sharedPreferences.edit();
        String text = sharedPreferences.getString(TEXT_VAL_NAME, "");
        textView.setText(text);
    }

    @Override
    protected void onPause() {
        super.onPause();
        editorSharedPreferences.putString(TEXT_VAL_NAME, textView.getText().toString());
        editorSharedPreferences.apply();
    }



    @OnClick(R.id.addBtn)
    public void onAdd() {
        String newNote = editText.getText().toString();
        if (!newNote.isEmpty()) {
            String text = newNote + "\n" + textView.getText().toString();
            textView.setText(text);
            editText.setText("");


        }
    }
}