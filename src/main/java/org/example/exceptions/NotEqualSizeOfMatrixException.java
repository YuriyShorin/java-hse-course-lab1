package org.example.exceptions;

/**
 * Матрицы разных размеров
 */
public class NotEqualSizeOfMatrixException extends RuntimeException {

    @Override
    public void printStackTrace() {
        System.out.println("The matrices must be the same size");
    }
}
