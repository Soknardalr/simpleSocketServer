package org.example.somewhatIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(200);
        ServerSocket serverSocket = new ServerSocket(8189);
        System.out.println("server was started");

        try {
            while (true) {
                final Socket socket = serverSocket.accept();

//                handle(socket);
//                new Thread(() -> handle(socket)).start();
                pool.submit(()->handle(socket));
            }
        } finally {
            serverSocket.close();
        }
    }

    private static void handle(Socket socket) {
        System.out.println("client was connected: "+ socket.getRemoteSocketAddress());
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String clientRequest = reader.readLine();

            System.out.println("received from "+ socket.getRemoteSocketAddress()+ " > "+clientRequest );

            String serverResponse = clientRequest +", servertime" + System.currentTimeMillis();
            PrintWriter writer = new PrintWriter(out);
            writer.println(serverResponse);
            writer.flush();
            System.out.println("send to "+ socket.getRemoteSocketAddress()+" > " + serverResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
