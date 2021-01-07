package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView output;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,
         point,plus,minus,divide,multiply,clear;
    Float num1;
    Boolean bplus=false,bminus=false,bmultiply,bdivide=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output.findViewById(R.id.output);
        b1.findViewById(R.id.b1);
        b2.findViewById(R.id.b2);
        b3.findViewById(R.id.b3);
        b4.findViewById(R.id.b4);
        b5.findViewById(R.id.b5);
        b6.findViewById(R.id.b6);
        b7.findViewById(R.id.b7);
        b8.findViewById(R.id.b8);
        b9.findViewById(R.id.b9);
        b0.findViewById(R.id.b0);
        point.findViewById(R.id.point);
        plus.findViewById(R.id.plus);
        minus.findViewById(R.id.minus);
        multiply.findViewById(R.id.multiply);
        divide.findViewById(R.id.divide);
        clear.findViewById(R.id.clear);


    }
    b1.s

}
