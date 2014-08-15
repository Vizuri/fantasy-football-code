package com.vizuri.fantasy.entity.manager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The Class DataUtil.
 */
public class DataUtil {
	private static transient Logger log = Logger.getLogger(DataUtil.class);
	
    public static List<String[]> createRecords(File dataFile) {
        return DataUtil.createRecords(dataFile, ",");
    }
	
	public static List<String[]> createRecords(File dataFile, String separator) {
        List<String[]> dataRecords;
        InputStream inputStream = null;
        InputStreamReader in = null;
        BufferedReader bReader = null;
        boolean firstRow = true;
        try {
            inputStream = new FileInputStream(dataFile);
            in = new InputStreamReader(inputStream);
            bReader = new BufferedReader(in);
            List<String> records = new ArrayList<String>();
            String record;
            while ((record = bReader.readLine()) != null) {
                if (!firstRow) { records.add(record); }
                firstRow = false;
            }
            dataRecords = new ArrayList<String[]>(records.size());
            for (String dataRecord : records) {
                dataRecords.add(dataRecord.split(separator, -1));
            }
        } catch (Exception e) {
            String message = "Error Initializing DB : " + e.getMessage();
            log.error(message, e);
            throw new RuntimeException(message);
        } finally {
            try {
                if (bReader != null) {
                    bReader.close();
                }
                if (in != null) {
                    in.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("Error closing streams : " + e.getMessage());
            }
        }
        return dataRecords;
    }
	
	public static void removeQuotes(List<String[]> dataRecords) {
        for (String[] dataRecord : dataRecords) {
            int index = 0;
            for (String value : dataRecord) {
                if (value.startsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }
                dataRecord[index] = value;
                index++;
            }
        }
    }
}
