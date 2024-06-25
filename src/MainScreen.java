import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainScreen extends Screen {
    private JButton bConta, bVagas;
    public MainScreen() {
        super();
        inicializarComponentes();
        this.add(bConta, BorderLayout.NORTH);
        this.add(bVagas, BorderLayout.NORTH);
    }

    private void inicializarComponentes() {
        String nomeConta = (Database.contaAtual == null) ? "Login" : Database.contaAtual.getNome();
        bConta = new JButton(nomeConta);
        bConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(MainWindow.getFrame());
            }
        });

        bVagas = new JButton("Vagas");
        bVagas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.setCurrentPanel(new VagasScreen());
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
