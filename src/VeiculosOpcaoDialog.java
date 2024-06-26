import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class VeiculosOpcaoDialog extends JDialog implements ActionListener{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JButton bCarro, bMoto, bCaminhao;
    private JPanel buttonPanel;
    public VeiculosOpcaoDialog() {
        super(MainWindow.getFrame(), "Escolha o Tipo de Veiculo: ", true);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        inicializarComponentes();
    }


    private void inicializarComponentes() {
        buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        
        bCarro = new JButton("Carro");
        bCarro.addActionListener(this);
        
        bMoto = new JButton("Moto");
        bMoto.addActionListener(this);
        
        bCaminhao = new JButton("Caminhao");
        bCaminhao.addActionListener(this);

        buttonPanel.add(bCarro);
        buttonPanel.add(bMoto);
        buttonPanel.add(bCaminhao);

        this.add(buttonPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
        String type = src.getText();

        if (type.equals("Carro")) {
            new RegistroCarroDialog().throwScreen();
        } else if (type.equals("Moto")) {
            new RegistroMotoDialog().throwScreen();
        } else {
            new RegistroCaminhaoDialog().throwScreen();
        }
        dispose();
    }

    public void throwScreen() { this.setVisible(true);}
}
