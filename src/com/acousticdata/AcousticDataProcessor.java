package com.acousticdata;

import com.acousticdata.analysis.DataAnalyzer;
import com.acousticdata.io.DataReader;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AcousticDataProcessor {
    public static void main(String[] args) {

        for(String s:args){
            System.out.println(args);
        }
        DataReader dataReader = new DataReader();
        List<AcousticDataSet> dataSet = dataReader.readData("src/com/acousticdata/dataset.txt");

        DataAnalyzer dataAnalyzer = new DataAnalyzer(dataSet);
        Map<String, Double> analyzedData = dataAnalyzer.analyzeData("11th January 2023");
        System.out.println("Analysis Results: " + analyzedData);
    }
}