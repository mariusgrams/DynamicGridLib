package com.mariusgrams.dynamicgridlib.examples.dashboard;

import com.mariusgrams.dynamicgrid.GridItem;

public class CameraGridItem extends GridItem {

    public CameraGridItem(int row, int column) {
        super(row, column);
    }

    public CameraGridItem(int row, int column, int rowSpan, int columnSpan) {
        super(row, column, rowSpan, columnSpan);
    }
}
