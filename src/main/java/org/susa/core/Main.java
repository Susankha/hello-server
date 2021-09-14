package org.susa.core;

import org.susa.runner.HelloServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        HelloServer server = new HelloServer(9000);
        try {
            server.startServer();
        } catch (IOException e) {
            log.log(Level.WARNING,e.getMessage());
        }

    }
}
