package commons.models.data;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.time.Instant;

public class SortedDataStructure {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected final SimpleQuote referencePrice;

    protected final double[] xValues;
    protected final double[] yValues;

    protected final int count;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public SortedDataStructure(SimpleQuote referencePrice, double[] xValues, double[] yValues) {

        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException();
        }

        if (xValues.length < 2) {
            throw new IllegalArgumentException();
        }

        this.referencePrice = referencePrice;
        this.xValues = xValues;
        this.yValues = yValues;

        this.count = this.xValues.length;
    }

    public SortedDataStructure(SortedDataStructure s) {
        this.referencePrice = s.getReferencePrice();
        this.xValues = s.getIndependentValues();
        this.yValues = s.getDependentValues();
        this.count = this.xValues.length;
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public double[] getIndependentValues() {
        return xValues;
    }

    public double[] getDependentValues() {
        return yValues;
    }

    public int getCount() {
        return count;
    }

    public SimpleQuote getReferencePrice() {
        return referencePrice;
    }

    // ##################################################################
    // INTERPOLATION
    // ##################################################################

    public double interpolateLinearly(double x) {

        if (x < this.xValues[0]) {
            return this.yValues[0];
        }

        if (x > this.xValues[count - 1]) {
            return this.yValues[count-1];
        }

        LinearInterpolator interpolator = new LinearInterpolator();
        PolynomialSplineFunction psf = interpolator.interpolate(this.xValues, this.yValues);
        return psf.value(x);
    }



}
