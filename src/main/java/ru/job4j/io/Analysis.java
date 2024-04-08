package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(target))) {
            StringBuilder builder = new StringBuilder();
            boolean servOnline = true;
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                if (!"".equals(line)) {
                    String[] state = line.split(" ", 2);
                    if (servOnline && "500".equals(state[0]) | "400".equals(state[0])) {
                        servOnline = false;
                        builder.append(state[1]);
                        builder.append(";");
                        output.write(builder.toString().getBytes());
                        builder.setLength(0);
                    } else if (!servOnline && "300".equals(state[0]) | "200".equals(state[0])) {
                        servOnline = true;
                        builder.append(state[1]);
                        builder.append(";");
                        builder.append(System.lineSeparator());
                        output.write(builder.toString().getBytes());
                        builder.setLength(0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
