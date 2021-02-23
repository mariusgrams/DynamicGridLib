package com.mariusgrams.dynamicgrid;

import android.content.Context;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class GridItemView extends LinearLayout {

    private final Context context;
    private GridItem gridItem;

    public GridItemView(Context context, int layoutView) {
        super(context);
        this.context = context;
        initView(layoutView);
    }

    public GridItem getGridItem() {
        return gridItem;
    }

    public void setGridItem(GridItem gridItem) {
        this.gridItem = gridItem;
    }

    private void initView(int layoutId) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        View v = View.inflate(context, layoutId, null);
        v.setLayoutParams(layoutParams);


        addView(v);
        this.setLayoutParams(layoutParams);
    }
}
