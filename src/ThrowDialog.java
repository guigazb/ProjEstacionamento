import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ThrowDialog extends JDialog implements ActionListener {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 150;
    private JButton okButton;

    public ThrowDialog(String title, String msg) {
        super(MainWindow.getFrame(), title, true);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLabel txt = new JLabel("<html><div style='text-align: center;'>" + msg + "</div></html>", SwingConstants.CENTER);
        txt.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.okButton = new JButton("Ok");
        this.okButton.setPreferredSize(new Dimension(60, 30));
        this.okButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        this.setLayout(new BorderLayout());
        this.add(txt, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void throwScreen() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
