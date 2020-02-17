
package com.mehul.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    TextView scoreTextView;
    TextView question;
    ArrayList <Integer> answers = new ArrayList<Integer>();
    int score = 0;
    int numberOfQuestions = 0;
    int locationOfCorrectAnswer;

    Button ansButton0;
    Button ansButton1;
    Button ansButton2;
    Button ansButton3;
    Button playAgain;

    public void answerButton(View V){
        if(V.getTag().toString().equals(locationOfCorrectAnswer)){
            Toast.makeText(MainActivity.this,"Correct!", Toast.LENGTH_LONG).show();
            score++;

        } else {
            Toast.makeText(MainActivity.this,"Wrong!", Toast.LENGTH_LONG).show();

        }

        numberOfQuestions++;

        scoreTextView.setText(score + "/" + numberOfQuestions);

        question();
    }

    public void playAgainButton (View V){
        scoreClock();
        question();
        playAgain.setVisibility(View.INVISIBLE);
    }
    public void question (){
        answers.clear();

        Random rnumber = new Random();
        int a = rnumber.nextInt(10);
        int b = rnumber.nextInt(10);
        int correctAnswer = a + b;
        int incorrectAnswer = 0;

        locationOfCorrectAnswer = rnumber.nextInt(4);

        question.setText(a + "+" + b);

        for (int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(correctAnswer);
            }else {
                incorrectAnswer = rnumber.nextInt(40);

                while(incorrectAnswer == correctAnswer){
                    incorrectAnswer = rnumber.nextInt(40);
                }

                answers.add(incorrectAnswer);
            }
        }

        ansButton0.setText(answers.get(0));
        ansButton1.setText(answers.get(1));
        ansButton2.setText(answers.get(2));
        ansButton3.setText(answers.get(3));
    }

    public void scoreClock(){
        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timer.setText("00:00s");
                Toast.makeText(MainActivity.this,"Time's Up! Your score" + score + "/" + numberOfQuestions, Toast.LENGTH_LONG).show();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.questionTextView);
        timer = (TextView) findViewById(R.id.timerTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);

        ansButton0 = (Button) findViewById(R.id.button0);
        ansButton1 = (Button) findViewById(R.id.button1);
        ansButton2 = (Button) findViewById(R.id.button2);
        ansButton3 = (Button) findViewById(R.id.button3);
        playAgain = (Button) findViewById(R.id.playAgainButton);

        scoreClock();
        question();
    }
}
