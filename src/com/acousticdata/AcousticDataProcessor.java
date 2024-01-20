package com.acousticdata;

import com.acousticdata.analysis.DataAnalyzer;
import com.acousticdata.io.DataReader;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AcousticDataProcessor {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Acoustic Data Processor!");

        // Get file path from user input
        String filePath = getFilePathFromUser();
        String date = getDateFromUser();

        // Fetch and analyze data
        analyzeAcousticData(filePath,date);

        // Close the scanner to avoid resource leaks
        scanner.close();
    }

    private static String getFilePathFromUser() {
        System.out.print("Please enter the path to the data file (e.g., ../../../src/com/acousticdata/dataset.txt): ");
        return scanner.nextLine().trim();
    }

    private static String getDateFromUser(){
        System.out.print("Please enter the date of the records you want: ");
        return scanner.nextLine().trim();
    }

    private static void analyzeAcousticData(String filePath,String date) {
        try {
            DataReader dataReader = new DataReader();
            List<AcousticDataSet> dataSet = dataReader.readData(filePath);

            System.out.println("\nFetching and analyzing results...\n");

            DataAnalyzer dataAnalyzer = new DataAnalyzer(dataSet);
            Map<String, Double> analyzedData = dataAnalyzer.analyzeData(date);

            System.out.println("Analysis Results for the specified date :"+ date+"\n");
            System.out.println(analyzedData);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}