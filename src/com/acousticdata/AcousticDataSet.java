package com.acousticdata;

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

    public static void main(String[] args) {
        AcousticDataSet a = new AcousticDataSet("12th jan",10,20,30);
        System.out.println(a);
    }
}