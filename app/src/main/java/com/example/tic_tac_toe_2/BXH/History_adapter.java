package com.example.tic_tac_toe_2.BXH;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tic_tac_toe_2.R;

import java.util.ArrayList;
import java.util.List;

class History_adapter extends BaseAdapter {
    List<Model_History>dsHistory = new ArrayList<>();
    Activity context ;

    public History_adapter(List<Model_History> dsHistory, Activity context) {
        this.dsHistory = dsHistory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dsHistory.size();
    }

    @Override
    public Object getItem(int position) {
        return dsHistory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;

        if (convertView==null){
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_history , parent , false);
            viewHolder.txt_history = convertView.findViewById(R.id.txt_history);
            viewHolder.txt_history_1 = convertView.findViewById(R.id.txt_history1);

            viewHolder.txt_point_one = convertView.findViewById(R.id.point_one);
            viewHolder.txt_point_two = convertView.findViewById(R.id.point_two);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Model_History model_bxh = dsHistory.get(position);

        viewHolder.txt_point_one.setText(model_bxh.getPointOne()+"");
        viewHolder.txt_point_two.setText(model_bxh.getPointtwo()+"");
        viewHolder.txt_history.setText(model_bxh.getNameOne());
        viewHolder.txt_history_1.setText(model_bxh.getNameTwo());

        return convertView;
    }

    class ViewHolder{
        TextView txt_history , txt_history_1;
        TextView txt_point_one , txt_point_two ;
    }
}
