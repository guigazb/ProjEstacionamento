import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistroMotoDialog extends JDialog {
public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JLabel lPlaca, lMarca, lCilindradas;
    private JTextField tfPlaca, tfMarca, tfCilindradas;
    private JButton bConfirmar;
    public RegistroMotoDialog() {
        super(MainWindow.getFrame(), "Registre a Moto", true);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        lPlaca = new JLabel("Placa:");
        lMarca = new JLabel("Marca:");
        lCilindradas = new JLabel("Cilindradas:");

        tfPlaca = new JTextField(20);
        tfMarca = new JTextField(20);
        tfCilindradas = new JTextField(20);

        bConfirmar = new JButton("Ok");
        bConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = tfPlaca.getText();
                if (placa.isBlank()) { new ThrowDialog("Erro", "Placa nao pode estar vazia").throwScreen(); return; }
                String marca = tfMarca.getText();
                if (marca.isBlank()) { new ThrowDialog("Erro", "Marca nao pode estar vazia").throwScreen(); return; }
                try {
                    int cilindradas = Integer.parseInt(tfCilindradas.getText());
                    Moto m = new Moto(placa, marca, cilindradas);
                    if (Database.contaAtual != null) { Database.contaAtual.addVeiculo(m); }
                    Database.veiculoEscolhido = m;
                    dispose();
                } catch (NumberFormatException exc) {
                    new ThrowDialog("Erro", "Cilindradas deve ser um numero inteiro").throwScreen();
                }
            }
            
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lPlaca, gbc);

        gbc.gridx = 1;
        panel.add(tfPlaca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lMarca, gbc);

        gbc.gridx = 1;
        panel.add(tfMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lCilindradas, gbc);

        gbc.gridx = 1;
        panel.add(tfCilindradas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(bConfirmar, gbc);

        this.add(panel, BorderLayout.CENTER);
    }

    public void throwScreen() { this.setVisible(true); }
}
