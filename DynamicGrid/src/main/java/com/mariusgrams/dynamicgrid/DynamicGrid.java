package com.mariusgrams.dynamicgrid;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class DynamicGrid extends GridLayout {
    private static final int DEFAULT_ROW_COUNT = 8;
    private static final int DEFAULT_COLUMN_COUNT = 8;
    private static final int DEFAULT_MARGIN = 10;
    private boolean fillWithEmptyItems = false;
    private int itemMargin = DEFAULT_MARGIN;
    private int[][] matrix;
    private List<View> tempViewList = new ArrayList<>();
    private int maxWidth = 0;
    private int maxHeight = 0;
    private boolean wasBuildRequested = false;
    private IViewCallback viewCallback;
    private List<GridItem> itemList = new ArrayList<>();

    public DynamicGrid(Context context) {
        super(context);
        setRowCount(DEFAULT_ROW_COUNT);
        setColumnCount(DEFAULT_COLUMN_COUNT);
        resetMatrix();
    }

    public DynamicGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRowCount(DEFAULT_ROW_COUNT);
        setColumnCount(DEFAULT_COLUMN_COUNT);
        resetMatrix();
    }

    public boolean isFillWithEmptyItems() {
        return fillWithEmptyItems;
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount);
        resetMatrix();
    }

    @Override
    public void setColumnCount(int columnCount) {
        super.setColumnCount(columnCount);
        resetMatrix();
    }

    public void setFillWithEmptyItems(boolean fillWithEmptyItems) {
        this.fillWithEmptyItems = fillWithEmptyItems;
    }

    public void setItemMargin(int itemMargin) {
        this.itemMargin = itemMargin;
    }

    public void addGridItem(GridItem gridItem){
        this.itemList.add(gridItem);
    }

    private void resetMatrix(){
        this.matrix = new int[getRowCount()][getColumnCount()];
    }

    private void setItemMatrix(List<GridItem> gridItemList) {
        this.itemList = gridItemList;

        if (gridItemList == null || gridItemList.size() == 0) {
            return;
        }

        if (getColumnCount() == 0) {
            return;
        }

        for (GridItem gridItem : this.itemList) {
            setMatrix(gridItem);
        }

    }

    private void setMatrix(GridItem gridItem) {
        //check if there is space for item
        //TODO check if item is out of matrix
        for (int i = gridItem.getRow(); i < gridItem.getRow() + gridItem.getRowSpan(); i++) {
            for (int j = gridItem.getColumn(); j < gridItem.getColumn() + gridItem.getColumnSpan(); j++) {
                if (matrix[i][j] != 0) {
                    return;
                } else {
                    matrix[i][j] = 1;
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        this.maxWidth = widthSpec;
        this.maxHeight = heightSpec;
        if(wasBuildRequested){
            build();
            wasBuildRequested = false;
        }
    }

    public void build(){
        if(maxHeight == 0 && maxWidth == 0){
            wasBuildRequested = true;
            return;
        }
        removeAllViews();
        tempViewList.clear();
        resetMatrix();
        setItemMatrix(itemList);

        //add all items to gridLayout
        for (GridItem gridItem : itemList) {
            addGridItemToLayout(gridItem, false);
        }


        //fill with empty views
        for (int col = 0; col < getRowCount(); col++) {
            for (int row = 0; row < getColumnCount(); row++) {
                int isCellFilled = matrix[row][col];
                if(isCellFilled == 0){
                    addEmptyViewToGridLayout(row, col);
                }
            }
        }

        //add all views to grid layout
        new Handler(Looper.getMainLooper()).post(() -> {
            removeAllViews();
            for (View v: new ArrayList<>(tempViewList)) {
                addView(v);
            }
        });
    }

    public void setViewCallback(IViewCallback viewCallback) {
        this.viewCallback = viewCallback;
    }

    private void addEmptyViewToGridLayout(int row, int column) {
        GridItem gridItem;
        if (fillWithEmptyItems) {
            //gridItem = (GridItem) emptyGridItem.clone();
            gridItem = new GridItem(row, column);
            gridItem.setRow(row);
            gridItem.setColumn(column);
            gridItem.setRowSpan(1);
            gridItem.setColumnSpan(1);
        } else {
            gridItem = new GridItem(row, column) {};
        }

        addGridItemToLayout(gridItem, true);
    }

    private void addGridItemToLayout(GridItem gridItem, boolean isEmptyItem) {
        View v;

        v = new View(getContext());
        if(isEmptyItem){
            v.setBackground(getResources().getDrawable(R.drawable.empty_item_background));
        }else{
            v = viewCallback.onViewCreate(gridItem);
        }

        //set row span and column span
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();

        layoutParams.width = calculateItemWidth(gridItem);
        layoutParams.height = calculateItemHeight(gridItem);

        gridItem.setWidth(layoutParams.width);
        gridItem.setHeight(layoutParams.height);

        layoutParams.rowSpec = GridLayout.spec(gridItem.getRow(), gridItem.getRowSpan());
        layoutParams.columnSpec = GridLayout.spec(gridItem.getColumn(), gridItem.getColumnSpan());

        layoutParams.topMargin = itemMargin;
        layoutParams.leftMargin = itemMargin;

        v.setLayoutParams(layoutParams);

        if (isEmptyItem) {
            v.setFocusable(false);
            v.setClickable(false);

            if (!fillWithEmptyItems) {
                v.setFocusable(true);
                v.setClickable(true);
                v.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            v.setFocusable(true);
            v.setClickable(true);
        }

        tempViewList.add(v);
    }

    private int calculateItemWidth(GridItem gridItem) {
        int baseWidth = (maxWidth / getColumnCount()) - itemMargin - 1; //one pixel padding right

        int itemWidth = baseWidth * gridItem.getColumnSpan();

        if (gridItem.getColumnSpan() > 1) {
            int colSpan = gridItem.getColumnSpan() - 1;
            itemWidth = itemWidth + (colSpan * itemMargin);
        }

        return itemWidth;
    }

    private int calculateItemHeight(GridItem gridItem) {
        int baseHeight = (maxHeight / getRowCount()) - itemMargin - 1;

        int itemHeight = baseHeight * gridItem.getRowSpan();

        if (gridItem.getRowSpan() > 1) {
            int rowSpan = gridItem.getRowSpan() - 1;
            itemHeight = itemHeight + (rowSpan * itemMargin);
        }

        return itemHeight;
    }
}
