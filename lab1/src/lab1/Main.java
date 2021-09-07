package lab1;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            double x = Double.parseDouble(args[0]);
            double k = Double.parseDouble(args[1]);
            Sum sum = new Sum();
            double summary = sum.Calculate(x,k);
            System.out.println(summary);
        }
        catch (NumberFormatException e)
        {
            System.out.println("NumberFormatException");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ArrayIndexOutOfBoundsException");
        }
    }
}
