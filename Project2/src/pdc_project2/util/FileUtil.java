/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pdc_project2.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tai Zhang 17970814
 */
public class FileUtil {

    public static List<String> readFileString(String file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            List<String> lines = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            
            bufferedReader.close();
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void writeFile(String file, List<String> lines) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                bufferedWriter.write(line + System.lineSeparator());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
