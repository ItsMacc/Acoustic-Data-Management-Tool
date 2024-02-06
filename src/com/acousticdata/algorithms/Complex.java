package com.acousticdata.algorithms;

/**
 * A class to represent complex numbers
 * 'real' represents the real part
 * 'imaginary' represents the imaginary part
 */
public class Complex {
    //Instance variables
    private double real;
    private double imaginary;

    //Constructors

    /**
     * A Default constructor that sets real as well as imaginary part to 0
     */
    public Complex(){
        this.real = 0;
        this.imaginary = 0;
    }

    /**
     * A constructor to create a complex number
     * @param real the real part of the number
     * @param imaginary the imaginary part of the number
     */
    public Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    //Other methods

    /**
     * Adding to complex numbers
     * @param other the complex number to be added
     * @return a complex number representing the sum
     */
    public Complex add(Complex other){
        return new Complex(this.real+other.real,this.imaginary+other.imaginary);
    }

    public Complex multiply(Complex other){
        //(a+ib)*(c+id) = ac + aid + cib + i^2 bd
        // = ac + i(ad+bc) + (-1)bd = ac + i(ad+bc) -bd
        //real part: ac - bd
        //imaginary part = ad+bc

        double realPart = this.real*other.real - this.imaginary* other.imaginary;
        double imaginaryPart = this.real* other.real + this.imaginary*other.imaginary;

        return new Complex(realPart,imaginaryPart);
    }

    @Override
    public String toString(){
        return "("+real+" , "+imaginary+"i"+")";
    }
}
