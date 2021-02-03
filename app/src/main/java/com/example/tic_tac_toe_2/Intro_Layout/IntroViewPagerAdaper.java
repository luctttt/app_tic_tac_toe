package com.example.tic_tac_toe_2.Intro_Layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tic_tac_toe_2.R;

import java.util.List;

public class IntroViewPagerAdaper extends PagerAdapter {
    Context mContext;
    List<Screen_item> mListScreen;

    public IntroViewPagerAdaper(Context mContext, List<Screen_item> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {     // nơi tạo sự kiện các nút và gọi layoutInflater (vẽ giao diện)

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  // gọi hàm vẽ giao diện
        View view = inflater.inflate(R.layout.screen_layout, null);

        ImageView imgSlide = view.findViewById(R.id.intro_img);     // ánh xạ các nút trong giao diện
        TextView txtTitle = view.findViewById(R.id.intro_title);
        TextView txtDescription = view.findViewById(R.id.intro_description);

        txtTitle.setText(mListScreen.get(position).getTitle());         // set giá trị cho list screen item
        txtDescription.setText(mListScreen.get(position).getDescription());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());

        container.addView(view);        // addView

        return view;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
