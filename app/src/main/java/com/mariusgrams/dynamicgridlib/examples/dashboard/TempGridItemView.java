package com.mariusgrams.dynamicgridlib.examples.dashboard;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mariusgrams.dynamicgrid.GridItem;
import com.mariusgrams.dynamicgrid.GridItemView;
import com.mariusgrams.dynamicgridlib.Helper;
import com.mariusgrams.dynamicgridlib.R;

import java.util.ArrayList;

public class TempGridItemView extends GridItemView {

    private LineChart lineChart;
    private TextView txtTempValue;

    public TempGridItemView(Context context) {
        super(context, R.layout.temp_gridview);

        this.lineChart = findViewById(R.id.lineChart);
        this.txtTempValue = findViewById(R.id.txtTempValue);

        txtTempValue.setText(String.valueOf(Helper.round(Helper.getRandomValue(10.14, 25.41), 2)));
        setChartData();
    }



    private void setChartData(){
        ArrayList dataList = new ArrayList<Entry>();
        for (int i = 0; i < 10; i++) {
            float random = (float) Helper.getRandomValue(10.14, 25.41);
            dataList.add(new Entry(i, random));
        }

        LineDataSet lineDataSet = new LineDataSet(dataList, "");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.fontWhite));

        LineData lineData = new LineData(lineDataSet);

        lineChart.animateX(1000);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        lineChart.getXAxis().setLabelCount(lineDataSet.getEntryCount());
        lineChart.getAxisLeft().setTextColor(getResources().getColor(R.color.fontWhite));
        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setDrawLabels(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setData(lineData);
    }

}

