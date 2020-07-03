package lesson6;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        clientMain clientMain = new clientMain();
        System.out.println("Клиент подключился");

        new Thread() {
            public void run() {
                try {
                    clientMain.readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    clientMain.sendMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

class clientMain {
    Socket socket = null;
    BufferedReader in = null;
    PrintWriter out = null;
    BufferedReader console = null;
    String userMessage, serverMessage;

    public clientMain() throws IOException {
        socket = new Socket("localhost",8189);
        in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    void sendMessage() throws IOException {
        while (true) {
            if ((userMessage = console.readLine()) != null) {
                out.println(userMessage);
                if (userMessage.equalsIgnoreCase("/end")) break;
            }
        }
    }

    void readMessage() throws IOException {
        while (true) {
            if ((serverMessage = in.readLine()) != null){
                System.out.println(serverMessage);
            }
        }
    }
}