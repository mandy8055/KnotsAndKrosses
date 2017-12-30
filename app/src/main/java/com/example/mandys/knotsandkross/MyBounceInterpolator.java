package com.example.mandys.knotsandkross;

/**
 * Created by MandyS on 12/30/2017.
 */

public class MyBounceInterpolator implements android.view.animation.Interpolator {
   private double mAmplitude = 1;
    private double mFrequency = 10;
    MyBounceInterpolator(double mAmplitude, double mFrequency){
        this.mAmplitude = mAmplitude;
        this.mFrequency = mFrequency;
    }

    @Override
    public float getInterpolation(float input) {
        return (float)(-1 * Math.pow(Math.E, -input / mAmplitude)
                * Math.cos(mFrequency * input) + 1);
    }
}
