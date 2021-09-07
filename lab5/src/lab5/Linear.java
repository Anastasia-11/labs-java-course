package lab5;

public class Linear extends Series {

    public Linear(double first, int N, double coefficient) {
        super(first, N, coefficient);
    }
    @Override
    public double getElement(int index) {
        return first + coefficient * index;
    }
}
