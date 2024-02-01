package com.acousticdata.analysis;

import com.acousticdata.AcousticData;
import com.acousticdata.algorithms.Algorithms;
import com.acousticdata.exceptions.InvalidDateForAnalysis;

import java.util.*;

/**
 * A class to analyze and process acoustic data.
 */
public class DataAnalyzer {

    private List<AcousticData> acousticDataList;

    public DataAnalyzer(List<AcousticData> acousticDataList){
        this.acousticDataList = acousticDataList;
    }


    /**
     * A method to analyze the data of all dates
     * @return a map of the following strucutre {date : {analysis:value} }
     */
    public Map<String, Map<String,Double>> analyzeData(){
        Map<String, List<AcousticData>> acousticData = new LinkedHashMap<>();
        Map<String, Map<String,Double>> databaseRecord = new LinkedHashMap<>();

        for (AcousticData dataSet: acousticDataList){
            String date = dataSet.getTimestamp();

            acousticData.putIfAbsent(date, new ArrayList<>());
            acousticData.get(date).add(dataSet);
        }

        Set<String> allDates = acousticData.keySet();

        for(String date: allDates){
            databaseRecord.put(date,analyzeData(date));
        }
        return databaseRecord;
    }

    /**
     * A method to analyze data of a particular date.
     * @param date the date whose data we want
     * @return key value pairs of analysis
     */
    public Map<String, Double> analyzeData(String date) {
        Map<String, Double> data = new LinkedHashMap<>();

        try {
            data.put("mean (Frequency)",Algorithms.meanFrequency(acousticDataList,date));
            data.put("mean (Amplitude)",Algorithms.meanAmplitude(acousticDataList,date));
            data.put("mean (Duration)",Algorithms.meanDuration(acousticDataList,date));
            data.put("mean (Temperature)",Algorithms.meanTemperature(acousticDataList,date));

            data.put("median (Frequency)",Algorithms.medianFrequency(acousticDataList,date));
            data.put("median (Amplitude)",Algorithms.medianAmplitude(acousticDataList,date));
            data.put("median (Duration)",Algorithms.medianDuration(acousticDataList,date));
            data.put("median (Temperature)",Algorithms.medianTemperature(acousticDataList,date));

            data.put("standard deviation (frequency)",Algorithms.standardDeviation(acousticDataList,"frequency"));
            data.put("standard deviation (amplitude)",Algorithms.standardDeviation(acousticDataList,"amplitude"));
            data.put("standard deviation (duration)",Algorithms.standardDeviation(acousticDataList,"duration"));
            data.put("standard deviation (temperature)",Algorithms.standardDeviation(acousticDataList,"temperature"));

            data.put("Energy of signal",Algorithms.calculateEnergy(acousticDataList,date));

            return data;

        } catch (InvalidDateForAnalysis e) {
            e.printStackTrace();
        }
        //If index is out of bounds, this means list is empty since there is no data for the current timestamp
        catch (IndexOutOfBoundsException e){
            System.err.println("Illegal Date: No date found");
        }
        return data;
    }
}