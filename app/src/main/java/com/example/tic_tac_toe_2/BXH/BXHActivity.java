package com.example.tic_tac_toe_2.BXH;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tic_tac_toe_2.R;
import com.example.tic_tac_toe_2.databinding.ActivityBXHBinding;

import java.util.ArrayList;
import java.util.List;

public class BXHActivity extends AppCompatActivity {

    List<Model_BXH> dsModel_BXH = new ArrayList<>();
    BXH_Adapter adapter;

    private ActivityBXHBinding binding;

    DataCSDL_BXH dataCSDL_bxh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_b_x_h);

        adapter = new BXH_Adapter(dsModel_BXH);     // adapter
        getData();      // creat dữ liệu tử sqlite
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));       // setLayout
        binding.recyclerView.setAdapter(adapter);

        getSwap();  // sắp xếp

//        binding.btnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dsModel_BXH.clear();
//                dataCSDL_bxh.QueryData("DELETE FROM BXH");
//                adapter.notifyDataSetChanged();
//            }
//        });


    }

    private void getSwap() {
        dsModel_BXH.clear();
        Cursor cursor = dataCSDL_bxh.GetData("SELECT *FROM BXH ORDER BY Point DESC");
        int index = 1;
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            int point = cursor.getInt(1);
            dsModel_BXH.add(new Model_BXH(index, name, point));
            index++;
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private void getData() {
        dsModel_BXH.clear();
        dataCSDL_bxh = new DataCSDL_BXH(this, "BXH.sqlite", null, 1);
        Cursor cursor = dataCSDL_bxh.GetData("SELECT *FROM BXH");
        if(cursor.getCount()>0){
            int index = 1;
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                int point = cursor.getInt(1);
                dsModel_BXH.add(new Model_BXH(index, name, point));
                index++;
            }
            cursor.close();
            adapter.notifyDataSetChanged();

        }


    }


}
