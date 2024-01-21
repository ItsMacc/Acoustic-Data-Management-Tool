package com.acousticdata.exceptions;

/**
 * An exception class representing an error related to illegal or invalid acoustic data.
 * This exception is thrown when an attempt is made to create or manipulate an AcousticDataSet
 * instance with data that violates the expected constraints.
 *
 * @author Aakarshan Khosla
 */
public class IllegalData extends AcousticDataException{
    public IllegalData(String message) {
        super(message);
    }
}