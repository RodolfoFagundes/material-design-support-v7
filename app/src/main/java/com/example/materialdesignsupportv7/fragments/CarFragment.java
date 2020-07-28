package com.example.materialdesignsupportv7.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.materialdesignsupportv7.MainActivity;
import com.example.materialdesignsupportv7.R;
import com.example.materialdesignsupportv7.adapters.CarAdapter;
import com.example.materialdesignsupportv7.domain.Car;
import com.example.materialdesignsupportv7.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

public class CarFragment extends Fragment implements RecyclerViewOnClickListenerHack {
    protected RecyclerView mRecyclerView;
    protected List<Car> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        mRecyclerView = view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                CarAdapter carAdapter = (CarAdapter) mRecyclerView.getAdapter();

                if (mList.size() == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Car> listAux = ((MainActivity) getActivity()).getSetCarList(10);
                    for (int i = 0; i < listAux.size(); i++) {
                        carAdapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecycleViewTouchListener(getActivity(), mRecyclerView, this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mList = ((MainActivity) getActivity()).getSetCarList(10);
        CarAdapter carAdapter = new CarAdapter(getActivity(), mList);
        //carAdapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(carAdapter);

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_LONG).show();
//        CarAdapter carAdapter = new CarAdapter(getActivity(), mList);
//        carAdapter.removeListItem(position);
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_LONG).show();
//        CarAdapter carAdapter = new CarAdapter(getActivity(), mList);
//        carAdapter.removeListItem(position);
    }

    private static class RecycleViewTouchListener implements RecyclerView.OnItemTouchListener {

        private Context context;
        private GestureDetector gestureDetector;
        private RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack;

        public RecycleViewTouchListener(Context pContext, final RecyclerView pRecyclerView, RecyclerViewOnClickListenerHack pRecyclerViewOnClickListenerHack) {
            context = pContext;
            recyclerViewOnClickListenerHack = pRecyclerViewOnClickListenerHack;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View cv = pRecyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && recyclerViewOnClickListenerHack != null) {
                        recyclerViewOnClickListenerHack.onLongPressClickListener(cv,
                                pRecyclerView.getChildLayoutPosition(cv));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = pRecyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && recyclerViewOnClickListenerHack != null) {
                        recyclerViewOnClickListenerHack.onClickListener(cv,
                                pRecyclerView.getChildLayoutPosition(cv));
                    }
                    return (true);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            gestureDetector.onTouchEvent(motionEvent);
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean b) {

        }
    }
}
