package com.example.tic_tac_toe_2.Intro_Layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.example.tic_tac_toe_2.BXH.LoginGameActivity;
import com.example.tic_tac_toe_2.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager screenPager;
    IntroViewPagerAdaper introViewPagerAdaper;
    TabLayout tabIndicator;
    Button btnNext, btnGetStart;
    int position = 0;
    Animation btnAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_intro);

        // hide the actionBar
        //getSupportActionBar().hide();   // ẩn thanh công cụ

        // ini views
        tabIndicator = findViewById(R.id.tabIndicator);
        btnNext = findViewById(R.id.btnNext);
        btnGetStart = findViewById(R.id.btnGetStart);

        btnAni = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);    // add animation

        // fill list screen
        final List<Screen_item> mList = new ArrayList<>();
        mList.add(new Screen_item("Màn hình gồm có 9 ô , mỗi người sẽ luân phiên đánh X hoặc O vào từng ô .",
                ""
                , R.drawable.anh1));
        mList.add(new Screen_item("Người chơi X sẽ được đi nước đầu tiên ."
                , ""
                , R.drawable.anh2));
        mList.add(new Screen_item("Sau đến O"
                , "",
                R.drawable.anh3));
        mList.add(new Screen_item("Ván chơi kết thúc khi có 1 người chơi tạo được 1 hàng dọc , 1 hàng ngang hoặc 1 hàng chéo 3 ô X hoặc O ."
                , "",
                R.drawable.anh4));


        // setup viewPager
        screenPager = findViewById(R.id.screen_viewPager);
        introViewPagerAdaper = new IntroViewPagerAdaper(this, mList);
        screenPager.setAdapter(introViewPagerAdaper);   // screenPager đóng vai trò như  listview

        // setup tabLayout with viewpaper
        tabIndicator.setupWithViewPager(screenPager);       // tạo phần ... khi lướt trang vào tablayout

        // next button click listner
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();        // sử dụng getCurrentItem() để xuất ra vị trí của screenPaper hiện tại
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);

                }

                if (position == mList.size() - 1) {        // when we rech to last screen
                    // todo : show the GETSTARTED Button and hide the indicatior and the next button
                    loadLastScreen();
                }

            }
        });

        // tablayout add change listener

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //trường hợp nếu k ấn nút next mà lướt tay thì lướt hêt vẫn gọi hàm loadLastScreen

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // get start button click listener
        btnGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open main activity
                Intent intent = new Intent(getApplicationContext(), LoginGameActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }



    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStart.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        // setup animation
        btnGetStart.setAnimation(btnAni);

    }
}
