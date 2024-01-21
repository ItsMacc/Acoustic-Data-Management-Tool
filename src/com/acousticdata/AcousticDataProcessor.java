package com.acousticdata;

import com.acousticdata.analysis.DataAnalyzer;
import com.acousticdata.exceptions.IllegalData;
import com.acousticdata.exceptions.IllegalFileFormat;
import com.acousticdata.io.DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AcousticDataProcessor {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IllegalFileFormat {
        System.out.println("Welcome to the Acoustic Data Processor!\n");

        //create DataReader Object
        DataReader dataReader = new DataReader();

        //Get input filepath from user and check if file is in correct format
        String filePath = getFilePathFromUser();
        List<AcousticDataSet> acousticDataSetList = dataReader.readData(filePath);

        //Get date from user
        String date = getDateFromUser();

        //Analyze the data
        DataAnalyzer dataAnalyzer =  new DataAnalyzer(acousticDataSetList);
        System.out.println(dataAnalyzer.analyzeData(date));
    }

    /**
     * A method to check and return an existing file by user
     * @return the file path entered by user
     */
    private static String getFilePathFromUser(){
        System.out.print("Please enter the correct file path: "); //src/com/acousticdata/dataset.txt
        String filePath = scanner.nextLine().trim();

        File file = new File(filePath);
        System.out.println("Checking for all files with that name..");
        if (file.exists()){
            System.out.println("File found!\n");
            return filePath;
        }
        return getFilePathFromUser();
    }

    /**
     * A method to get the date from the user
     * @return the date entered by user
     */
    private static String getDateFromUser(){
        System.out.print("Enter the date whose records you want to analyze " +
                "(Make sure the date is exactly matching with your dataset): ");
        String date = scanner.nextLine().trim();

        return date;
    }
}