package spbu.sem3.hw3.task1;
import javafx.embed.swing.JFXPanel;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    private static int PORT = 2000;
    private int WINDOW_WIDTH = 800;
    private int WINDOW_HEIGHT = 500;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private JFXPanel jfx;
    private JFrame frame = new JFrame("The Canon");


    public Client(String serverAddress) throws Exception {
        socket = new Socket(serverAddress, PORT);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        jfx = new JFXPanel();
        jfx.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(jfx);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void start() throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        root.getChildren().add(canvas);
        jfx.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Graphic graphic = new Graphic(gc, root, writer, reader, socket);
        graphic.start();

        jfx.addKeyListener(new KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (graphic != null)
                    graphic.handleKeyEvent(e, root);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        while(true) {
            Client client = new Client("localhost");
            client.start();
            break;
        }
    }
}
