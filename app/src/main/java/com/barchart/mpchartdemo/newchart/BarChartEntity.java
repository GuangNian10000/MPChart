package com.barchart.mpchartdemo.newchart;

import android.graphics.Color;
import android.graphics.Matrix;

import com.github.mikephil.chart_3_0_1v.charts.BarChart;
import com.github.mikephil.chart_3_0_1v.charts.BarLineChartBase;
import com.github.mikephil.chart_3_0_1v.data.BarData;
import com.github.mikephil.chart_3_0_1v.data.BarDataSet;
import com.github.mikephil.chart_3_0_1v.data.BarEntry;
import com.github.mikephil.chart_3_0_1v.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图
 * Created by jin
 */

public class BarChartEntity extends BaseChartEntity<BarEntry> {
    public BarChartEntity(BarLineChartBase chart, List<BarEntry>[] entries, String[] labels, int[] chartColor, int valueColor, float textSize) {
        super(chart, entries, labels, chartColor, valueColor, textSize);
    }

    @Override
    protected void initChart() {
        super.initChart();
        mChart.getAxisLeft().setDrawGridLines(true);
        mChart.getAxisLeft().enableGridDashedLine(10f, 15f, 0f);
        mChart.getAxisLeft().setGridLineWidth(0.5f);
        mChart.getAxisLeft().setGridColor(Color.parseColor("#f5f5f5"));
        mChart.getAxisLeft().setDrawZeroLine(false);
        mChart.getAxisRight().setDrawZeroLine(false);
        mChart.getAxisRight().setZeroLineWidth(0f);
        mChart.getAxisLeft().setZeroLineWidth(0f);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getXAxis().setDrawAxisLine(false);
        mChart.getXAxis().setAxisMinimum(0);

        /**
         * 下面这几个属性你们可以试试 挺有意思的
         * */
        // 设置是否可以触摸
        mChart.setTouchEnabled(true);
        // 是否可以拖拽
        mChart.setDragEnabled(true);//放大可拖拽
        // 是否可以缩放
        mChart.setScaleEnabled(false);
        // 集双指缩放
        mChart.setPinchZoom(false);


            //当为true时,放大图
            // 为了使 柱状图成为可滑动的,将水平方向 放大 2.5倍
        mChart.invalidate();
        Matrix mMatrix = new Matrix();
        mMatrix.postScale(2f, 1f);
        mChart.getViewPortHandler().refresh(mMatrix, mChart, false);
        mChart.animateY(1000);

    }

    @Override
    protected void setChartData() {
        BarDataSet barDataSet;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            barDataSet = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            barDataSet.setValues(mEntries[0]);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            barDataSet = new BarDataSet(mEntries[0], labels == null ? "" : labels[0]);
            barDataSet.setColors(mChartColors);
            List<Integer> colors = new ArrayList<>();
            for (int color : mChartColors) {
                colors.add(color);
            }
            barDataSet.setValueTextColors(colors);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(barDataSet);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(mTextSize);
            data.setBarWidth(0.9f);
            mChart.setData(data);
        }
    }


    public void setDrawValueAboveBar(boolean aboveBar) {
        ((BarChart)mChart).setDrawValueAboveBar(aboveBar);
    }

    /**
     * <p>设置bar宽度</p>
     * @param barWidth float
     */
    public void setBarWidth(float barWidth) {
        ((BarChart)mChart).getData().setBarWidth(barWidth);
    }
}
