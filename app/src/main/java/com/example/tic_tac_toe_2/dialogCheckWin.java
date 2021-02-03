package com.example.tic_tac_toe_2;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class dialogCheckWin extends Dialog {
    public dialogCheckWin(@NonNull Context context) {
        super(context);
    }

    public dialogCheckWin(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected dialogCheckWin(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
