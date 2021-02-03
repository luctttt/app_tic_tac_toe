package com.example.tic_tac_toe_2.BXH;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tic_tac_toe_2.R;

import java.util.ArrayList;
import java.util.List;

class BXH_Adapter extends RecyclerView.Adapter<BXH_Adapter.ViewHolder> {

    // hello , tôi là trần đức lực !!!

    List<Model_BXH> dsModel = new ArrayList<>();

    public BXH_Adapter(List<Model_BXH> dsModel) {
        this.dsModel = dsModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bxh, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Model_BXH model_bxh = dsModel.get(position);

        holder.img.setText(model_bxh.getImg()+"");
        holder.txt_name_win.setText(model_bxh.getName());
        holder.txt_point_win.setText(model_bxh.getPoint()+"");

    }

    @Override
    public int getItemCount() {
        return dsModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView img;
        TextView txt_name_win, txt_point_win;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_win = itemView.findViewById(R.id.txt_name_bxh);
            txt_point_win = itemView.findViewById(R.id.txt_point_win_bxh);
            img = itemView.findViewById(R.id.img_bxh);
        }
    }
}
