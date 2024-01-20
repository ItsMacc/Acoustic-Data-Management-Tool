package com.acousticdata.io;

import com.acousticdata.AcousticDataSet;
import com.acousticdata.exceptions.IllegalData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class to read acoustic data from a file and convert it into AcousticDataSet type.
 * Assumes the data in the file is formatted as key-value pairs separated by commas.
 * Example data line: "timestamp: 11th January 2023, frequency: 120hz, amplitude: 20hz, duration: 0.3 sec"
 * Each line represents a single AcousticDataSet.
 */
public class DataReader {

    /**
     * Reads the data from a file and converts it into AcousticDataSet type.
     *
     * @param filePath the path of the file.
     * @return a list of acoustic data sets.
     */
    public List<AcousticDataSet> readData(String filePath) {
        List<AcousticDataSet> dataSet = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                AcousticDataSet data = parseData(line);
                dataSet.add(data);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalData e) {
            e.printStackTrace();
        }

        return dataSet;
    }

    /**
     * Parses a line of data into an AcousticDataSet object.
     *
     * @param line the line of data containing key-value pairs.
     * @return an AcousticDataSet object.
     */
    private static AcousticDataSet parseData(String line) throws IllegalData {
        String[] keyValuePairs = line.split(", ");

        String timestamp = extractValue(keyValuePairs[0], "timestamp");
        int frequency = extractIntValue(keyValuePairs[1], "frequency");
        int amplitude = extractIntValue(keyValuePairs[2], "amplitude");
        double duration = extractDoubleValue(keyValuePairs[3], "duration");
        String oceanLevel = extractValue(keyValuePairs[4], "ocean_level");
        double temperature = extractDoubleValue(keyValuePairs[5],"temperature");


        return new AcousticDataSet(timestamp, frequency, amplitude, duration,oceanLevel,temperature);
    }

    /**
     * Extracts the value associated with a key from a key-value pair.
     *
     * @param pair the key-value pair.
     * @param key  the key to extract the value for.
     * @return the value associated with the key.
     */
    private static String extractValue(String pair, String key) {
        return pair.split(":")[1].trim();
    }

    /**
     * Extracts an integer value associated with a key from a key-value pair.
     *
     * @param pair the key-value pair.
     * @param key  the key to extract the integer value for.
     * @return the integer value associated with the key.
     */
    private static int extractIntValue(String pair, String key) {
        String val = extractValue(pair, key);
        return Integer.parseInt(val);
    }

    /**
     * Extracts a double value associated with a key from a key-value pair.
     *
     * @param pair the key-value pair.
     * @param key  the key to extract the double value for.
     * @return the double value associated with the key.
     */
    private static double extractDoubleValue(String pair, String key) {
        String val = extractValue(pair, key);
        return Double.parseDouble(val);
    }
}