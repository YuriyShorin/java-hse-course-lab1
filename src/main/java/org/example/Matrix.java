package org.example;

import org.example.exceptions.InconsistentMatricesException;
import org.example.exceptions.NonSquareMatrixException;
import org.example.exceptions.NotEqualSizeOfMatrixException;

/**
 * Класс для работы с матрицами
 */
public class Matrix {

    /**
     * Матрица
     */
    private final Complex[][] matrix;

    /**
     * Количество строк в матрице
     */
    private final int numberOfRows;


    /**
     * Количество столбцов в матрице
     */
    private final int numberOfColumns;

    /**
     * Конструктор для создания квадратной матрицы
     *
     * @param size размер квадратной матрицы
     */
    public Matrix(int size) {
        this.numberOfRows = size;
        this.numberOfColumns = size;
        this.matrix = new Complex[this.numberOfRows][this.numberOfColumns];
    }

    /**
     * Конструктор для создания прямоугольной матрицы
     *
     * @param numberOfRows    количество строк в матрице
     * @param numberOfColumns количество столбцов в матрице
     */
   public Matrix(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.matrix = new Complex[this.numberOfRows][this.numberOfColumns];
    }

    /**
     * Конструктор для создания матрицы из массива чисел типа double
     *
     * @param matrix матрица в виде чисел типа double
     */
    public Matrix(double[][] matrix) {
        this.numberOfRows = matrix.length;
        this.numberOfColumns = matrix[0].length;
        this.matrix = new Complex[this.numberOfRows][this.numberOfColumns];
        for (int i = 0; i < this.numberOfRows; ++i) {
            for (int j = 0; j < this.numberOfColumns; ++j) {
                this.matrix[i][j] = new Complex(matrix[i][j]);
            }
        }
    }

    /**
     * Конструктор для создания матрицы из массива чисел типа complex
     *
     * @param matrix матрица в виде чисел типа Complex
     */
    public Matrix(Complex[][] matrix) {
        this.numberOfRows = matrix.length;
        this.numberOfColumns = matrix[0].length;
        this.matrix = matrix;
    }

    /**
     * @return количество строк в матрице
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * @return количество столбцов в матрице
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * @param i индекс строки, в которой находится эелемент
     * @param j индекс стобца, в которой находится элемент
     * @return элемент под индексами i, j
     */
    public Complex getElement(int i, int j) {
        return this.matrix[i][j];
    }

    /**
     * @param element число типа Complex
     * @param i       индекс строки, в которой необоходимо поменять элемент
     * @param j       индекс столбца, в котором необходимо поменять элемент
     */
    public void setElement(Complex element, int i, int j) {
        this.matrix[i][j] = element;
    }

    /**
     * @param element число типа double
     * @param i       индекс строки, в которой необоходимо поменять элемент
     * @param j       индекс столбца, в котором необходимо поменять элемент
     */
    public void setElement(double element, int i, int j) {
        this.matrix[i][j] = new Complex(element);
    }


    /**
     * Печать матрицы в консоль
     */
    public void print() {
        for (int i = 0; i < this.numberOfRows; ++i) {
            for (int j = 0; j < this.numberOfColumns; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @return определитель матрицы
     * @throws NonSquareMatrixException неквадратная матрица
     */
    public Complex determinant() throws NonSquareMatrixException {
        if (this.numberOfColumns != this.numberOfRows) {
            throw new NonSquareMatrixException();
        }
        return det(this.matrix, this.numberOfRows);
    }

    /**
     * @param matrix квадратная матрица
     * @param n      размер квадратной матрицы
     * @return определитель матрицы
     */
    public Complex det(Complex[][] matrix, int n) {
        if (n == 1) {
            return this.matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0].multiply(matrix[1][1]).subtract(matrix[0][1].multiply(matrix[1][0]));
        }
        Complex[][] minor = new Complex[n][n];
        Complex result = new Complex(0);
        int f = 1;
        for (int i = 0; i < n; ++i) {
            int minorRowIndex = 0, minorColumnIndex = 0;
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if ((j != 0) && (k != i)) {
                        minor[minorRowIndex][minorColumnIndex] = matrix[j][k];
                        minorColumnIndex++;
                        if (minorColumnIndex > n - 2) {
                            minorRowIndex++;
                            minorColumnIndex = 0;
                        }
                    }
                }
            }
            result = result.add(new Complex(f).multiply(matrix[0][i].multiply(det(minor, n - 1))));
            f *= -1;
        }
        return result;
    }

