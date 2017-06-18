package logic;

import java.util.List;

public class Histogram {
    private Object min;
    private Object max;
    private Object step;
    private Integer stepCount;
    private List<Long> frequencies;
    private Double expectation;//матожидание
    private Double dispersion;

    public static int Sturges(Long count){
        return (int)(1+3.322*Math.log10(count));
    }
    @Override
    public String toString() {
        return "Histogram{" +
                "min=" + min +
                ", max=" + max +
                ", step=" + step +
                ", stepCount=" + stepCount +
                ", frequencies=" + frequencies +
                '}';
    }

    public Histogram(Object min, Object max, Long cnt) {
        if (cnt==0) return;
        this.min = min;
        this.max = max;
        stepCount = Histogram.Sturges(cnt);
        if (stepCount>20)
            stepCount = 20;
        if ((min instanceof Integer)&&(max instanceof Integer))
            step = ((Integer)max - (Integer)min) / stepCount;
        else if ((min instanceof Long)&&(max instanceof Long))
            step = ((Long)max - (Long)min) / stepCount;
        else
        if ((min instanceof Double)&&(max instanceof Double))
            step = ((Double)max -  (Double)min) / stepCount;
    }

    public Object getMin() {
        return min;
    }

    public void setMin(Object min) {
        this.min = min;
    }

    public Object getMax() {
        return max;
    }

    public void setMax(Object max) {
        this.max = max;
    }

    public Object getStep() {
        return step;
    }

    public void setStep(Object step) {
        this.step = step;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    public List<Long> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(List<Long> frequencies) {
        this.frequencies = frequencies;
    }

    public Double getExpectation() {
        return expectation;
    }

    public void setExpectation(Double expectation) {
        this.expectation = expectation;
    }

    public Double getDispersion() {
        return dispersion;
    }

    public void setDispersion(Double dispersion) {
        this.dispersion = dispersion;
    }
}