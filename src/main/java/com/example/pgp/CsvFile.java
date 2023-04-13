//William Vaughan
package com.example.pgp;
/*
 * Updated 4/13/23
 * Class allows for the reading and writing of a four column csv file.
 * Is meant to be started when program starts as to allow access to data
 * for verifying PINs and editing fuel amounts.
 * William Vaughan
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CsvFile {
    //defines number of columns in array
    private static final int NUM_COLUMNS = 4;
    //filepath for csv file
    private String filePath;
    //2d array for data pulled from csv
    private String[][] data;

    public CsvFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method reads raw data from csv file and puts it into internal class data array
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
     *
     * @param data 2d array !!Must have four entries per row!!
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

    /**
     * Same as writeData but writes from the internal array in the class
     */
    public void writeDataInternal() {
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

    /**
     * Adds a new row with four entries to a 2d array
     *
     * @param newRow String array with four entries
     */
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

    /**
     * Receives a String value, checks against column 0 of array,
     * and removes row if value matches.
     *
     * @param valueToRemove String Value to check against array
     */
    public void removeRow(String valueToRemove) {
        for (int i = 0; i < data.length; i++) {
            if (data[i][0].equals(valueToRemove)) {
                // Shift the rows above the matched row to fill the gap
                for (int j = i; j < data.length - 1; j++) {
                    data[j] = data[j + 1];
                }
                // Resize the data array to exclude the removed row
                data = Arrays.copyOf(data, data.length - 1);

                System.out.println("Line with " + valueToRemove + " removed.");
                return;
            }
        }

        System.out.println("No line found with " + valueToRemove);
    }
}
