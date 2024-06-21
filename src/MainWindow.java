import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class MainWindow {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;
    public static final int BUTTONWIDTH = WIDTH / 10;
    public static final int BUTTONHEIGHT = HEIGHT / 30;
    
    private static JFrame window;
    private static Stack<JPanel> nxtScreens, prvScreens;
    private static JPanel mainPanel, naviPanel;
    private static Screen currentPanel;
    private static JButton nxtScreenButton, prvScreenButton;
    public static Cliente contaAtual = null;

    public static void main(String[] args) {
        try {
            Database.iniciar();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        window = new JFrame("App Estacionamento");
        window.setSize(WIDTH, HEIGHT);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Database.fechar();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                window.dispose();
                System.exit(0);
            }
        });
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        initializeComponents();

        window.add(naviPanel, BorderLayout.SOUTH);
        window.add(mainPanel, BorderLayout.CENTER);

        window.setVisible(true);
    }

    public static void prevWindow() {
        if (!prvScreens.isEmpty()) {
            nxtScreens.push(currentPanel);
            mainPanel.remove(currentPanel);

            JPanel newScreen = prvScreens.pop();
            currentPanel = (Screen)newScreen;
            mainPanel.add(currentPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    public static void nextWindow() {
        if (!nxtScreens.isEmpty()) {
            prvScreens.push(currentPanel);
            mainPanel.remove(currentPanel);

            JPanel newScreen = nxtScreens.pop();
            currentPanel = (Screen)newScreen;
            mainPanel.add(currentPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    public static void setCurrentPanel(Screen p) {
        if (currentPanel != null) {
            prvScreens.push(currentPanel);
            mainPanel.remove(currentPanel);
        }
        mainPanel.add(p);
        currentPanel = p;
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void update() {
        currentPanel.update();
    }

    private static void initializeComponents() {
        nxtScreens = new Stack<>();
        prvScreens = new Stack<>();

        mainPanel = new JPanel(new CardLayout());
        setCurrentPanel(new MainScreen());

        nxtScreenButton = new JButton(">");
        nxtScreenButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        nxtScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.nextWindow();
            }
        });

        prvScreenButton = new JButton("<");
        prvScreenButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        prvScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.prevWindow();
            }
        });

        naviPanel = new JPanel();
        naviPanel.add(prvScreenButton);
        naviPanel.add(nxtScreenButton);
    }

    public static JFrame getFrame() { return window; }
}
