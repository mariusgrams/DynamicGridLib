package com.mariusgrams.dynamicgridlib.examples;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mariusgrams.dynamicgrid.DynamicGrid;
import com.mariusgrams.dynamicgrid.GridItem;
import com.mariusgrams.dynamicgrid.IViewCallback;
import com.mariusgrams.dynamicgridlib.MainActivity;
import com.mariusgrams.dynamicgridlib.R;

import java.util.Random;

public class RandomItemsColor extends Fragment implements IViewCallback {

    private DynamicGrid dynamicGrid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        container.removeAllViews();
        MainActivity.showBackButton(true);
        View v = inflater.inflate(R.layout.fragment_random_items, container, false);

        this.dynamicGrid = v.findViewById(R.id.dynamicGrid);

        dynamicGrid.setFillWithEmptyItems(true);
        dynamicGrid.setViewCallback(this);

        addRandomItems();
        dynamicGrid.build();

        return v;
    }

    private void addRandomItems(){
        dynamicGrid.setColumnCount(8);
        dynamicGrid.setRowCount(8);

        for (int i = 0; i < dynamicGrid.getRowCount(); i++) {
            for (int j = 0; j < dynamicGrid.getColumnCount(); j++) {
                dynamicGrid.addGridItem(new GridItem(i, j));
            }
        }
    }

    @Override
    public View onViewCreate(GridItem gridItem) {
        View view = new View(getContext());

        int color = Color.argb(255, getRandomNumber(0, 256), getRandomNumber(0, 256), getRandomNumber(0, 256));
        view.setBackgroundColor(color);

        return view;
    }

    private int getRandomNumber(int min, int max){
        Random rnd = new Random();
        return rnd.nextInt(max - min + 1) + min;
    }
}