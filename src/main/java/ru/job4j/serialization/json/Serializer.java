package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializer {
    public static void main(String[] args) {
        final Server server = new Server(true, 2, new User("Andrew"), "Star_1", new String[]{"192.168.11", "192.168.12"});
        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(server);
        System.out.println(json);
        final Server serverNEW = gson.fromJson(json, Server.class);
        System.out.println(serverNEW);
    }
}
