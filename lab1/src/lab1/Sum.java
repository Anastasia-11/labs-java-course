package lab1;

public class Sum {
    public double Calculate(double x, double k) throws Exception {
        if(Math.abs(x) > 1){
            throw new Exception("Число х должно быть <= 1");
        }
        double currentItem = 1;
        double sum = currentItem;
        for (int i = 1; Math.abs(currentItem) >= k; i++){
            System.out.println(sum);
            currentItem *= -x * Math.pow(i, 2) / Math.pow((i + 1), 2);
            sum += currentItem;
        }
        return sum;
    }
}
