import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistroCarroDialog extends JDialog {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JLabel lPlaca, lCor, lMarca, lModelo;
    private JTextField tfPlaca, tfCor, tfMarca, tfModelo;
    private JButton bConfirmar;

    public RegistroCarroDialog() {
        super(MainWindow.getFrame(), "Registre o Carro", true);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        lPlaca = new JLabel("Placa:");
        lCor = new JLabel("Cor:");
        lMarca = new JLabel("Marca:");
        lModelo = new JLabel("Modelo:");

        tfPlaca = new JTextField(20);
        tfCor = new JTextField(20);
        tfMarca = new JTextField(20);
        tfModelo = new JTextField(20);

        bConfirmar = new JButton("Ok");
        bConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = tfPlaca.getText();
                if (placa.isBlank()) { new ThrowDialog("Erro", "Placa não pode estar vazia").throwScreen(); return; }
                String cor = tfCor.getText();
                if (cor.isBlank()) { new ThrowDialog("Erro", "Cor não pode estar vazia").throwScreen(); return; }
                String marca = tfMarca.getText();
                if (marca.isBlank()) { new ThrowDialog("Erro", "Marca não pode estar vazia").throwScreen(); return; }
                String modelo = tfModelo.getText();
                if (modelo.isBlank()) { new ThrowDialog("Erro", "Modelo não pode estar vazio").throwScreen(); return; }
                Carro c = new Carro(placa, cor, marca, modelo);
                if (Database.contaAtual != null) { Database.contaAtual.addVeiculo(c); }
                Database.veiculoEscolhido = c;
                dispose();
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
        panel.add(lCor, gbc);

        gbc.gridx = 1;
        panel.add(tfCor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lMarca, gbc);

        gbc.gridx = 1;
        panel.add(tfMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lModelo, gbc);

        gbc.gridx = 1;
        panel.add(tfModelo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(bConfirmar, gbc);

        this.add(panel, BorderLayout.CENTER);
    }

    public void throwScreen() { this.setVisible(true); }
}
