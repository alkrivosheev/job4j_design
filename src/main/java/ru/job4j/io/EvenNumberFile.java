package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] parts = text.toString().split(System.lineSeparator());
            for (String part: parts) {
                boolean res = Integer.parseInt(part) % 2 == 0;
                System.out.println(part + " : " + res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
