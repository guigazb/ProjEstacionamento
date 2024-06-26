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

public class RegistroCaminhaoDialog extends JDialog {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JLabel lPlaca, lCargaMax, lComprimento;
    private JTextField tfPlaca, tfCargaMax, tfComprimento;
    private JButton bConfirmar;

    public RegistroCaminhaoDialog() {
        super(MainWindow.getFrame(), "Registre o Caminhão", true);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        lPlaca = new JLabel("Placa:");
        lCargaMax = new JLabel("Carga Maxima:");
        lComprimento = new JLabel("Comprimento:");

        tfPlaca = new JTextField(20);
        tfCargaMax = new JTextField(20);
        tfComprimento = new JTextField(20);

        bConfirmar = new JButton("Ok");
        bConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = tfPlaca.getText();
                if (placa.isBlank()) { new ThrowDialog("Erro", "Placa não pode estar vazia").throwScreen(); return; }
                String cargaMax = tfCargaMax.getText();
                if (cargaMax.isBlank()) { new ThrowDialog("Erro", "Carga Maxima não pode estar vazia").throwScreen(); return; }
                String comprimento = tfComprimento.getText();
                if (comprimento.isBlank()) { new ThrowDialog("Erro", "Comprimento não pode estar vazio").throwScreen(); return; }
                try {
                    double cargaMaxDouble = Double.parseDouble(cargaMax);
                    double comprimentoDouble = Double.parseDouble(comprimento);
                    Caminhao c = new Caminhao(placa, cargaMaxDouble, comprimentoDouble);
                    if (Database.contaAtual != null) { Database.contaAtual.addVeiculo(c); }
                    Database.veiculoEscolhido = c;
                    dispose();
                } catch (NumberFormatException exc) {
                    new ThrowDialog("Erro", "Carga Maxima e Comprimento devem ser números").throwScreen();
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
        panel.add(lCargaMax, gbc);

        gbc.gridx = 1;
        panel.add(tfCargaMax, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lComprimento, gbc);

        gbc.gridx = 1;
        panel.add(tfComprimento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(bConfirmar, gbc);

        this.add(panel, BorderLayout.CENTER);
    }

    public void throwScreen() { this.setVisible(true); }
}
