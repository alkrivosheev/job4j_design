package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String msg = input.readLine();
                    if (msg.contains("msg=Hello")) {
                        output.write("Hello\n".getBytes());
                    } else if (msg.contains("msg=Exit")) {
                        server.close();
                    } else {
                        output.write("What.".getBytes());
                    }
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                } catch (Exception e) {
                    LOG.error("Error in socket I/O.", e);
                }
            }
        } catch (Exception e) {
            LOG.error("Error in socket init.", e);
        }
    }
}
