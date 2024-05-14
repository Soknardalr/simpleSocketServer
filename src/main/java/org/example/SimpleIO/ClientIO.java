package org.example.SimpleIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClientIO {
    private final Socket socket;
    private final OutputStream out;
    private final InputStream in;

    public ClientIO() throws Exception {
        this.socket = new Socket("localhost", 8189);
        this.out = socket.getOutputStream();
        this.in = socket.getInputStream();

        go();
    }

    private void go() throws IOException {
        out.write("Hello, world!".getBytes());

        int read;
        while ((read = in.read()) != -1){
            System.out.print((char) read);
        }
    }

//    private void handle(byte[] data) {
//        System.out.println(Arrays.toString(data));
//    }
}
