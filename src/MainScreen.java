import javax.swing.JFrame;
import java.awt.FlowLayout;

public class MainScreen {
    private JFrame frame;
    public MainScreen(int width, int height) {
        this.frame = new JFrame("App Estacionamento");
        this.frame.setSize(width, height);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLayout(new FlowLayout());
    }

    public void run() { this.frame.setVisible(true); }
}
