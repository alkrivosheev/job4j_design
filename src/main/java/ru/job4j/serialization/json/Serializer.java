package ru.job4j.serialization.json;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public static void main(String[] args) {
        JSONObject jsonUser = new JSONObject("{\"user\":\"Alexander\"}");
        List<String> list = new ArrayList<>();
        list.add("192.168.11");
        list.add("192.168.12");
        JSONArray jsonAddresses = new JSONArray(list);
        final Server server = new Server(true, 2, new User("Alexander"), "Terminals", new String[]{"192.168.11", "192.168.12"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("online", server.isOnline());
        jsonObject.put("units", server.getUnits());
        jsonObject.put("user", jsonUser);
        jsonObject.put("IPAddresses", jsonAddresses);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(server).toString());
    }
}
