package com.acousticdata;

import com.acousticdata.io.DataReader;

import java.util.List;

public class AcousticDataProcessor {
    public static void main(String[] args) {

        DataReader dataReader = new DataReader();
        List<AcousticDataSet> dataSet = dataReader.readData("src/com/acousticdata/dataset.txt");

        System.out.println(dataSet);
    }
}