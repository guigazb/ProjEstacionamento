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

    private JButton bOcupar, bPagar, bReservar;
    private JPanel buttonPanel;
    private VagaOptionsDialog aux;
    private Vaga pressionada;
    private JLabel txt;

    public VagaOptionsDialog(Vaga pressionada) {
        super(MainWindow.getFrame(), "Escolha uma opcao", true);
        aux = this;
        this.pressionada = pressionada;
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        bReservar = null;
        inicializarComponentes();

        buttonPanel.add(bOcupar);
        buttonPanel.add(bPagar);
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
                if (Database.veiculoEscolhido == null) {
                    new VeiculosOpcaoDialog().throwScreen();
                }
                Database.ocuparVaga(pressionada, Database.veiculoEscolhido);
                aux.update();
            }
            
        });

        bPagar = new JButton("Pagar");
        bPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: erro por aqui ou em Database.pagarVaga(), por algum motivo e possivel pagar a vaga de outra pessoa
                // e tambem nao eh possivel pagar a vaga depois de, sem login, ocupar uma vaga, fechar a janela da vaga e voltar depois
                String email = pressionada.getEmailClienteOcupando();
                if (email.equals(Database.CLIENTENAOREGISTRADO)) {
                    // Cliente ocupando nao eh registrado, so aceita pagamento
                    Database.pagarVaga(pressionada);
                    dispose();
                } else {
                    if (Database.contaAtual != null && email.equals(Database.contaAtual.getEmail())) {
                        Database.pagarVaga(pressionada);
                        dispose();
                    } else {
                        new ThrowDialog("Erro", "Voce nao pode pagar a conta de outra pessoa!").throwScreen();
                    }
                }
            }
            
        });

        if (Database.contaAtual != null) {
            bReservar = new JButton("Reservar");
            bReservar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Database.veiculoEscolhido.getTipo().equals(pressionada.getTipoVeiculo())) {
                        Database.reservarVaga(pressionada);
                        aux.update();
                    } else {
                        new ThrowDialog("Erro", "Vaga incompativel com seu veiculo");
                    }
                }
                
            });
        }

        buttonPanel = new JPanel(new GridLayout(1, 3));
    }
    public void throwScreen() { this.setVisible(true); }
}
