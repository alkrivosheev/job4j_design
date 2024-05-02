package ru.job4j.serialization.json;

import java.util.Arrays;

public class Server {
    private final boolean online;
    private final int units;
    private final User user;
    private final String serverName;
    private final String[] ipAddresses;

    public Server(boolean online, int units, User user, String serverName, String[] ipAddresses) {
        this.online = online;
        this.units = units;
        this.user = user;
        this.serverName = serverName;
        this.ipAddresses = ipAddresses;
    }

    @Override
    public String toString() {
        return "Server{" +  "online=" + online + ", units=" + units
                + ", user=" + user + ", serverName='" + serverName + '\''
                + ", ipAddresses=" + Arrays.toString(ipAddresses) + '}';
    }
}