    /**
     * @param matrix матрица
     * @return сумма двух матриц
     * @throws NotEqualSizeOfMatrixException матрицы разного размера
     */
    public Matrix add(Matrix matrix) throws NotEqualSizeOfMatrixException {
        if (this.numberOfRows != matrix.getNumberOfRows() || this.numberOfColumns != matrix.getNumberOfColumns()) {
            throw new NotEqualSizeOfMatrixException();
        }
        Complex[][] sum = new Complex[this.numberOfRows][this.numberOfColumns];
        for (int i = 0; i < this.numberOfRows; ++i) {
            for (int j = 0; j < this.numberOfColumns; ++j) {
                sum[i][j] = this.getElement(i, j).add(matrix.getElement(i, j));
            }
        }
        return new Matrix(sum);
    }

    /**
     * @param matrix матрица
     * @return раность двух матриц
     * @throws NotEqualSizeOfMatrixException матрицы разного размера
     */
    public Matrix subtract(Matrix matrix) throws NotEqualSizeOfMatrixException {
        if (this.numberOfRows != matrix.getNumberOfRows() || this.numberOfColumns != matrix.getNumberOfColumns()) {
            throw new NotEqualSizeOfMatrixException();
        }
        Complex[][] subtraction = new Complex[this.numberOfRows][this.numberOfColumns];
        for (int i = 0; i < this.numberOfRows; ++i) {
            for (int j = 0; j < this.numberOfColumns; ++j) {
                subtraction[i][j] = this.getElement(i, j).subtract(matrix.getElement(i, j));
            }
        }
        return new Matrix(subtraction);
    }

    /**
     * @param number комлпексное число
     * @return произведение матрицы на число
     */
    public Matrix multiplyOnNumber(Complex number) {
        Complex[][] result = new Complex[this.numberOfRows][this.numberOfColumns];
        for (int i = 0; i < this.numberOfRows; ++i) {
            for (int j = 0; j < this.numberOfColumns; ++j) {
                result[i][j] = this.getElement(i, j).multiply(number);
            }
        }
        return new Matrix(result);
    }

    /**
     * @param number число типа double
     * @return произведение матрицы на число
     */
    public Matrix multiplyOnNumber(double number) {
        return multiplyOnNumber(new Complex(number));
    }

    /**
     * @param matrix матрица
     * @return произведение двух матриц
     * @throws InconsistentMatricesException несогласующиеся матрицы
     */
    public Matrix multiply(Matrix matrix) throws InconsistentMatricesException {
        if (this.numberOfColumns != matrix.getNumberOfRows()) {
            throw new InconsistentMatricesException();
        }
        Complex[][] product = new Complex[this.numberOfRows][matrix.getNumberOfColumns()];
        for (int i = 0; i < this.numberOfRows; i++) {
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
                product[i][j] = new Complex(0);
                for (int k = 0; k < this.numberOfColumns; k++)
                    product[i][j] = product[i][j].add(this.getElement(i, k).multiply(matrix.getElement(k, j)));
            }
        }
        return new Matrix(product);
    }

    /**
     * @return транспонированная матрица
     */
    public Matrix transpose() {
        Complex[][] result = new Complex[this.numberOfColumns][this.numberOfRows];
        for (int i = 0; i < this.numberOfRows; i++)
            for (int j = 0; j < this.numberOfColumns; j++)
                result[j][i] = this.getElement(i, j);
        return new Matrix(result);
    }
}
