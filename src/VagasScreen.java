import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VagasScreen extends Screen {

    private HashMap<JButton, Vaga> bVagas;
    private JScrollPane scroll;
    private JPanel panel;

    public VagasScreen() {
        super();
        bVagas = new HashMap<>();
        panel = new JPanel(new GridLayout(0, 1, 10, 10));
        scroll = new JScrollPane(panel);
        this.setLayout(new GridLayout(1, 1));
        this.add(scroll);
        inicializarComponentes();
    }

    
    public void inicializarComponentes() {
        panel.removeAll();
        bVagas.clear();

        for (Vaga v : Database.getVagaList()) {
            JButton button = new JButton(v.getLocalizacao() + ": " + v.getNumero());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Vaga vPressionada = bVagas.get(button);
                    new VagaOptionsDialog(vPressionada).throwScreen();
                }
            });
            bVagas.put(button, v);
            panel.add(button);
        }

        panel.revalidate();
        panel.repaint();
    }

    public void reservarVaga(Vaga v) {
        if (Database.contaAtual == null) {
            new ThrowDialog("Erro", "Voce deve fazer login para reservar uma vaga").throwScreen();
            return;
        } else if (v.getTipoVeiculo().equals("Moto")) {
            new ThrowDialog("Erro", "Somente vagas para Carros e Caminhoes podem ser reservadas").throwScreen();
            return;
        } else if (!v.getStatus().equals("livre")) {
            new ThrowDialog("Erro", "Vaga nao esta livre para ser reservada").throwScreen();
            return;
        }
        v.setStatus("reservada");

    }
    
    @Override
    public void update() {
        inicializarComponentes();
        this.revalidate();
        this.repaint();
    }
}
