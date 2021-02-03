package com.example.tic_tac_toe_2.BXH;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tic_tac_toe_2.R;

public class StartGame extends AppCompatActivity implements View.OnClickListener, interrr {

    DataCSDL_BXH dataCSDL_bxh;

    private final Button[] buttons = new Button[10];
    private TextView txt_name_one, txt_name_two, txt_point_one, txt_point_two;

    private Button btn_back, btn_save;

    private int pointOne, pointTwo;

    int rountCount = 0;

    private boolean checkXY;

    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPosition = {
            {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
            {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
            {1, 5, 9}, {3, 5, 7}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        txt_name_one = findViewById(R.id.txt_nameOne_startGame);
        txt_name_two = findViewById(R.id.txt_nameTwo_startGame);
        txt_point_one = findViewById(R.id.txt_point_one);
        txt_point_two = findViewById(R.id.txt_point_two);


        Intent intent = getIntent();        // name player
        String a = intent.getStringExtra("NAMEONE");
        String b = intent.getStringExtra("NAMETWO");

        //Toast.makeText(this, "name : " + a + " - " + b, Toast.LENGTH_SHORT).show();

        txt_name_one.setText(a);
        txt_name_two.setText(b);


        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                saveHistory();
            }
        });

        for (int i = 1; i <= 9; i++) {
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourceID);
            buttons[i].setOnClickListener(this);

        }

        pointOne = 0;
        pointTwo = 0;
        checkXY = true;

    }

    private void saveHistory() {        // tạo sql history
        DataCSDL_BXH dataCSDL_bxh1 = new DataCSDL_BXH(this , "HistoryGame.sqlite" , null , 1);
        //dataCSDL_bxh1.QueryData("CREATE TABLE IF NOT EXISTS HistoryGame( NameOne VARCHAR(200) , PointOne INTEGER , NameTwo VARCHAR(200) , PointTwo INTEGER )");

        dataCSDL_bxh1.QueryData("INSERT INTO HistoryGame VALUES('" +
                txt_name_one.getText().toString() +  "'  , '" + pointOne + "'  ,  '" +
                txt_name_two.getText().toString() +  "'  ,  '" +  pointTwo +"')");

    }

    private void getData() {        // save player + point
        dataCSDL_bxh = new DataCSDL_BXH(this, "BXH.sqlite", null, 1);

        Cursor cursor1 = dataCSDL_bxh.GetData("SELECT Name,Point FROM BXH WHERE Name ='" + txt_name_one.getText().toString() + "' ");

        if (cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {
                String name1 = cursor1.getString(0);
                int point1 = cursor1.getInt(1);

                if (!name1.equals("")) {
                    dataCSDL_bxh.QueryData("UPDATE BXH SET Point = '" + (point1 + pointOne) + "'WHERE Name ='" + txt_name_one.getText().toString() + "'");
                } else {
                    dataCSDL_bxh.QueryData("INSERT INTO BXH VALUES('" + txt_name_one.getText().toString() + "', '" + pointOne + "')");
                }
            }
        } else {
            dataCSDL_bxh.QueryData("INSERT INTO BXH VALUES('" + txt_name_one.getText().toString() + "', '" + pointOne + "')");
        }

        Cursor cursor2 = dataCSDL_bxh.GetData("SELECT Name,Point FROM BXH WHERE Name ='" + txt_name_two.getText().toString() + "' ");

        if (cursor2.getCount() > 0) {
            while (cursor2.moveToNext()) {
                String name2 = "";
                name2 = cursor2.getString(0);
                int point2 = cursor2.getInt(1);

                if (!name2.equals("")) {
                    dataCSDL_bxh.QueryData("UPDATE BXH SET Point = '" + (point2 + pointTwo) + "'WHERE Name = '" + txt_name_two.getText().toString() + "'");
                } else {
                    dataCSDL_bxh.QueryData("INSERT INTO BXH VALUES('" + txt_name_two.getText().toString() + "', '" + pointTwo + "')");
                }
            }
        } else {
            dataCSDL_bxh.QueryData("INSERT INTO BXH VALUES('" + txt_name_two.getText().toString() + "', '" + pointTwo + "')");
        }

        cursor1.close();
        cursor2.close();
        Toast.makeText(this, "Đã lưu !! ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        String buttonId = v.getResources().getResourceEntryName(v.getId());  // btn_2
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length() - 1, buttonId.length()));   //2

        if (checkXY) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));

            gamestate[gameStatePointer] = 0;
        } else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.RED);
            gamestate[gameStatePointer] = 1;
        }

        rountCount++;

        boolean checkHoa = false;
        if (checkWinner()) {
            checkHoa = true;
            MycustomDialog dialog = new MycustomDialog(StartGame.this, this, checkXY, txt_name_one.getText().toString(), txt_name_two.getText().toString());
            if (checkXY) {      // true : x , false : y
                pointOne++;
                updatePoint();
                dialog.show();

            } else {
                pointTwo++;
                updatePoint();
                dialog.show();

            }
        } else if (rountCount == 9) {
            checkHoa = true;
            MycustomDialog dialog = new MycustomDialog(StartGame.this, checkHoa, this);
            dialog.show();
        } else {
            checkXY = !checkXY;
        }
    }

    private boolean checkWinner() {
        boolean win = false;
        for (int[] winningPosition : winningPosition) {

            if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] &&
                    gamestate[winningPosition[1]] == gamestate[winningPosition[2]] &&
                    gamestate[winningPosition[0]] != 2) {
                buttons[winningPosition[0]].setBackgroundColor(Color.RED);
                buttons[winningPosition[1]].setBackgroundColor(Color.RED);
                buttons[winningPosition[2]].setBackgroundColor(Color.RED);
                win = true;
                break;
            }

        }
        return win;
    }


    private void updatePoint() {
        txt_point_one.setText(Integer.toString(pointOne));
        txt_point_two.setText(Integer.toString(pointTwo));
    }

    private void playAgain() {
        checkXY = true;

        rountCount = 0;

        for (int i = 1; i < buttons.length; i++) {
            gamestate[i] = 2;
            buttons[i].setText("");
            buttons[i].setBackgroundResource(R.color.purple_500);
        }

    }

    @Override
    public void playGame() {
        playAgain();
    }
}