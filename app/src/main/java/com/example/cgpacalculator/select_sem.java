package com.example.cgpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class select_sem extends AppCompatActivity {
    Button chem,phy,third,fourth,fifth,sixth,seventh,eighth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sem);
        phy = findViewById(R.id.phy_cycle);
        chem = findViewById(R.id.chem_cycle);
        third = findViewById((R.id.third_sem));
        fourth = findViewById(R.id.fourth_sem);
        fifth = findViewById(R.id.fifth_sem);
        sixth = findViewById(R.id.sixth_sem);
        seventh = findViewById(R.id.seventh_sem);
        eighth = findViewById(R.id.eigth_sem);

        phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_sem.this,EnterPhyCycleMarks.class));
            }
        });

        chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_sem.this,enter_chem_cycle_marks.class));
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_sem.this,EnterThirdSemMarks.class));
            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_sem.this,EnterFourthSemMarks.class));
            }
        });

        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_sem.this,EnterFifthSemMarks.class));
            }
        });

        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_sem.this,EnterSixthSemMarks.class));
            }
        });

        seventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_sem.this,EnterSeventhSemMarks.class));
            }
        });

        eighth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_sem.this,EnterEigthSemMarks.class));
            }
        });

    }
}