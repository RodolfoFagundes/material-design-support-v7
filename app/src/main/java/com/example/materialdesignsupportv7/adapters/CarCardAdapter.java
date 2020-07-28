package com.example.materialdesignsupportv7.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.materialdesignsupportv7.R;
import com.example.materialdesignsupportv7.domain.Car;
import com.example.materialdesignsupportv7.utils.ImageHelper;

import java.util.List;

public class CarCardAdapter extends RecyclerView.Adapter<CarCardAdapter.MyViewHolder> {
    private Context mContext;
    private List<Car> mList;
    private LayoutInflater mLayoutInflater;
    private int width;
    private int height;

    public CarCardAdapter(Context c, List<Car> l) {
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        float scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_car_card, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.tvModel.setText(mList.get(position).getModel());
        myViewHolder.tvBrand.setText(mList.get(position).getBrand());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myViewHolder.ivCar.setImageResource(mList.get(position).getPhoto());
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mList.get(position).getPhoto());
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
            bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 10, width, height, false, false, true, true);
            myViewHolder.ivCar.setImageBitmap(bitmap);
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
