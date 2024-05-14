package org.example.SimpleIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerIO {
    private final ServerSocket server;
    private final Socket socket;
    private final OutputStream out;
    private final InputStream in;

    public ServerIO() throws IOException {
        this.server = new ServerSocket(8189);
        System.out.println("server was started");
        socket = server.accept();
        System.out.println("accept");
        this.out = socket.getOutputStream();
        this.in = socket.getInputStream();

        System.out.println(in.getClass()+" "+out.getClass());

        go();
    }

    private void go() throws IOException {
        byte[] data = new byte[128];
        while (in.read()!= -1){
            out.write(data);
        }
    }
}
