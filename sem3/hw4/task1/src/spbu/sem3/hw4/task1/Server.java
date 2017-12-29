package spbu.sem3.hw4.task1;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(2000);
        System.out.println("Server is Running");
        try {
                Socket socket1 = listener.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                System.out.println("Got the first client");
                Socket socket2 = listener.accept();
                System.out.println("Got the second client");

                PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
                PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

                Background bg = new Background(800, 500);
                int[][] coord = bg.getCoord();
                StringBuilder sb1 = new StringBuilder("MOUNT COORD");
                StringBuilder sb2 = new StringBuilder("MOUNT COORD");
                for (int i = 0; i < bg.getN(); i++) {
                    sb1.append(coord[0][i] + " ");
                    sb2.append(coord[0][i] + " ");
                    sb1.append(coord[1][i] + " ");
                    sb2.append(coord[1][bg.getN() - 1 - i] + " ");
                }
                System.out.println(sb1.toString());
                System.out.println(sb2.toString());

                out1.println(sb1.toString());
                out2.println(sb2.toString());
                System.out.println("messages sent");

                Player player1 = new Player(socket1);
                Player player2 = new Player(socket2);
                player1.setOpponent(player2);
                player2.setOpponent(player1);
                player1.start();
                player2.start();
        } finally {
            listener.close();
        }
    }
}
