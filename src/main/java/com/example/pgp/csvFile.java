package com.example.pgp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class allows for the reading  and writing of a four column csv file.
 * Is meant to be started when program starts as to allow access to data
 * for verifying PINs and editing fuel amounts.
 */
public class csvFile {
    private static final int NUM_COLUMNS = 4; //defines number of columns in array
    private String filePath;                  //filepath for csv file
    private String[][] data;                  //2d array for data pulled from csv

    public csvFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method reads raw data from csv file and puts it into data array
     */
    public void readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int lineCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
            }
            data = new String[lineCount][NUM_COLUMNS];
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int lineCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(",");
                for (int i = 0; i < NUM_COLUMNS; i++) {
                    data[lineCount][i] = lineData[i];
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * takes a formatted 2d array and writes it to a csv file
     * @param data 2d array !!Must have three entries per row!!
     */
    public void writeData(String[][] data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String[] lineData : data) {
                for (int i = 0; i < NUM_COLUMNS; i++) {
                    writer.write(lineData[i]);
                    if (i < NUM_COLUMNS - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[][] getData() {
        return data;
    }

    public void addRow(String[] newRow) {
        if (newRow.length != NUM_COLUMNS) {
            throw new IllegalArgumentException("New row must have " + NUM_COLUMNS + " columns");
        }

        int newLength = data.length + 1;
        String[][] newData = new String[newLength][NUM_COLUMNS];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        newData[newLength - 1] = newRow;
        data = newData;
    }
}
