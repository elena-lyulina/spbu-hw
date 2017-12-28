package spbu.sem3.hw3.task1;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(2000);
        System.out.println("Server is Running");
        try {
            while (true) {
                Game game = new Game();
                Socket socket1 = listener.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                System.out.println("Got the first client");
                Socket socket2 = listener.accept();
                System.out.println("Got the second client");

                PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
                PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

                Background bg = new Background(1000, 500);
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

                Player player1 = new Player(socket1, '1', game);
                Player player2 = new Player(socket2, '2', game);
                game.setPlayers(player1, player2);
                player1.start();
                player2.start();
            }
        } finally {
            listener.close();
        }
    }
}
//    public static void main(String[] ar)    {
//        int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
//        try {
//            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
//            System.out.println("Waiting for a clients...");
//
//            Socket socket1 = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
//            System.out.println("Got the first client");
//
//            Socket socket2 = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
//            System.out.println("Got the second client");
//
//            //Первый клиент должен запустить игру и скинуть координаты бэкграунда
//
//            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
//            InputStream sin = socket1.getInputStream();
//            OutputStream sout = socket1.getOutputStream();
//
//            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
//            DataInputStream in = new DataInputStream(sin);
//            DataOutputStream out = new DataOutputStream(sout);
//
//            String line = null;
//            while(true) {
//                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
//                System.out.println("The dumb client just sent me this line : " + line);
//                System.out.println("I'm sending it back...");
//                out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
//                out.flush(); // заставляем поток закончить передачу данных.
//                System.out.println("Waiting for the next line...");
//                System.out.println();
//            }
//        } catch(Exception x) { x.printStackTrace(); }
//}
