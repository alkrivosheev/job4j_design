package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "server")
@XmlAccessorType(XmlAccessType.FIELD)
public class Server {
    @XmlAttribute
    private boolean online;
    @XmlAttribute
    private int units;
    private User user;
    private String serverName;
    @XmlElementWrapper(name = "ipAddresses")
    @XmlElement(name = "IP_Address")
    private String[] ipAddresses;

    public Server() {

    }

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
