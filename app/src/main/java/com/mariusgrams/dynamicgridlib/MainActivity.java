package com.mariusgrams.dynamicgridlib;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mariusgrams.dynamicgrid.DynamicGrid;
import com.mariusgrams.dynamicgrid.GridItem;
import com.mariusgrams.dynamicgrid.IViewCallback;
import com.mariusgrams.dynamicgridlib.gridItems.ExampleGridItem1;
import com.mariusgrams.dynamicgridlib.gridItems.ExampleGridItem2;

public class MainActivity extends AppCompatActivity implements IViewCallback {

    private DynamicGrid dynamicGrid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dynamicGrid = findViewById(R.id.dynamicGrid);
        dynamicGrid.setFillWithEmptyItems(true);
        dynamicGrid.setViewCallback(this);

        addItems();

        dynamicGrid.build();
    }

    private void addItems(){
       dynamicGrid.addGridItem(new ExampleGridItem1(0, 0, 4, 2));
       dynamicGrid.addGridItem(new ExampleGridItem2(4, 6, 3, 1));
    }

    @Override
    public View onViewCreate(GridItem gridItem) {

        if(gridItem instanceof ExampleGridItem1){
            return View.inflate(this, R.layout.example_item_1, null);
        }

        if(gridItem instanceof ExampleGridItem2){
            return View.inflate(this, R.layout.example_item_2, null);
        }

        return null;
    }
}