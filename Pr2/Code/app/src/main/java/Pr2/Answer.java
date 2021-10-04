package Pr2;

import java.io.Serializable;

public class Answer implements Serializable {
    private Complex[] roots;
    
    Answer(Complex r1, Complex r2) {
        this.roots = new Complex[]{ r1, r2 };
    }

    Answer(Complex r) {
        this.roots = new Complex[]{ r };
    }

    Answer() {
        this.roots = new Complex[0];
    }

    @Override
    public String toString() {
        switch (roots.length) {
            case 0:
                return "No answer";
            case 1: 
                return "Single answer: " + roots[0];
            case 2: 
                return roots[0] + ", " + roots[1];
        }

        return "Something wrong";
    }
}
