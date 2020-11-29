package com.mariusgrams.dynamicgridlib.examples;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mariusgrams.dynamicgrid.DynamicGrid;
import com.mariusgrams.dynamicgrid.GridItem;
import com.mariusgrams.dynamicgrid.IViewCallback;
import com.mariusgrams.dynamicgridlib.MainActivity;
import com.mariusgrams.dynamicgridlib.R;

public class SimpleExampleFragment extends Fragment implements IViewCallback {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        MainActivity.showBackButton(true);
        View v = inflater.inflate(R.layout.fragment_simple_example, container, false);

        DynamicGrid dynamicGrid = v.findViewById(R.id.dynamicGrid);
        dynamicGrid.setRowCount(4);
        dynamicGrid.setColumnCount(4);
        dynamicGrid.setViewCallback(this);
        dynamicGrid.setFillWithEmptyItems(true);
        dynamicGrid.addGridItem(new GridItem(0,0,2,1));
        dynamicGrid.addGridItem(new GridItem(2, 0, 1, 3));
        dynamicGrid.addGridItem(new GridItem(0, 1));
        dynamicGrid.addGridItem(new GridItem(1, 2));
        dynamicGrid.addGridItem(new GridItem(0, 3));
        dynamicGrid.build();

        return v;
    }

    @Override
    public View onViewCreate(GridItem gridItem) {
        View view = new View(getContext());
        view.setBackgroundColor(Color.RED);
        return view;
    }
}