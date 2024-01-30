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
     * @throws FileNotFoundException if the specified file is not found.
     * @throws IllegalData if there is an issue with the format or content of the data.
     */
    public List<AcousticDataSet> readData(String filePath) throws FileNotFoundException, IllegalData {
        List<AcousticDataSet> dataSet = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                AcousticDataSet data = parseData(line);
                dataSet.add(data);
            }

        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        catch (IllegalData e) {
            throw new IllegalData("Error parsing data: " + e.getMessage());
        }

        return dataSet;
    }

    /**
     * Parses a line of data into an AcousticDataSet object.
     *
     * @param line the line of data containing key-value pairs.
     * @return an AcousticDataSet object.
     * @throws IllegalData if there is an issue with the format or content of the data.
     */
    private static AcousticDataSet parseData(String line) throws IllegalData {
        String[] keyValuePairs = line.split(", ");

        String timestamp = "";
        int frequency = 0;
        int amplitude = 0;
        double duration = 0.0;
        String oceanLevel = "";
        double temperature = 0.0;

        for (String pair : keyValuePairs) {
            String[] pairComponents = pair.split(":");
            String key = pairComponents[0].trim();
            String value = pairComponents[1].trim();

            switch (key) {
                case "timestamp":
                    timestamp = value;
                    break;
                case "frequency":
                    frequency = Integer.parseInt(value);
                    break;
                case "amplitude":
                    amplitude = Integer.parseInt(value);
                    break;
                case "duration":
                    duration = Double.parseDouble(value);
                    break;
                case "ocean_level":
                    oceanLevel = value;
                    break;
                case "temperature":
                    temperature = Double.parseDouble(value);
                    break;
                default:
                    throw new IllegalData("Unknown key: " + key);
            }
        }

        if (timestamp.isEmpty() || frequency == 0 || amplitude == 0 || duration == 0.0 || oceanLevel.isEmpty() || temperature == 0.0) {
            throw new IllegalData("Missing key or value");
        }

        return new AcousticDataSet(timestamp, frequency, amplitude, duration, oceanLevel, temperature);
    }

    /**
     * Extracts the value associated with a key from a key-value pair.
     *
     * @param pair the key-value pair.
     * @param key  the key to extract the value for.
     * @return the value associated with the key.
     */
    private static String extractValue(String pair, String key) {
        String[] keyValue = pair.split(":");
        if (keyValue.length == 2 && keyValue[0].trim().equals(key)) {
            return keyValue[1].trim();
        }
        return "";
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