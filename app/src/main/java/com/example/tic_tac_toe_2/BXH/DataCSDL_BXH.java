package com.example.tic_tac_toe_2.BXH;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DataCSDL_BXH  extends SQLiteOpenHelper {
    public DataCSDL_BXH(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void QueryData(String sql){      // ham truy van du lieu
        SQLiteDatabase database =getWritableDatabase();     // ghi
        database.execSQL(sql);
    }

    public Cursor GetData(String sql){      // tra ve 1 chuoi con tro tro ve tung o trong csdl
        SQLiteDatabase database =getReadableDatabase();     // doc , k tuong tac
        return database.rawQuery(sql , null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
