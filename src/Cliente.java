import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private List<Veiculo> veiculos;

    public Cliente(String nome, String telefone, String email, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.veiculos = new ArrayList<Veiculo>();
    }

    public void addVeiculo(Veiculo veiculo) { veiculos.add(veiculo); }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public List<Veiculo> getVeiculos() { return veiculos; }

    public boolean equals(Cliente c) {
        if (!nome.equals(c.getNome())) { return false; }
        if (!telefone.equals(c.getTelefone())) { return false; }
        if (!email.equals(c.getEmail())) { return false; }
        if (!senha.equals(c.getSenha())) { return false; }
        return true;
    }

    public boolean loginEquals(Cliente c) {
        if (!email.equals(c.getEmail())) { return false; }
        if (!senha.equals(c.getSenha())) { return false; }
        return true;
    }
}
