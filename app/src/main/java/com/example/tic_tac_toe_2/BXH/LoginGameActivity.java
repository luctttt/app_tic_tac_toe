package com.example.tic_tac_toe_2.BXH;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tic_tac_toe_2.Intro_Layout.MainActivity;
import com.example.tic_tac_toe_2.R;

import java.util.ArrayList;
import java.util.List;

public class LoginGameActivity extends AppCompatActivity {
   AutoCompleteTextView att_name_one_login , att_name_two_login ;


    Button btn_start ;
    DataCSDL_BXH dataCSDL_bxh;

    List<String> dsName = new ArrayList<>();

    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_game);

        att_name_one_login = findViewById(R.id.att_name_one_login);
        att_name_two_login = findViewById(R.id.att_name_two_login);

        btn_start = findViewById(R.id.btn_start_game);

        getData();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = att_name_one_login.getText().toString().trim();
                String b = att_name_two_login.getText().toString().trim();
                if(a.equals("")||b.equals("")){
                    Toast.makeText(LoginGameActivity.this, "Bạn chưa nhập tên người chơi !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (a.equals(b)){
                    Toast.makeText(LoginGameActivity.this, "Tên trùng nhau !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(LoginGameActivity.this , MainActivity.class);
                intent.putExtra("NAMEONE",a);
                intent.putExtra("NAMETWO",b);
                startActivity(intent);
            }
        });
    }

    private void getData() {

        dataCSDL_bxh = new DataCSDL_BXH(this, "BXH.sqlite", null, 1);
        Cursor cursor = dataCSDL_bxh.GetData("SELECT *FROM BXH");
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            dsName.add(name);
        }
        cursor.close();

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsName);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsName);
        att_name_one_login.setAdapter(adapter1);
        att_name_two_login.setAdapter(adapter2);
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

    }
}