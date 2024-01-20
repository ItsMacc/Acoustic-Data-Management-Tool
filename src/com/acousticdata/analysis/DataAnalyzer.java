package com.acousticdata.analysis;

import com.acousticdata.AcousticDataSet;
import com.acousticdata.algorithms.Algorithms;

import java.util.*;

/**
 * A class to analyze and process acoustic data.
 */
public class DataAnalyzer {

    private List<AcousticDataSet> acousticDataSetList;

    public DataAnalyzer(List<AcousticDataSet> acousticDataSetList){
        this.acousticDataSetList = acousticDataSetList;
    }


    /**
     * A method to analyze data of a particular date.
     * @param date the date whose data we want
     * @return key value pairs of analysis
     */
    public Map<String , Double> analyzeData(String date){
        Map<String, Double> data = new LinkedHashMap<>();

        data.put("mean (Frequency)",Algorithms.meanFrequency(acousticDataSetList,date));
        data.put("mean (Amplitude)",Algorithms.meanAmplitude(acousticDataSetList,date));
        data.put("mean (Duration)",Algorithms.meanDuration(acousticDataSetList,date));
        data.put("mean (Temperature)",Algorithms.meanTemperature(acousticDataSetList,date));

        data.put("median (Frequency)",Algorithms.medianFrequency(acousticDataSetList,date));
        data.put("median (Amplitude)",Algorithms.medianAmplitude(acousticDataSetList,date));
        data.put("median (Duration)",Algorithms.medianDuration(acousticDataSetList,date));
        data.put("median (Temperature)",Algorithms.medianTemperature(acousticDataSetList,date));

        data.put("standard deviation (frequency)",Algorithms.standardDeviation(acousticDataSetList,"frequency"));
        data.put("standard deviation (amplitude)",Algorithms.standardDeviation(acousticDataSetList,"amplitude"));
        data.put("standard deviation (duration)",Algorithms.standardDeviation(acousticDataSetList,"duration"));
        data.put("standard deviation (temperature)",Algorithms.standardDeviation(acousticDataSetList,"temperature"));

        data.put("Energy of signal",Algorithms.calculateEnergy(acousticDataSetList,date));

        return data;
    }
}