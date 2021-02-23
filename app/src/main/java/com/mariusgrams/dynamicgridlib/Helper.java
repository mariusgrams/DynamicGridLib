package com.mariusgrams.dynamicgridlib;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Helper {

    public static double getRandomValue(double min, double max){
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
