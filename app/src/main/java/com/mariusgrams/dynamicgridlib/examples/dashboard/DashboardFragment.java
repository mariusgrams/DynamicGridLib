package com.mariusgrams.dynamicgridlib.examples.dashboard;

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

public class DashboardFragment extends Fragment implements IViewCallback {

    private static final int ROWS = 8;
    private static final int COLUMNS = 8;

    private DynamicGrid dynamicGrid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        MainActivity.showBackButton(true);

        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        this.dynamicGrid = v.findViewById(R.id.dynamicGrid);
        dynamicGrid.setRowCount(ROWS);
        dynamicGrid.setColumnCount(COLUMNS);
        dynamicGrid.setFillWithEmptyItems(true);
        dynamicGrid.setViewCallback(this);

        dynamicGrid.addGridItem(new TempGridItem(1, 1, 3, 4));
        dynamicGrid.addGridItem(new CameraGridItem(5,0,3,8));
        dynamicGrid.build();

        return v;
    }

    @Override
    public View onViewCreate(GridItem gridItem) {

        if(gridItem instanceof TempGridItem){
            TempGridItemView tempGridItemView = new TempGridItemView(getContext());
            tempGridItemView.setGridItem(gridItem);
            return tempGridItemView;
        }

        if(gridItem instanceof CameraGridItem){
            CameraGridItemView cameraGridItemView = new CameraGridItemView(getContext());
            cameraGridItemView.setGridItem(gridItem);
            return cameraGridItemView;
        }

        return null;
    }
}