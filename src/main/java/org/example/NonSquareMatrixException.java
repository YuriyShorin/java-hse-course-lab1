package org.example;

/**
 * Неквадратная матрица
 */
public class NonSquareMatrixException extends Exception {

    @Override
    public void printStackTrace() {
        System.out.println("The matrix must be square");
    }
}
