package com.example.tic_tac_toe_2.BXH;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tic_tac_toe_2.R;

public class MycustomDialog extends Dialog {

    Button btn_tieptuc, btn_thoat;
    TextView txt_name_win;
    Activity context;

    Boolean checkXY;
    boolean checkHoa = false;
    interrr inter;
    String nameOne, nameTwo;

    public MycustomDialog(@NonNull Context context, boolean checkHoa, interrr inter) {
        super(context);
        this.checkHoa = checkHoa;
        this.inter = inter;
        setContentView(R.layout.itemfordialog);
        addControls();
        addEvents();
    }

    public MycustomDialog(@NonNull Activity context, interrr inter, boolean checkXY, String a, String b) {
        super(context);
        this.context = context;
        this.inter = inter;
        this.checkXY = checkXY;
        this.nameOne = a;
        this.nameTwo = b;


        setContentView(R.layout.itemfordialog);
        addControls();
        addEvents();


    }

    private void addEvents() {

        btn_tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inter.playGame();
                dismiss();
            }
        });
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void addControls() {
        btn_tieptuc = findViewById(R.id.btn_Tiep_tuc);
        btn_thoat = findViewById(R.id.btn_thoat);

        txt_name_win = findViewById(R.id.txt_name_win);
        // do cac imgview nam trong chinh dialog(ke thua) nen k can sd dialog.findViewById

        if (!checkHoa) {
            if (checkXY) {
                txt_name_win.setText(nameOne + " win !!");
            } else {
                txt_name_win.setText(nameTwo + " win !!");
            }
        } else {
            txt_name_win.setText("Hòa !!!");
        }


        setTitle(" Xac nhan ");
        setCanceledOnTouchOutside(false);
    }

}
