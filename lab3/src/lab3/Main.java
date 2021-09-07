package lab3;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String fileName = "f.txt";
            int size = GetSizeFromFile(fileName);

            double[][] A = new double[size][size];
            double[] F = new double[size];

            ReadDataFromFile(A, F, fileName);
            Print(A);

            double[] solution = Solve(A, F);

            for (double item : solution)
                System.out.println(item);
            double[][] B = {{1, 2, 3},
                            {4, -5, 7},
                            {1, 0, 9}};
            double result = FindMultiple(B);
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException!");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("ArrayIndexOutOfBoundsException!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException!");
        }  catch (Exception e) {
            System.out.println("Exception!");
        }
    }

    static double FindMultiple(double[][] A) throws ArrayIndexOutOfBoundsException {
        double minInRow = A[0][0], maxInCol = A[0][0], result = 0;
        int indexRow = 0, indexCol = 0, size = A[0].length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(A[i][j] < minInRow)
                {
                    minInRow = A[i][j];
                    indexRow = i;
                }
                if(A[j][i] > maxInCol)
                {
                    maxInCol = A[i][j];
                    indexCol = j;
                }
            }
        }
        for (int i = 0; i < size; i++){
            result += A[i][indexCol] * A[indexRow][i];
        }
        System.out.println("\nmax: " + maxInCol + " indexCol " + indexCol + "\nmin: " + minInRow + " indexRow " + indexRow);
        System.out.println("result: " + result);
        return result;
    }

    static int GetSizeFromFile(String fileName) throws IOException {
        int size;
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        size = Integer.parseUnsignedInt(scanner.next());
        scanner.close();
        return size;
    }

    static void Print(final double[][] A) throws IndexOutOfBoundsException {
        int size = A[0].length;
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                System.out.print(A[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    static double[] Solve(double[][] A, double[] F) {
        int size = A.length;
        double[] solution = new double[size];
        for (int i = size - 1; i >= 0; i--) // обратный ход
        {
            double sum = 0;
            for (int j = size - 1; j >= i; j--) {
                sum += A[i][j] * solution[j];
            }
            solution[i] = (F[i] - sum) / A[i][i];
        }
        return solution;
    }

    static void ReadDataFromFile(double[][] A, double[] F, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        int size = Integer.parseUnsignedInt(scanner.nextLine());
        for (int i = 0; i < size; i++) {
            String[] line = scanner.nextLine().split(" ");
            if(line.length != size) {
                System.out.println("Wrong input!");
                throw new IOException();
            }
            for (int j = 0; j < size; j++) {
                A[i][j] = Double.parseDouble(line[j]);
            }
        }
        for (int i = 0; i < size; i++) {
            F[i] = Double.parseDouble(scanner.next());
        }
        if(scanner.hasNext()) {
            System.out.println("Wrong input!");
            throw new IOException();
        }
        scanner.close();
    }
}