package lab5;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    protected double first;
    protected int N;
    protected double coefficient;

    public Series(double first, int N, double coefficient) {
        this.first = first;
        this.N = N;
        this.coefficient = coefficient;
    }

    public abstract double getElement(int index);

    public double sum() {
        double sum = 0;
        for (int i = 0; i < N; i++)
            sum += getElement(i);
        return sum;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < N; i++) {
            str.append(getElement(i)).append(" ");
        }
        str.append('\n');
        return str.toString();
    }

    public void toFile(String fileName) throws IOException {
            FileWriter writer = new FileWriter(fileName, false);
            writer.write(toString());
            //writer.write(" " + this);
            writer.write(String.valueOf(sum()));
            writer.flush();
            writer.close();
    }
}
