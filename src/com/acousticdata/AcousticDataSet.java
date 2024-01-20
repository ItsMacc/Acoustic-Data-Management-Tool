package com.acousticdata;

import java.util.Comparator;

/**
 * A class to represent acoustic data
 * @author Aakarshan Khosla
 */

public class AcousticDataSet {
    //Instance variables
    private String timestamp;
    private int frequency;
    private int amplitude;
    private double duration;

    //Constructor
    public AcousticDataSet(String timestamp, int frequency, int amplitude, double duration){
        this.timestamp = timestamp;
        this.frequency = frequency;
        this.amplitude = amplitude;
        this.duration = duration;
    }

    //Getters
    public String getTimestamp() {
        return timestamp;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getAmplitude() {
        return amplitude;
    }

    public double getDuration() {
        return duration;
    }


    //Setters
    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setAmplitude(int amplitude) {
        this.amplitude = amplitude;
    }

    //Other methods
    @Override
    public String toString(){
        return "AcousticDataSet {\n" +
                "timestamp: " + timestamp + "\n" +
                "frequency (hz): " + frequency + "\n" +
                "amplitude (m): " + amplitude + "\n" +
                "duration (s): " + duration + "\n" +"}";
    }

    //Comparators
    public static Comparator<AcousticDataSet> BY_FREQUENCY =
            (AcousticDataSet a1, AcousticDataSet a2) -> Double.compare(a1.frequency,a2.frequency);

    public static Comparator<AcousticDataSet> BY_AMPLITUDE =
            (AcousticDataSet a1, AcousticDataSet a2) -> Double.compare(a1.amplitude,a2.amplitude);

    public static Comparator<AcousticDataSet> BY_DURATION =
            (AcousticDataSet a1, AcousticDataSet a2) -> Double.compare(a1.duration,a2.duration);

    public static Comparator<AcousticDataSet> BY_TIMESTAMP =
            (AcousticDataSet a1, AcousticDataSet a2) -> a1.timestamp.compareTo(a2.timestamp);
}