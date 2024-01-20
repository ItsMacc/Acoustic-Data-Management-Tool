package com.acousticdata.algorithms;


import com.acousticdata.AcousticDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Algorithms for all analysis are implemented here
 */
public class Algorithms {

    /**
     * A method to find the mean of all the frequencies in the dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @return mean of the frequencies in dataset of a particular date
     */
    public static double meanFrequency(List<AcousticDataSet> acousticDataSet, String date){
        return calculateMean(getDataForDate(acousticDataSet,"frequency",date));
    }

    /**
     * A method to find the mean of all the amplitudes in dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @return mean of the amplitudes in dataset of a particular date
     */
    public static double meanAmplitude(List<AcousticDataSet> acousticDataSet, String date){
        return calculateMean(getDataForDate(acousticDataSet,"amplitude",date));
    }

    /**
     * A method to find the mean of all the durations in dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @return mean of the durations in dataset of a particular date
     */
    public static double meanDuration(List<AcousticDataSet> acousticDataSet, String date){
        return calculateMean(getDataForDate(acousticDataSet,"duration",date));
    }


    /**
     * A method to find the median of all the frequencies in dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @return median of the frequencies in dataset of a particular date
     */
    public static double medianFrequency(List<AcousticDataSet> acousticDataSetList,String date){
        return calculateMedian(getDataForDate(acousticDataSetList,"frequency",date));
    }

    /**
     * A method to find the median of all the amplitudes in dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @return median of the amplitudes in dataset of a particular date
     */
    public static double medianAmplitude(List<AcousticDataSet> acousticDataSetList, String date){
        return calculateMedian(getDataForDate(acousticDataSetList,"amplitude",date));
    }

    public static double medianDuration(List<AcousticDataSet> acousticDataSetList,String date){
        return calculateMedian(getDataForDate(acousticDataSetList,"duration",date));
    }


    /**
     * A method to find the standard deviation of a dataset
     * @param dataSet the dataset that we want to analyze
     * @param data the type of data to retrieve ("frequency", "amplitude", "duration")
     * @return standard deviation of the dataset
     */
    public static double standardDeviation(List<AcousticDataSet> dataSet, String data) {
        List<Object> dataList = getData(dataSet, data);

        double mean = calculateMean(dataList);
        double sumOfSquaredDifferences = 0;

        for (Object value : dataList) {
            if (value instanceof Number) {
                double difference = ((Number) value).doubleValue() - mean;
                sumOfSquaredDifferences += difference * difference;
            } else {
                throw new IllegalArgumentException("Invalid data type for standard deviation calculation");
            }
        }

        double variance = sumOfSquaredDifferences / dataList.size();
        return Math.sqrt(variance);
    }


    //----------------Helper methods----------------------------

    /**
     * A method to get data of a specific type for a given date
     * @param dataSet the dataset that we want to analyze
     * @param data the type of data to retrieve ("timestamp", "frequency", "amplitude", "duration")
     * @param date the date whose data we want.
     * @return List of data for the specified type and date
     */
    private static List<Object> getDataForDate(List<AcousticDataSet> dataSet, String data, String date) {
        List<Object> dataList = new ArrayList<>();
        switch (data) {
            case "timestamp":
                for (AcousticDataSet d : dataSet) {
                    if (d.getTimestamp().equals(date)) {
                        dataList.add(d.getTimestamp());
                    }
                }
                break;
            case "frequency":
                for (AcousticDataSet d : dataSet) {
                    if (d.getTimestamp().equals(date)) {
                        dataList.add(d.getFrequency());
                    }
                }
                break;
            case "amplitude":
                for (AcousticDataSet d : dataSet) {
                    if (d.getTimestamp().equals(date)) {
                        dataList.add(d.getAmplitude());
                    }
                }
                break;
            case "duration":
                for (AcousticDataSet d : dataSet) {
                    if (d.getTimestamp().equals(date)) {
                        dataList.add(d.getDuration());
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid data type: " + data);
        }
        return dataList;
    }

    private static double calculateMean(List<Object> data) {
        double mean = 0;
        for (Object value : data) {
            if (value instanceof Number) {
                mean += ((Number) value).doubleValue();
            } else {
                throw new IllegalArgumentException("Invalid data type for mean calculation");
            }
        }
        return mean / data.size();
    }

    private static double calculateMedian(List<Object> data) {
        int size = data.size();
        if (size % 2 == 0) {
            // If the size is even, return the average of the middle two values
            int middle1 = (size / 2) - 1;
            int middle2 = size / 2;
            return (((Number) data.get(middle1)).doubleValue() + ((Number) data.get(middle2)).doubleValue()) / 2;
        } else {
            // If the size is odd, return the middle value
            return ((Number) data.get(size / 2)).doubleValue();
        }
    }

    private static List<Object> getData(List<AcousticDataSet> dataSet, String data) {
        List<Object> dataList = new ArrayList<>();
        switch (data) {
            case "timestamp":
                for (AcousticDataSet d : dataSet) {
                    dataList.add(d.getTimestamp());
                }
                break;
            case "frequency":
                for (AcousticDataSet d : dataSet) {
                    dataList.add(d.getFrequency());
                }
                break;
            case "amplitude":
                for (AcousticDataSet d : dataSet) {
                    dataList.add(d.getAmplitude());
                }
                break;
            case "duration":
                for (AcousticDataSet d : dataSet) {
                    dataList.add(d.getDuration());
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid data type: " + data);
        }
        return dataList;
    }
}