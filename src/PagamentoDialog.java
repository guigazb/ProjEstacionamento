import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PagamentoDialog extends JDialog implements ActionListener {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 150;

    private JLabel lValor;
    private JButton bPix, bDinheiro, bCartao;
    private double valorAPagar;
    private Vaga vagaPaga;
    public PagamentoDialog(double valor, Vaga vagaPaga) {
        super(MainWindow.getFrame(), "Pagamento", true);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.valorAPagar = valor;
        this.vagaPaga = vagaPaga;

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        lValor = new JLabel("Valor a ser pago: " + valorAPagar);
        this.add(lValor, BorderLayout.CENTER);

        bPix = new JButton("Pix");
        bPix.addActionListener(this);

        bDinheiro = new JButton("Dinheiro");
        bDinheiro.addActionListener(this);

        bCartao = new JButton("Cartao");
        bCartao.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(bPix);
        buttonPanel.add(bDinheiro);
        buttonPanel.add(bCartao);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void throwScreen() { this.setVisible(true); }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
        new Pagamento(valorAPagar, src.getText());
        Database.pagarVaga(vagaPaga);
        this.dispose();
    }
}
