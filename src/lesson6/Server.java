package lesson6;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        serverMain serv = new serverMain();
        serv.start();
        serv.catchClient();
        new Thread() {
            public void run() {
                while (true) {
                    String txt = null;
                    try {
                        txt = serv.in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (txt != null) {
                        try {
                            serv.sendMessage(txt);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    serv.writeToConsole();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

class serverMain {
    BufferedReader in = null;
    PrintWriter out = null;
    ServerSocket serverSocket = null;
    Socket socket = null;
    BufferedReader console = null;

    void start() {
        try {
            serverSocket = new ServerSocket(8189);
        } catch (IOException e) {
            System.exit(1);
        }
        System.out.print("Сервер запущен");
    }

    void catchClient() throws IOException {
        try {
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
        } catch (IOException e) {
            System.exit(1);
        }

        in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
    }

    void sendMessage(String text) throws IOException {
        if (text.equalsIgnoreCase("/end")) close();
        out.println(text);
        System.out.println(text);
    }

    void close() throws IOException {
        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }

    void writeToConsole() throws IOException {
        while (true) {
            console = new BufferedReader(new InputStreamReader(System.in));
            String str = console.readLine();
            sendMessage(str);
        }
    }
}