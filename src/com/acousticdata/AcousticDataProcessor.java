package com.acousticdata;

import com.acousticdata.analysis.DataAnalyzer;
import com.acousticdata.io.DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AcousticDataProcessor {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Acoustic Data Processor!\n");

        //Get input from user
        String filePath = getFilePathFromUser();
        String date = getDateFromUser();

        //Read the data
        DataReader dataReader = new DataReader();
        List<AcousticDataSet> acousticDataSetList = dataReader.readData(filePath);

        //Analyze the data
        DataAnalyzer dataAnalyzer =  new DataAnalyzer(acousticDataSetList);
        dataAnalyzer.analyzeData(date);
    }

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

    private static String getDateFromUser(){
        System.out.print("Enter the date whose records you want to analyze " +
                "(Make sure the date is exactly matching with your dataset): ");
        String date = scanner.nextLine().trim();

        return date;
    }
}