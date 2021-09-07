package lab5;

public class Exponential extends Series {

    public Exponential(double first, int N, double coefficient) {
        super(first, N, coefficient);
    }
    @Override
    public double getElement(int index) {
        return first * Math.pow(coefficient, index);
    }
}
