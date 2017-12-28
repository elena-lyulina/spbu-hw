//package spbu.sem3.hw3.task1;
//
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.canvas.*;
//import javafx.scene.paint.*;
//
//import java.awt.*;
//import java.awt.Canvas;
//import java.awt.Color;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//public class Test extends JFrame {
//    private GraphicsContext gc;
//    private JLabel label;
//    private JFXPanel jfx;
//    private JPanel panel;
//
//    public Test() {
//        super("Test frame");
//        System.out.println("test");
//        createGUI();
//    }
//
//    public void createGUI() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jfx = new JFXPanel();
//        jfx.setSize(1000, 500);
//        jfx.setLocation(10, 40);
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        panel.setFocusable(true);
//
//        Group root = new Group();
//        Scene scene = new Scene(root, 1000, 500);
//        javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas(1000, 500);
//        root.getChildren().add(canvas);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        System.out.println("plz work");
//        panel.addKeyListener(new KeyAdapter() {
//
//            public void keyReleased(KeyEvent e) {
//                Graphic.handleKeyEvent(e, root);
//            }
//
//        });
//        jfx.setScene(scene);
//        panel.add(jfx);
//        setPreferredSize(new Dimension(1000, 500));
//        getContentPane().add(panel);
//
//        gc.setFill(new LinearGradient(0, 0, 0, 1, true,
//                CycleMethod.REFLECT,
//                new Stop(1.1, javafx.scene.paint.Color.WHITE),
//                new Stop(0.0, javafx.scene.paint.Color.rgb(108, 166, 205))
//        ));
//        gc.fillRect(0, 0 , 1000, 500);
//
//    }
//
//    public static void main(String[] args) throws Exception {
//        Client1 client = new Client1("localhost");
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                JFrame.setDefaultLookAndFeelDecorated(true);
//                Test frame = null;
//                try {
//                    frame = new Test(client.getSocket(), client.getReader(), client.getWriter());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//            }
//        });
//
//    }
//}