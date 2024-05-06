package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {
    @XmlAttribute
    private String name;

    public User() {

    }
    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + '}';
    }
}
