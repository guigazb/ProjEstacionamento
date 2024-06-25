import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;

public class VagasScreen extends Screen {

    private HashMap<JButton, Vaga> bVagas;
    private VagasScreen aux;
    public VagasScreen() {
        super();
        bVagas = null;
        aux = this;
        inicializarComponentes();
    }

    
    public void inicializarComponentes() {
        if (bVagas == null) {
            bVagas = new HashMap<JButton, Vaga>();
        } else {
            for (Entry<JButton, Vaga> entry : bVagas.entrySet()) {
                this.remove(entry.getKey());
            }
            bVagas.clear();
        }
        
        for (Vaga v : Database.getVagaList()) {
            JButton button = new JButton(v.getLocalizacao() + ": " + v.getNumero());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Vaga vPressionada = bVagas.get(button);
                    new VagaOptionsDialog(aux, vPressionada).throwScreen();
                }
            });
            bVagas.put(button, v);
            this.add(button);
        }
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
