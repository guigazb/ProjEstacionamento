import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VeiculosScreen extends Screen {

    private HashMap<JButton, Veiculo> bVeics;
    private JScrollPane scroll;
    private JPanel panel;
    private JButton bCadastrarVeic;

    public VeiculosScreen() {
        super();
        bVeics = new HashMap<>();
        panel = new JPanel(new GridLayout(0, 1, 10, 10));
        scroll = new JScrollPane(panel);
        this.setLayout(new GridLayout(1, 1));
        this.add(scroll);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panel.removeAll();
        bVeics.clear();

        bCadastrarVeic = new JButton("Cadastrar Veículo");
        bCadastrarVeic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VeiculosOpcaoDialog().throwScreen();
                update();
            }
        });
        panel.add(bCadastrarVeic);

        if (Database.contaAtual != null) {
            for (Veiculo v : Database.contaAtual.getVeiculos()) {
                JButton button = new JButton(v.getTipo() + ": " + v.getPlaca());
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Database.veiculoEscolhido = bVeics.get(button);
                        MainWindow.setCurrentPanel(new MainScreen());
                    }
                });
                bVeics.put(button, v);
                panel.add(button);
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void update() {
        inicializarComponentes();
        this.revalidate();
        this.repaint();
    }
}
