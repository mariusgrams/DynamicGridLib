package com.mariusgrams.dynamicgridlib.examples.dashboard;

import com.mariusgrams.dynamicgrid.GridItem;

public class TempGridItem extends GridItem {


    public TempGridItem(int row, int column, int rowSpan, int columnSpan) {
        super(row, column, rowSpan, columnSpan);
    }

    public TempGridItem(int row, int column) {
        super(row, column);
    }
}
