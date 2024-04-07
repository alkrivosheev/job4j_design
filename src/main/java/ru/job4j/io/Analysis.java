package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        StringJoiner writeLog = new StringJoiner("");
        try (BufferedReader input = new BufferedReader(new FileReader(source))) {
            boolean servOnline = true;
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                if (!"".equals(line)) {
                    String[] state = line.split(" ", 2);
                    if (servOnline && "500".equals(state[0]) | "400".equals(state[0])) {
                        servOnline = false;
                        writeLog.add(state[1] + ";");
                    } else if (!servOnline && "300".equals(state[0]) | "200".equals(state[0])) {
                        servOnline = true;
                        writeLog.add(state[1] + ";" + System.lineSeparator());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeLog(writeLog, target);
    }

    private void writeLog(StringJoiner log, String target) {
        try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(target))) {
            output.write(log.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
