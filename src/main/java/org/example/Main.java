package org.example;

import org.example.exceptions.InconsistentMatricesException;
import org.example.exceptions.NonSquareMatrixException;
import org.example.exceptions.NotEqualSizeOfMatrixException;
import org.example.utils.Input;

public class Main {
    public static void main(String[] args){
        complexClassTests();
        matrixClassTests();
    }

    public static void complexClassTests() {
        System.out.println("Constructor without parameters:");
        Complex complex = new Complex(); // использование конструктора без параметров
        complex.print();

        System.out.println("Constructor with parameters:");
        complex = new Complex(3.8, -4.5); // использование конструктора с парараметрами
        System.out.println("Complex number " + complex + "\n"); // проверка метода to_string

        System.out.println("Constructor with String parameter:");
        complex = new Complex("78.24+11.2i");
        complex.print();

        System.out.println("Setters:");
        complex.setReal(543.8);
        complex.setComplex(15.6);
        complex.print(); // задание комплексного числа через сеттеры
        System.out.println("Trigonometric:");
        complex.printTrigonometric(); // в тригонометрической форме

        System.out.println("Parsing:");
        complex = Complex.parseComplex("34.78+56443.332i"); // проверка парсинга
        complex.print();

        Complex firstEqualComplex = new Complex(5.5, -8.33);
        Complex secondEqualComplex = new Complex(5.5, -8.33);
        Complex notEqualComplex = new Complex(4.5, -7.33);
        System.out.println("Check of method equals(): " + firstEqualComplex.equals(secondEqualComplex)); // true
        System.out.println("Check of method equals(): " + firstEqualComplex.equals(notEqualComplex)); // false

        System.out.println("The module of a complex number: " + complex.abs() + "\n"); // проверка метода abs

        System.out.println("The phase of a complex number: " + complex.phase() + "\n"); // проверка метода phase

        Complex complex1 = new Complex(-5.5, 9.77); // еще одно комплексное число для проверки операций

        System.out.println("Summation:");
        Complex resultOfSummation = complex.add(complex1); // проверка суммы
        resultOfSummation.print();

        System.out.println("Subtraction:");
        Complex resultOfSubtraction = complex.subtract(complex1); // проверка вычитания
        resultOfSubtraction.print();

        System.out.println("Multiplication:");
        Complex resultOfMultiplication = complex.multiply(complex1); // проверка умножения
        resultOfMultiplication.print();

        System.out.println("Division:");
        Complex resultOfDivision = complex.divide(complex1); // проверка деления
        resultOfDivision.print();
    }

    public static void matrixClassTests(){

        Matrix matrix = Input.inputMatrix();
        matrix.print();

        Complex number2 = new Complex(-12.33, -45.8);
        matrix = matrix.multiplyOnNumber(number2);
        matrix.print();

        System.out.println("Constructor with a matrix of type double:");
        matrix = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}}); // конструктор с матрицей типа double
        matrix.print();

        try {
            System.out.println("Determinant: " + matrix.determinant() + "\n"); // проверка метода determinant
        } catch (NonSquareMatrixException exception) {
            exception.printStackTrace();
        }

        System.out.println("Constructor with a matrix of type Complex:");
        matrix = new Matrix(new Complex[][]{
                {new Complex(1, 1), new Complex(2, 2), new Complex(3, 3)},
                {new Complex(4, 4), new Complex(5, 5), new Complex(6, 6)},
                {new Complex(7, 7), new Complex(8, 8), new Complex(9, 9)}}); // конструктор с матрицей типа Complex
        matrix.print();

        System.out.println("Element with indexes 0,1: " + matrix.getElement(0, 1) + "\n"); // проверка метода getElement

        matrix.setElement(1.1, 0, 0); // проверка метода setElement с типом double
        matrix.setElement(new Complex(5.5, 5.5), 1, 1); // проверка метода setElement с типом Complex
        System.out.println("Changed matrix:");
        matrix.print();

        Matrix matrixForSummaryAndSubtraction1 = new Matrix(new Complex[][]{
                {new Complex(-5, 3), new Complex(2.8, 8), new Complex(3)},
                {new Complex(6.5, -2.3), new Complex(5, 5.2), new Complex(10.3, 1.3)},
                {new Complex(7), new Complex(0, -3), new Complex(2, 1)}});
        Matrix matrixForSummaryAndSubtraction2 = new Matrix(new Complex[][]{
                {new Complex(5, -3), new Complex(-2.8, -8), new Complex(-3)},
                {new Complex(-6.5, 2.3), new Complex(-5, -5.2), new Complex(-10.3, -1.3)},
                {new Complex(-7), new Complex(0, 3), new Complex(-2, -1)}});

        System.out.println("Summation:");
        try {
            Matrix resultOfSummation = matrixForSummaryAndSubtraction1.add(matrixForSummaryAndSubtraction2); // проверка суммы
            resultOfSummation.print(); // результатом должна быть 0
        } catch (NotEqualSizeOfMatrixException exception) {
            exception.printStackTrace();
        }

        System.out.println("Subtraction:");
        try {
            Matrix resultOfSubtraction = matrixForSummaryAndSubtraction1.subtract(matrixForSummaryAndSubtraction2); // проверка вычитания
            resultOfSubtraction.print();
        } catch (NotEqualSizeOfMatrixException exception) {
            exception.printStackTrace();
        }

        System.out.println("Multiplication on a number:");
        Matrix resultOfMultiplicationOnNumber = matrixForSummaryAndSubtraction1.multiplyOnNumber(2); // проверка умножения на число
        resultOfMultiplicationOnNumber.print(); // результат должен совпасть с результаттом вычитания


        Matrix matrixForMultiplication1 = new Matrix(new Complex[][]{
                {new Complex(15), new Complex(27)},
                {new Complex(18), new Complex(10)}});
        Matrix matrixForMultiplication2 = new Matrix(new Complex[][]{
                {new Complex(35)},
                {new Complex(16)}});

        System.out.println("Multiplication:");
        try {
            Matrix resultOfMultiplication = matrixForMultiplication1.multiply(matrixForMultiplication2); // проверка умножения
            resultOfMultiplication.print();
        } catch (InconsistentMatricesException exception) {
            exception.printStackTrace();
        }

        Matrix matrixForTransposition = new Matrix(new double[][]{
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0}});
        System.out.println("Transposition:"); // транспонирование матрицы
        Matrix transposedMatrix = matrixForTransposition.transpose();
        transposedMatrix.print();
    }
}