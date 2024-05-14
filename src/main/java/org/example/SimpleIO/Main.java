package org.example.SimpleIO;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        new Thread(()-> {
            try {
                new ServerIO();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()-> {
            try {
                new ClientIO();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();



    }
}
