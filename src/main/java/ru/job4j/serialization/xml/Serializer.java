package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Serializer {
    public static void main(String[] args) throws Exception {
            Server server = new Server(true, 3, new User("Alexander"), "Terminals", new String[]{"192.168.11", "192.168.12"});
            JAXBContext context = JAXBContext.newInstance(Server.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String xml = "";
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(server, writer);
                xml = writer.getBuffer().toString();
                System.out.println(xml);
            }
            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(xml)) {
                Server result = (Server) unmarshaller.unmarshal(reader);
                System.out.println(result);
            }
    }
}
