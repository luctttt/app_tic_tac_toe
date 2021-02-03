package com.example.tic_tac_toe_2.BXH;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tic_tac_toe_2.R;
import com.example.tic_tac_toe_2.databinding.ActivityHistoryBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    History_adapter adapter;

    List<Model_History> dsHistory = new ArrayList<>();

    private ActivityHistoryBinding binding;

    DataCSDL_BXH dataCSDL_bxh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history);

        dataCSDL_bxh = new DataCSDL_BXH(this, "HistoryGame.sqlite", null, 1);

        getData();
    }

    private void getData() {
        dsHistory.clear();
        Cursor cursor = dataCSDL_bxh.GetData("SELECT *FROM HistoryGame");
        if (cursor.getCount()<=0){
            return;
        }
        while (cursor.moveToNext()) {
            String nameOne = cursor.getString(0);
            String nameTwo = cursor.getString(2);
            int pointOne = cursor.getInt(1);
            int pointTwo = cursor.getInt(3);

            dsHistory.add(new Model_History(nameOne , nameTwo , pointOne , pointTwo));

        }
        cursor.close();

        adapter = new History_adapter(dsHistory, this);
        binding.listview.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        // ? -> neeus null thif k thuc hien dang sau nua
        // Application la ham chay dau tien va luon ton tai duy nhat , application chet -> app destroy
        // relea

        // function trong kotlin là interface có duy nhất 1 hàm
        // Unit ; void
    }
}