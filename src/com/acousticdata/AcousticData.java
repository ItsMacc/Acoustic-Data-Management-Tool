package com.acousticdata;

import com.acousticdata.exceptions.IllegalData;

import java.util.Comparator;

/**
 * A class to represent acoustic data
 * @author Aakarshan Khosla
 */

public class AcousticData {
    //Instance variables
    private String timestamp;
    private int frequency;
    private int amplitude;
    private double duration;
    private String oceanLevel;
    private double temperature;
    //Constructor
    public AcousticData(String timestamp, int frequency, int amplitude, double duration, String oceanLevel, double temperature) throws IllegalData {
        if(timestamp == null || timestamp.isBlank()){
            throw new IllegalData("Illegal Data: "+timestamp);
        }
        if(frequency<0){
            throw new IllegalData("Illegal Data: "+frequency);
        }
        if(amplitude<0){
            throw new IllegalData("Illegal Data: "+amplitude);
        }
        if(duration<=0){
            throw new IllegalData("Illegal Data: "+duration);
        }
        if(oceanLevel==null || oceanLevel.isBlank()){
            throw new IllegalData("Illegal Data: "+oceanLevel);
        }
        this.timestamp = timestamp;
        this.frequency = frequency;
        this.amplitude = amplitude;
        this.duration = duration;
        this.oceanLevel = oceanLevel;
        this.temperature = temperature;
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

    public double getTemperature() {
        return temperature;
    }

    public String getOceanLevel(){
        return oceanLevel;
    }

    //Setters
    public void setDuration(double duration) throws IllegalData {
        if (duration<=0){
            throw new IllegalData("Illegal Data: Cannot set duration to "+duration);
        }
        this.duration = duration;
    }

    public void setFrequency(int frequency) throws IllegalData {
        if (frequency<0){
            throw new IllegalData("Illegal Data: Cannot set frequency to "+frequency);
        }
        this.frequency = frequency;
    }

    public void setAmplitude(int amplitude) throws IllegalData{
        if (amplitude<0){
            throw new IllegalData("Illegal Data: Cannot set amplitude to "+frequency);
        }
        this.amplitude = amplitude;
    }

    public void setOceanLevel(String oceanLevel) throws IllegalData{
        if(oceanLevel==null || oceanLevel.isBlank()){
            throw new IllegalData("Illegal Data: "+oceanLevel);
        }
        this.oceanLevel = oceanLevel;
    }

    public void setTemperature(double temperature) throws IllegalData{
        this.temperature = temperature;
    }

    //Other methods
    @Override
    public String toString(){
        return "AcousticData {\n" +
                "timestamp: " + timestamp + "\n" +
                "frequency (hz): " + frequency + "\n" +
                "amplitude (m): " + amplitude + "\n" +
                "duration (s): " + duration + "\n" +
                "ocean level: " + duration + "\n" +
                "temperature (°C): " + duration + "}";
    }

    //Comparators
    public static Comparator<AcousticData> BY_FREQUENCY =
            (AcousticData a1, AcousticData a2) -> Double.compare(a1.frequency,a2.frequency);

    public static Comparator<AcousticData> BY_AMPLITUDE =
            (AcousticData a1, AcousticData a2) -> Double.compare(a1.amplitude,a2.amplitude);

    public static Comparator<AcousticData> BY_DURATION =
            (AcousticData a1, AcousticData a2) -> Double.compare(a1.duration,a2.duration);

    public static Comparator<AcousticData> BY_TIMESTAMP =
            (AcousticData a1, AcousticData a2) -> a1.timestamp.compareTo(a2.timestamp);

    public static Comparator<AcousticData> BY_TEMPERATURE =
            (AcousticData a1, AcousticData a2) -> Double.compare(a1.temperature,a2.temperature);

}