import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainScreen extends Screen {
    private JButton bConta, bVagas;
    public MainScreen() {
        super();
        inicializarBotoes();
        this.add(bConta, BorderLayout.NORTH);
        this.add(bVagas, BorderLayout.NORTH);
    }

    private void inicializarBotoes() {
        String nomeConta = (MainWindow.contaAtual == null) ? "Login" : MainWindow.contaAtual.getNome();
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
                new ThrowDialog(MainWindow.getFrame(), ":(", "Faz isso nao amg, eu nao fiz essa parte ainda").throwScreen();;
            }
            
        });
    }

    @Override
    public void update() {
        String nomeConta = (MainWindow.contaAtual == null) ? "Login" : MainWindow.contaAtual.getNome();
        this.bConta.setText(nomeConta);
    }
}
