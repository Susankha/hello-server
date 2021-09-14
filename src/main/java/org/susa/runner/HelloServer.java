package org.susa.runner;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class HelloServer{

    private static Logger log = Logger.getLogger(HelloServer.class.getName());
    private int serverPort;
    private boolean isStop;

    public HelloServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void startServer() throws IOException {
        log.info("************ Starting Server ************");

        ServerSocket serverSocket = new ServerSocket(serverPort);
        log.info("************ Server IP : "+InetAddress.getLocalHost().getHostAddress());
        log.info("************ Server Listning on port : "+serverPort);

        Socket socket;

        while (!isStop){
            socket = serverSocket.accept();
            new Thread(new HelloWorker(socket)).start();
        }
    }
}
