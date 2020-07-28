package com.example.materialdesignsupportv7.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.materialdesignsupportv7.R;
import com.example.materialdesignsupportv7.domain.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {
    private List<Car> mList;
    private LayoutInflater mLayoutInflater;

    public CarAdapter(Context c, List<Car> l) {
        //mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_car, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.ivCar.setImageResource(mList.get(position).getPhoto());
        myViewHolder.tvModel.setText(mList.get(position).getModel());
        myViewHolder.tvBrand.setText(mList.get(position).getBrand());

        try {
            YoYo.with(Techniques.Tada)
                    .duration(300)
                    .repeat(2)
                    .playOn(myViewHolder.itemView);
        } catch (Exception e) {
            //Não faz nada
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addListItem(Car c, int position) {
        mList.add(position, c);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivCar;
        private TextView tvModel;
        private TextView tvBrand;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCar = itemView.findViewById(R.id.iv_car);
            tvModel = itemView.findViewById(R.id.tv_model);
            tvBrand = itemView.findViewById(R.id.tv_brand);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
