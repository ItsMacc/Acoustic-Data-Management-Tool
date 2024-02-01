package com.acousticdata.algorithms;


import com.acousticdata.AcousticData;
import com.acousticdata.exceptions.InvalidDateForAnalysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithms for all analysis are implemented here
 */
public class Algorithms {

    /**
     * A method to find the mean of all the frequencies in the dataset of a particular date
     * @param acousticData the dataset that we want to analyze
     * @param date the date whose data we want.
     * @throws InvalidDateForAnalysis if no record exists for date
     * @return mean of the frequencies in dataset of a particular date
     */
    public static double meanFrequency(List<AcousticData> acousticData, String date) throws InvalidDateForAnalysis{
        return calculateMean(getDataForDate(acousticData,"frequency",date));
    }

    /**
     * A method to find the mean of all the amplitudes in dataset of a particular date
     * @param acousticData the dataset that we want to analyze
     * @param date the date whose data we want.
     * @throws InvalidDateForAnalysis if no record exists for date
     * @return mean of the amplitudes in dataset of a particular date
     */
    public static double meanAmplitude(List<AcousticData> acousticData, String date) throws InvalidDateForAnalysis{
        return calculateMean(getDataForDate(acousticData,"amplitude",date));
    }

    /**
     * A method to find the mean of all the durations in dataset of a particular date
     * @param acousticData the dataset that we want to analyze
     * @param date the date whose data we want.
     * @throws InvalidDateForAnalysis if no record exists for date
     * @return mean of the durations in dataset of a particular date
     */
    public static double meanDuration(List<AcousticData> acousticData, String date) throws InvalidDateForAnalysis{
        return calculateMean(getDataForDate(acousticData,"duration",date));
    }

    /**
     * A method to find the mean of all the temperatures in dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @throws InvalidDateForAnalysis if no record exists for date
     * @return mean of the temperatures in dataset of a particular date
     */
    public static double meanTemperature(List<AcousticData> acousticDataList, String date) throws InvalidDateForAnalysis{
        return calculateMean(getDataForDate(acousticDataList,"temperature",date));
    }

    /**
     * A method to find the median of all the frequencies in dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @throws InvalidDateForAnalysis if no record exists for date
     * @return median of the frequencies in dataset of a particular date
     */
    public static double medianFrequency(List<AcousticData> acousticDataList, String date) throws InvalidDateForAnalysis{
        return calculateMedian(getDataForDate(acousticDataList,"frequency",date));
    }

    /**
     * A method to find the median of all the amplitudes in dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @throws InvalidDateForAnalysis if no record exists for date
     * @return median of the amplitudes in dataset of a particular date
     */
    public static double medianAmplitude(List<AcousticData> acousticDataList, String date) throws InvalidDateForAnalysis{
        return calculateMedian(getDataForDate(acousticDataList,"amplitude",date));
    }

    /**
     * A method to find the median of all the duration in dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @throws InvalidDateForAnalysis if no record exists for date
     * @return median of the duration in dataset of a particular date
     */
    public static double medianDuration(List<AcousticData> acousticDataList, String date) throws InvalidDateForAnalysis{
        return calculateMedian(getDataForDate(acousticDataList,"duration",date));
    }

    /**
     * A method to find the median of all the temperature in dataset of a particular date
     * @param acousticDataSet the dataset that we want to analyze
     * @param date the date whose data we want.
     * @throws InvalidDateForAnalysis if no record exists for date
     * @return median of the temperature in dataset of a particular date
     */
    public static double medianTemperature(List<AcousticData> acousticDataList, String date) throws InvalidDateForAnalysis{
        return calculateMedian(getDataForDate(acousticDataList,"temperature",date));
    }

    /**
     * Calculate the energy of acoustic signal of data
     * @param data data whose energy we have to calculate
     * @throws InvalidDateForAnalysis if no record exists for date
     * @return energy of acoustic signal of data
     */
    public static double calculateEnergy(List<AcousticData> acousticDataList, String date) throws InvalidDateForAnalysis{
        List<Object> data = getDataForDate(acousticDataList,"amplitude",date);
        double energy = 0;
        for(Object amplitude : data){
            energy +=  ((int)amplitude * (int)amplitude);
        }
        return energy;
    }
    
    //----------------Helper methods----------------------------

    /**
     * A method to get data of a specific type for a given date
     * @param dataSet the dataset that we want to analyze
     * @param data the type of data to retrieve ("timestamp", "frequency", "amplitude", "duration")
     * @param date the date whose data we want.
     * @return List of data for the specified type and date
     */
    private static List<Object> getDataForDate(List<AcousticData> dataSet, String data, String date) throws InvalidDateForAnalysis{
        List<Object> dataList = new ArrayList<>();
        switch (data) {
            case "timestamp":
                for (AcousticData d : dataSet) {
                    if (d.getTimestamp().equals(date)) {
                        dataList.add(d.getTimestamp());
                    }
                }
                if(dataList.size()==0){
                    throw new InvalidDateForAnalysis("Invalid Date: No data found for data "+date);
                }
                break;
            case "frequency":
                for (AcousticData d : dataSet) {
                    if (d.getTimestamp().equals(date)) {
                        dataList.add(d.getFrequency());
                    }
                }
                break;
            case "amplitude":
                for (AcousticData d : dataSet) {
                    if (d.getTimestamp().equals(date)) {
                        dataList.add(d.getAmplitude());
                    }
                }
                break;
            case "duration":
                for (AcousticData d : dataSet) {
                    if (d.getTimestamp().equals(date)) {
                        dataList.add(d.getDuration());
                    }
                }
                break;
            case "temperature":
                for (AcousticData d : dataSet) {
                    if (d.getTimestamp().equals(date)) {
                        dataList.add(d.getTemperature());
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid data type: " + data);
        }
        return dataList;
    }

    /**
     * Calculate the mean of data
     * @param data data whose mean we have to calculate
     * @return mean of data
     */
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

    /**
     * Calculate the median of data
     * @param data data whose median we have to calculate
     * @return median of data
     */
    private static double calculateMedian(List<Object> data){
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

    /**
     * A method to get data of a specific type
     * @param dataSet the dataset that we want to analyze
     * @param data the values of data to retrieve ("timestamp", "frequency", "amplitude", "duration")
     * @return List of data for the specified type
     */
    private static List<Object> getData(List<AcousticData> dataSet, String data) {
        List<Object> dataList = new ArrayList<>();
        switch (data) {
            case "timestamp":
                for (AcousticData d : dataSet) {
                    dataList.add(d.getTimestamp());
                }
                break;
            case "frequency":
                for (AcousticData d : dataSet) {
                    dataList.add(d.getFrequency());
                }
                break;
            case "amplitude":
                for (AcousticData d : dataSet) {
                    dataList.add(d.getAmplitude());
                }
                break;
            case "duration":
                for (AcousticData d : dataSet) {
                    dataList.add(d.getDuration());
                }
                break;
            case "temperature":
                for (AcousticData d : dataSet) {
                    dataList.add(d.getTemperature());
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid data type: " + data);
        }
        return dataList;
    }

    /**
     * A method to find the standard deviation of a dataset
     * @param dataSet the dataset that we want to analyze
     * @param data the type of data to retrieve ("frequency", "amplitude", "duration")
     * @return standard deviation of the dataset
     */
    public static double standardDeviation(List<AcousticData> dataSet, String data) {
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
}