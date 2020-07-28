package com.example.materialdesignsupportv7.interfaces;

import android.view.View;

public interface RecyclerViewOnClickListenerHack {
    void onClickListener(View view, int position);

    public void onLongPressClickListener(View view, int position);
}
