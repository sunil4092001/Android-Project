package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button go;
    ArrayList<Integer> answer =new ArrayList<Integer>();
    int locaationofcorect;
    TextView opperation;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;

    TextView result;
    int score = 0;
    int noofquestion=0;
    TextView scoretv;
    TextView time;
    RelativeLayout game;


    public void start(View view) {
        go.setVisibility(View.INVISIBLE);
        game.setVisibility(View.VISIBLE);
    }

    public void generateQuestion(){
        Random rand =new Random();
        int a = rand.nextInt(100);
        int b = rand.nextInt(100);

        opperation.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locaationofcorect =rand.nextInt(4);
        answer.clear();

        int incoreect;
        for(int i=0; i<4; i++){
            if( i == locaationofcorect){
                answer.add(a+b);
            }
            else{
                incoreect =rand.nextInt(100);
                while ( incoreect == a+b ){
                    incoreect = rand.nextInt(100);
                }
                answer.add(incoreect);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));



    }

    public void choseanswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locaationofcorect))){

            result.setText("*Correct*");
            score++;
        }else {
            result.setText("*Wrong*");
        }
        noofquestion++;
        scoretv.setText(Integer.toString(score)+"/"+Integer.toString(noofquestion));
        generateQuestion();

    }

    public void playAgain(View view){

        score = 0;
        noofquestion = 0;

        time.setText("30s");
        scoretv.setText("0/0");
        result.setText("");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        playAgain.setVisibility(View.INVISIBLE);

        new CountDownTimer(1*30000,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                time.setText("0s");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                result.setText("Your Score is :"+Integer.toString(score)+"/"+Integer.toString(noofquestion));
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go =(Button)findViewById(R.id.go);
        opperation = (TextView)findViewById(R.id.optv);
        button0 =(Button)findViewById(R.id.one);
        button1 =(Button)findViewById(R.id.two);
        button2 =(Button)findViewById(R.id.three);
        button3 =(Button)findViewById(R.id.four);
        playAgain =(Button)findViewById(R.id.play);

        scoretv =(TextView)findViewById(R.id.scoretv);
        time =(TextView)findViewById(R.id.timetv);
        result =(TextView)findViewById(R.id.result);
        game =(RelativeLayout)findViewById(R.id.gamerl);


       generateQuestion();

       playAgain(findViewById(R.id.play));


    }

   }
