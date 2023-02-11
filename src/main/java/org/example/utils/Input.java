package org.example.utils;

import org.example.Complex;
import org.example.Matrix;

import java.util.Scanner;

public class Input {

    /**
     * Ввод матрицы с консоли
     */
    public static Matrix inputMatrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of the matrix:");
        System.out.print("Rows = ");
        int numberOfRows = scanner.nextInt();
        System.out.print("Columns = ");
        int numberOfColumns = scanner.nextInt();
        Matrix matrix = new Matrix(numberOfRows, numberOfColumns);
        System.out.println("Enter matrix with size " + matrix.getNumberOfRows() + "x" + matrix.getNumberOfColumns() + ":");
        for (int i = 0; i < matrix.getNumberOfRows(); ++i) {
            for (int j = 0; j < matrix.getNumberOfColumns(); ++j) {
                String number = scanner.next();
                matrix.setElement(new Complex(number), i, j);
            }
        }
        return matrix;
    }
}
