import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainScreen extends Screen {
    private JButton bConta, bVagas, bVeiculos;
    public MainScreen() {
        super();
        inicializarComponentes();
        this.add(bConta, BorderLayout.NORTH);
        this.add(bVagas, BorderLayout.NORTH);
        this.add(bVeiculos, BorderLayout.NORTH);
    }

    private void inicializarComponentes() {
        String nomeConta = (Database.contaAtual == null) ? "Login" : Database.contaAtual.getNome();
        bConta = new JButton(nomeConta);
        bConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginDialog();
            }
        });

        bVagas = new JButton("Vagas");
        bVagas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.setCurrentPanel(new VagasScreen());
            }
            
        });

        bVeiculos = new JButton("Veiculos");
        bVeiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Database.contaAtual == null) {
                    new ThrowDialog("Erro", "Voce deve estar logado para acessar esta funcao").throwScreen();
                    return;
                }
                MainWindow.setCurrentPanel(new VeiculosScreen());
            }
            
        });
    }

    @Override
    public void update() {
        if (Database.contaAtual != null) {
            bConta.setText(Database.contaAtual.getNome());
        }
        this.revalidate();
        this.repaint();
    }
}
