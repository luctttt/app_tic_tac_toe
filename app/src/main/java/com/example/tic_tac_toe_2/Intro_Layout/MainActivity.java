package com.example.tic_tac_toe_2.Intro_Layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tic_tac_toe_2.BXH.BXHActivity;
import com.example.tic_tac_toe_2.BXH.EditNameActivity;
import com.example.tic_tac_toe_2.BXH.HistoryActivity;
import com.example.tic_tac_toe_2.BXH.StartGame;
import com.example.tic_tac_toe_2.R;
import com.example.tic_tac_toe_2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Animation animation;

    String nameOne, nameTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        

        binding.btnStart.setAnimation(animation);       // animation
        binding.btnBxh.setAnimation(animation);
        binding.btnSetting.setAnimation(animation);
        binding.btnHistory.setAnimation(animation);

        Intent intent = getIntent();
        nameOne = intent.getStringExtra("NAMEONE");
        nameTwo = intent.getStringExtra("NAMETWO");

        //Toast.makeText(this, "Name : " + nameOne + " - " + nameTwo, Toast.LENGTH_SHORT).show();

        addEvent();
    }

    private void addEvent() {
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartGame.class);
                intent.putExtra("NAMEONE", nameOne);
                intent.putExtra("NAMETWO", nameTwo);
                startActivity(intent);
            }
        });


        binding.btnBxh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BXHActivity.class);
                startActivity(intent);
            }
        });

        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//bug
                Intent intent = new Intent(MainActivity.this, EditNameActivity.class);
                startActivity(intent);
            }
        });
        binding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , HistoryActivity.class);
                intent.putExtra("HISTORY","");
                startActivity(intent);
            }
        });
    }

}