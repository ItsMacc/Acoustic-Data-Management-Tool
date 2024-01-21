package com.acousticdata.exceptions;

/**
 * An exception class representing a base exception for acoustic data-related issues.
 * This class is extended by more specific exception types for detailed error handling.
 *
 * @author Aakarshan Khosla
 */
public class AcousticDataException extends Exception{
    public AcousticDataException(String s) {
        super(s);
    }
}