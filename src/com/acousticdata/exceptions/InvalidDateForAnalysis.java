package com.acousticdata.exceptions;

/**
 * An exception class representing an error related to an invalid date for analysis.
 * This exception is thrown when an attempt is made to analyze acoustic data for a date
 * that does not exist in the available dataset.
 *
 * @author Aakarshan Khosla
 */
public class InvalidDateForAnalysis extends AcousticDataException{

    public InvalidDateForAnalysis(String message){
        super(message);
    }
}