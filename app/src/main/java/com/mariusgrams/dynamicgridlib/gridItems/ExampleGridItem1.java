package com.mariusgrams.dynamicgridlib.gridItems;

import com.mariusgrams.dynamicgrid.GridItem;

public class ExampleGridItem1 extends GridItem {

    public ExampleGridItem1(int row, int column) {
        super(row, column);
    }

    public ExampleGridItem1(int row, int column, int rowSpan, int columnSpan) {
        super(row, column, rowSpan, columnSpan);
    }
}
