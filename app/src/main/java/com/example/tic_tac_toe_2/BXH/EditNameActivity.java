package com.example.tic_tac_toe_2.BXH;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tic_tac_toe_2.R;
import com.example.tic_tac_toe_2.databinding.ActivityEditNameBinding;

import java.util.ArrayList;
import java.util.List;

public class EditNameActivity extends AppCompatActivity {

    private ActivityEditNameBinding binding;

    DataCSDL_BXH dataCSDL_bxh;

    List<String> dsName = new ArrayList<>();


    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_name);

        dataCSDL_bxh = new DataCSDL_BXH(this, "BXH.sqlite", null, 1);

        getData();

        binding.btnXacNhanSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yourName = binding.autoYourNameSetting.getText().toString().trim();
                String newName = binding.edtNewNameSetting.getText().toString().trim();

                if (yourName.equals("")) {       // editname
                    Toast.makeText(EditNameActivity.this, "Bạn chưa nhập tên ...", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = false;
                    for (int i = 0; i < dsName.size(); i++) {
                        if (yourName.equals(dsName.get(i))) {
                            check = true;
                        }
                    }

                    if (check) {

                        if (newName.equals("")) {
                            Toast.makeText(EditNameActivity.this, "Bạn chưa nhập tên mới", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean checkNewName = true ;
                            for (int i = 0; i < dsName.size(); i++) {
                                if (newName.equals(dsName.get(i))){
                                    checkNewName = false;
                                }
                            }

                            if (checkNewName){
                                dataCSDL_bxh.QueryData("UPDATE BXH SET Name = '" + newName + "'WHERE Name ='" + yourName + "'");
                                Toast.makeText(EditNameActivity.this, "Thay đổi thành công !!!", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(EditNameActivity.this, "Tên mới bị trùng", Toast.LENGTH_SHORT).show();
                                binding.edtNewNameSetting.setText("");
                                binding.edtNewNameSetting.requestFocus();
                            }

                        }

                    } else {
                        binding.autoYourNameSetting.setText("");
                        binding.autoYourNameSetting.requestFocus();
                        Toast.makeText(EditNameActivity.this, "Tên bạn chọn không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private void getData() {

        Cursor cursor = dataCSDL_bxh.GetData("SELECT *FROM BXH");
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            dsName.add(name);
        }
        cursor.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsName);
        binding.autoYourNameSetting.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}