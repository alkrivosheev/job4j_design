package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        Random rn = new Random();
        boolean stop = false;
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        String text = in.nextLine();
        while (!OUT.equals(text)) {
            log.add(text);
            if (STOP.equals(text)) {
                stop = true;
            } else if (CONTINUE.equals(text)) {
                stop = false;
            } else if (!stop) {
                String answer = phrases.get(rn.nextInt(phrases.size()));
                System.out.println(answer);
                log.add(answer);
            }
            text = in.nextLine();
        }
        log.add(text);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .map(string -> string + System.lineSeparator())
                    .forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(botAnswers, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/phrases.txt", "./data/log.txt");
        consoleChat.run();
    }
}
