package org.example;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс для работы с комлпексными числами
 */
class Complex {

    /**
     * Реальная часть
     */
    private double real = 0;


    /**
     * Комплексная часть
     */
    private double complex = 0;

    /**
     * Конструктор без параметров
     */
    Complex() {
    }

    /**
     * Конструктор с одним параметром
     *
     * @param real реальная часть
     */

    Complex(double real) {
        this.real = real;
    }

    /**
     * Конструктор с двумя параметрами
     *
     * @param real    реальная часть
     * @param complex комплексна часть
     */
    Complex(double real, double complex) {
        this.real = real;
        this.complex = complex;
    }

    /**
     * Конструктор с парметром типа String
     *
     * @param number кмоплексное число записанное в формес строки
     */
    Complex(String number) {
        Complex complex = Complex.parseComplex(number);
        this.real = complex.getReal();
        this.complex = complex.getComplex();
    }

    /**
     * @param real реальная часть
     */
    public void setReal(double real) {
        this.real = real;
    }

    /**
     * @param complex комплексная часть
     */
    public void setComplex(double complex) {
        this.complex = complex;
    }

    /**
     * @return реальная часть
     */
    public double getReal() {
        return this.real;
    }

    /**
     * @return комплексная часть
     */
    public double getComplex() {
        return this.complex;
    }

    /**
     * @return модуль комплексного числа
     */
    public double abs() {
        return Math.sqrt(this.real * this.real + this.complex * this.complex);
    }

    /**
     * @return фаза компексного числа
     */
    public double phase() {
        return Math.atan2(this.complex, this.real);
    }

    /**
     * Вывод в комплексного числа в консоль
     */
    public void print() {
        System.out.println(this + "\n");
    }

    /**
     * Вывод комлпексного числа в консоль в тригонометрической форме
     */
    public void printTrigonometric() {
        double r = Math.sqrt(this.real * this.real + this.complex * this.complex);
        double phi = Math.toDegrees(this.phase());
        System.out.println(r + "(cos(" + phi + "°) + i*sin(" + phi + "°))\n");
    }

    /**
     * @return комлпексное число в форме строки
     */
    @Override
    public String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMAN);
        symbols.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.####", symbols);
        if (this.complex == 0) {
            return format.format(this.real);
        } else if (this.real == 0 && this.complex == 1) {
            return "i";
        } else if (this.real == 0) {
            return format.format(this.complex) + "i";
        } else if (this.complex == 1) {
            return format.format(this.real) + "+i";
        } else if (this.complex > 0) {
            return format.format(this.real) + "+" + format.format(this.complex) + "i";
        } else {
            return format.format(this.real) + format.format(this.complex) + "i";
        }
    }

    /**
     * @param number комлексное число
     * @return true если числа равны, false если нет
     */
    @Override
    public boolean equals(Object number) {
        if (number == null || number.getClass() != Complex.class) {
            return false;
        }
        return this.real == ((Complex) number).getReal() && this.complex == ((Complex) number).getComplex();
    }

    /**
     * @return хэшкод
     */
    @Override
    public int hashCode() {
        return 31 * Objects.hash(this.complex, this.real);
    }

    /**
     * @param number комлпексное число
     * @return сумма двух комплексных чисел
     */
    public Complex add(Complex number) {
        return new Complex(this.real + number.getReal(), this.complex + number.getComplex());
    }

    /**
     * @param number комлпексное число
     * @return разность двух комплексных чисел
     */
    public Complex subtract(Complex number) {
        return new Complex(this.real - number.getReal(), this.complex - number.getComplex());
    }

    /**
     * @param number комлпексное число
     * @return произведение двух комплексных чисел
     */
    public Complex multiply(Complex number) {
        double realPart = this.real * number.getReal() - this.complex * number.getComplex();
        double complexPart = this.real * number.getComplex() + this.complex * number.getReal();
        return new Complex(realPart, complexPart);
    }

    /**
     * @param number комлпексное число
     * @return частное двух комплексных чисел
     */
    public Complex divide(Complex number) {
        double realPart = this.multiply(new Complex(number.getReal(), -number.getComplex())).getReal()
                / number.multiply(new Complex(number.getReal(), -number.getComplex())).getReal();
        double complexPart = this.multiply(new Complex(number.getReal(), -number.getComplex())).getComplex()
                / number.multiply(new Complex(number.getReal(), -number.getComplex())).getReal();
        return new Complex(realPart, complexPart);
    }

    /**
     * @param numberToParse комлпексное число в форме строки
     * @return комлпексное число
     */
    public static Complex parseComplex(String numberToParse) {
        boolean isRealNegative = false;
        boolean isComplexNegative = false;
        if (numberToParse.startsWith("i")) {
            return new Complex(0, 1);
        }
        if (!numberToParse.contains("i")) {
            return new Complex(parseDouble(numberToParse), 0);
        }
        if (!numberToParse.substring(1).contains("+") && !numberToParse.substring(1).contains("-")) {
            numberToParse = numberToParse.replace("i", "");
            return new Complex(0, parseDouble(numberToParse));
        }
        if (numberToParse.startsWith("-")) {
            isRealNegative = true;
            numberToParse = numberToParse.substring(1);
        }
        if (numberToParse.contains("-")) {
            isComplexNegative = true;
        }
        String[] split = numberToParse.split("[+-]");
        double real, complex;
        if (numberToParse.indexOf("+") == numberToParse.indexOf("i") - 1 || numberToParse.indexOf("-") == numberToParse.indexOf("i") - 1) {
            if (isRealNegative) {
                real = -parseDouble(split[0]);
            } else {
                real = parseDouble(split[0]);
            }
            complex = 1;
            return new Complex(real, complex);
        }
        split[1] = split[1].replace("i", "");
        if (isRealNegative) {
            real = -parseDouble(split[0]);
        } else {
            real = parseDouble(split[0]);
        }
        if (isComplexNegative) {
            complex = -parseDouble(split[1]);
        } else {
            complex = parseDouble(split[1]);
        }
        return new Complex(real, complex);
    }

    /**
     * @param numberToParse комплексное число в форму строки
     * @return комлпексное число
     */
    private static double parseDouble(String numberToParse) {
        try {
            return Double.parseDouble(numberToParse);
        } catch (NumberFormatException exception) {
            System.out.println(numberToParse + " is incorrect number, will be replaced by zero");
            return 0;
        }
    }
}