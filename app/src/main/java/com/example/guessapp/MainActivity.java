package com.example.guessapp;

import static com.example.guessapp.R.*;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    Button buttonRestart;
    TextView textView;
    int attempts;
    int secretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        buttonRestart=findViewById(id.btnRestart);
        editText = findViewById(id.eText);
        button = findViewById(id.btn);
        textView = findViewById(id.tResult);

        startNewGame();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGuess();
            }
        });
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    private void startNewGame() {
        buttonRestart.setVisibility(View.INVISIBLE);

        attempts = 3;
        Random random = new Random();
        secretKey = random.nextInt(10) + 1;
        Log.i("Result", secretKey + "");
        textView.setText("Попробуйте угадать число от 1 до 10");
        button.setEnabled(true);
        editText.setText("");
    }

    private void handleGuess() {
        if (attempts > 0) {
            String value = editText.getText().toString();
            if (!value.isEmpty()) {
                int inValue = Integer.parseInt(value);

                if (inValue == secretKey) {
                    textView.setText("Вы выиграли! Загаданное число: " + secretKey + "\nИграть еще раз?");
                    button.setText("Играть еще раз");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startNewGame();
                        }
                    });
                } else {
                    attempts--;
                    if (attempts > 0) {
                        if (inValue > secretKey) {
                            textView.setText("Секретное число меньше! Осталось попыток: " + attempts);
                        } else {
                            textView.setText("Секретное число больше! Осталось попыток: " + attempts);
                        }
                    } else {
                        textView.setText("Вы проиграли! Загаданное число было: " + secretKey + "\nИграть еще раз?");
                        buttonRestart.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                textView.setText("Пожалуйста, введите допустимое число!");
            }
        }
    }
}
