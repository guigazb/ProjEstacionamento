import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VagaOptionsDialog extends JDialog {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 150;

    private JButton bOcupar;
    private JPanel buttonPanel;
    private VagaOptionsDialog aux;
    private Vaga pressionada;
    private JLabel txt;

    public VagaOptionsDialog(Vaga pressionada) {
        super(MainWindow.getFrame(), "Escolha uma opcao");
        aux = this;
        this.pressionada = pressionada;
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        inicializarComponentes();

        buttonPanel.add(bOcupar);
        this.add(txt, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void update() {
        String msg = pressionada.getLocalizacao() + 
                    ": " + String.valueOf(pressionada.getNumero()) + " (" + pressionada.getTipoVeiculo() +
                    " | " + pressionada.getStatus() + ")";

        txt.setText("<html><div style='text-align: center;'>" + msg + "</div></html>");
        this.revalidate();
        this.repaint();
    }

    public void inicializarComponentes() {
        String msg = pressionada.getLocalizacao() + 
                    ": " + String.valueOf(pressionada.getNumero()) + " (" + pressionada.getTipoVeiculo() +
                    " | " + pressionada.getStatus() + ")";

        txt = new JLabel("<html><div style='text-align: center;'>" + msg + "</div></html>", SwingConstants.CENTER);
        txt.setBorder(new EmptyBorder(10, 10, 10, 10));

        
        bOcupar = new JButton("Ocupar");
        bOcupar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.ocuparVaga(pressionada, new Carro("teste", "teste", "teste", "teste"));
                aux.update();
            }
            
        });

        buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    }
    public void throwScreen() { this.setVisible(true); }
}
