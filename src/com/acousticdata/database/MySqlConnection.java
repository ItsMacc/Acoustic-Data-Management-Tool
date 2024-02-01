package com.acousticdata.database;

import com.acousticdata.AcousticData;
import com.acousticdata.analysis.DataAnalyzer;
import com.acousticdata.exceptions.IllegalData;
import com.acousticdata.io.DataReader;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class MySqlConnection {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/{YOUR DATABASE NAME}";
        String username = "{YOUR USERNAME}";
        String password = "{YOUR PASSWORD}";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
            Statement statement = connection.createStatement();

            createAnalysisDataTable(statement);

            DataReader dataReader = new DataReader();
            List<AcousticData> acousticDataList = dataReader.readData("src/com/acousticdata/dataset.txt");

            //Analyze the data
            DataAnalyzer dataAnalyzer =  new DataAnalyzer(acousticDataList);
            Map<String,Map<String,Double>> data = dataAnalyzer.analyzeData();

            insertAnalyzedData(statement,data);


            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        catch (IllegalData e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a analysis data table in sql if not already created.
     * @param statement the statement that executes querry
     * @throws SQLException
     */
    private static void createAnalysisDataTable(Statement statement) throws SQLException {
        // Create a table to store analyzed data
        String createTableQuery = "CREATE TABLE IF NOT EXISTS AnalysisData ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "date VARCHAR(50) UNIQUE NOT NULL,"
                + "meanFrequency DOUBLE(6,3),"
                + "meanAmplitude DOUBLE(6,3),"
                + "meanDuration DOUBLE(6,3),"
                + "medianFrequency DOUBLE(6,3),"
                + "medianAmplitude DOUBLE(6,3),"
                + "medianDuration DOUBLE(6,3),"
                + "stdDevFrequency DOUBLE(6,3),"
                + "stdDevAmplitude DOUBLE(6,3),"
                + "stdDevDuration DOUBLE(6,3),"
                + "energy DOUBLE(8,3)"
                + ");";
        statement.executeUpdate(createTableQuery);
    }

    private static void insertAnalyzedData(Statement statement, Map<String, Map<String, Double>> analyzedData) throws SQLException {
        try {
            for (Map.Entry<String, Map<String, Double>> entry : analyzedData.entrySet()) {
                String date = entry.getKey();
                Map<String, Double> analysis = entry.getValue();

                String insertQuery = "INSERT INTO AnalysisData (date, meanFrequency, meanAmplitude, meanDuration, " +
                        "medianFrequency, medianAmplitude, medianDuration, " +
                        "stdDevFrequency, stdDevAmplitude, stdDevDuration, energy) " +
                        "VALUES ('" + date + "', " +
                        analysis.get("mean (Frequency)") + ", " +
                        analysis.get("mean (Amplitude)") + ", " +
                        analysis.get("mean (Duration)") + ", " +
                        analysis.get("median (Frequency)") + ", " +
                        analysis.get("median (Amplitude)") + ", " +
                        analysis.get("median (Duration)") + ", " +
                        analysis.get("standard deviation (frequency)") + ", " +
                        analysis.get("standard deviation (amplitude)") + ", " +
                        analysis.get("standard deviation (duration)") + ", " +
                        analysis.get("Energy of signal") + ")";

                statement.executeUpdate(insertQuery);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}