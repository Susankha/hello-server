package org.susa.runner;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorker implements Runnable {

    private static Logger log = Logger.getLogger(HelloWorker.class.getName());
    private Socket socket;

    public HelloWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        log.info("***** Starting worker ***** " + Thread.currentThread().getId());
        try {
            processRequest();
            processResponse();
        } catch (IOException e) {
            log.log(Level.WARNING, e.getMessage());
        }
    }

    private void processRequest() throws IOException {
        log.info("***** Processing Request *****");
        InputStream reader = this.socket.getInputStream();
        if (reader != null) {
            BufferedReader requestReader = new BufferedReader(new InputStreamReader(reader));
            StringBuilder reqPayload = new StringBuilder();
            while (requestReader.ready()) {
                reqPayload = reqPayload.append(requestReader.read());
            }
        }
        this.socket.shutdownInput();
    }

    private void processResponse() throws IOException {
        log.info("***** Processing Response *****");
        Long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        OutputStream writer = this.socket.getOutputStream();

        String hostIP = InetAddress.getLocalHost().getHostAddress();
        writer.write(("HTTP/1.1 200 OK\r\nServer: hello/1.13.12\r\nDate: " + date.toString() + "\r\npod-ip: "+hostIP+"\r\n\r\n")
                .getBytes());
        writer.flush();
        this.socket.shutdownOutput();
    }
}
