import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginFrame extends JDialog implements ActionListener {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JButton bLogin, bCadastrar;
    private JLabel lNome, lTelefone, lEmail, lSenha;
    private JLabel lAux1, lAux2;
    private JTextField tfNome, tfTelefone, tfEmail, tfSenha;

    public LoginFrame(JFrame parent) {
        super(parent, "Login", true);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        inicializarComponentes();
        this.setVisible(true);
    }

    private void inicializarComponentes() {
        lNome = new JLabel("*Nome:");
        lTelefone = new JLabel("*Telefone:");
        lEmail = new JLabel("**Email:");
        lSenha = new JLabel("**Senha:");

        lAux1 = new JLabel("*: Necessário para cadastro");
        lAux2 = new JLabel("**: Necessário para login e cadastro");

        tfNome = new JTextField(20);
        tfTelefone = new JTextField(20);
        tfEmail = new JTextField(20);
        tfSenha = new JTextField(20);

        bLogin = new JButton("Login");
        bCadastrar = new JButton("Cadastrar");

        bLogin.addActionListener(this);
        bCadastrar.addActionListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(lAux1, gbc);

        gbc.gridy = 1;
        this.add(lAux2, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lNome, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(tfNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(lTelefone, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(tfTelefone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(lEmail, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(tfEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(lSenha, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(tfSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(bLogin, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(bCadastrar, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = tfEmail.getText();
        if (email.isBlank()) {
            new ThrowDialog(MainWindow.getFrame(), "Erro", "Email não pode estar vazio!").throwScreen();
            return;
        }

        String senha = tfSenha.getText();
        if (senha.isBlank()) {
            new ThrowDialog(MainWindow.getFrame(), "Erro", "Senha não pode ser vazia!");
            return;
        }

        Object src = e.getSource();
        if (src == bLogin) {
            Cliente c = new Cliente("", "", email, senha);
            Cliente log = Database.tentarLogin(c);
            if (log != null) {
                new ThrowDialog(MainWindow.getFrame(), "Sucesso!", "Login feito com sucesso!").throwScreen();
                MainWindow.contaAtual = log;
                MainWindow.update();
                return;
            }
            new ThrowDialog(MainWindow.getFrame(), "Erro", "Conta não encontrada").throwScreen();
            return;
        } else if (src == bCadastrar) {
            String nome = tfNome.getText();
            if (nome.isBlank()) {
                new ThrowDialog(MainWindow.getFrame(), "Erro", "Nome não pode estar vazio!").throwScreen();
                return;
            }

            String telefone = tfTelefone.getText();
            if (telefone.isBlank()) {
                new ThrowDialog(MainWindow.getFrame(), "Erro", "Telefone não pode estar vazio!").throwScreen();
                return;
            }

            Cliente c = new Cliente(nome, telefone, email, senha);
            if (Database.cadastrarCliente(c)) {
                new ThrowDialog(MainWindow.getFrame(), "Sucesso!", "Cliente cadastrado com sucesso!").throwScreen();
                MainWindow.contaAtual = c;
                MainWindow.update();
                return;
            }
            new ThrowDialog(MainWindow.getFrame(), "Erro", "Não foi possível cadastrar o cliente").throwScreen();
            return;
        }
    }
}
