package com.acousticdata.exceptions;

/**
 * An exception class representing an error related to an illegal or invalid file format.
 * This exception is thrown when an attempt is made to read acoustic data from a file
 * with a format that does not adhere to the expected structure.
 *
 * @author Aakarshan Khosla
 */
public class IllegalFileFormat extends AcousticDataException {

    public IllegalFileFormat(String message){
        super(message);
    }
}