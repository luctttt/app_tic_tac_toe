package com.example.tic_tac_toe_2.BXH;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tic_tac_toe_2.Intro_Layout.IntroActivity;
import com.example.tic_tac_toe_2.R;
import com.example.tic_tac_toe_2.databinding.ActivityManHinhStartBinding;

public class ManHinhStart extends AppCompatActivity {

    private ActivityManHinhStartBinding binding ;
    DataCSDL_BXH dataCSDL_bxh1;
    DataCSDL_BXH dataCSDL_bxh2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this ,R.layout.activity_man_hinh_start);

         dataCSDL_bxh1 = new DataCSDL_BXH(this , "HistoryGame.sqlite" , null , 1);
         dataCSDL_bxh1.QueryData("CREATE TABLE IF NOT EXISTS HistoryGame( NameOne VARCHAR(200) , PointOne INTEGER , NameTwo VARCHAR(200) , PointTwo INTEGER )");

        // tao csdl
        dataCSDL_bxh2 = new DataCSDL_BXH(this, "BXH.sqlite", null, 1);

        // tao bang
        dataCSDL_bxh2.QueryData("CREATE TABLE IF NOT EXISTS BXH( Name VARCHAR(200) , Point INEGER )");

        binding.btnHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhStart.this , IntroActivity.class);
                startActivity(intent);
               // dataCSDL_bxh1.QueryData("DELETE FROM HistoryGame");
            }
        });

        binding.btnStartManhinhstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhStart.this , LoginGameActivity.class);
                startActivity(intent);
            }
        });
    }
}