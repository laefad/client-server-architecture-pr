package Pr2;

import java.io.Serializable;

public class Complex implements Serializable {
    private double re;
    private double im;

    Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    Complex(double re) {
        this(re, 0);
    }

    @Override
    public String toString() {
        if (im == 0)
            return "" + re;
        else if (re == 0)
            return im + "i";
        else
            return re + (im > 0 ? " + " : " - ") + Math.abs(im) + "i";
    }
}
