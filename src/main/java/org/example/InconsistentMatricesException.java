package org.example;

/**
 * Несогласованная матрица
 */
public class InconsistentMatricesException extends Exception {

    @Override
    public void printStackTrace() {
        System.out.println("Number of columns in the first matrix must be equal with number of rows in the second");
    }
}
