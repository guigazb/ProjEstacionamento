import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public abstract class Database {
    public static Cliente contaAtual = null;
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static HashSet<String> emailsCadastrados = new HashSet<>();
    private static ArrayList<Vaga> vagas = new ArrayList<>();

    public static void iniciar() {

        // Lendo logins anteriores
        File flogins = new File("logins.txt");
        if (flogins.exists()) {
            try (Scanner sc = new Scanner(flogins)) {
                while (sc.hasNextLine()) {
                    String nome = sc.nextLine();
                    String tel = sc.nextLine();
                    String ema = sc.nextLine();
                    String sen = sc.nextLine();
                    Cliente c = new Cliente(nome, tel, ema, sen);
                    cadastrarCliente(c);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    

        // Lendo dados de Vagas
        File fvagas = new File("vagas.txt");
        if (fvagas.exists()) {
            try (Scanner sc = new Scanner(fvagas)) {
                while (sc.hasNextLine()) {
                    int num = Integer.parseInt(sc.nextLine());
                    String loc = sc.nextLine();
                    String stat = sc.nextLine();
                    String veic = sc.nextLine();
                    Vaga v = new Vaga(num, loc, veic);
                    v.setStatus(stat);
                    vagas.add(v);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fechar() {
        File flogins = new File("logins.txt");
        if (flogins.exists()) { flogins.delete(); }

        try {
            flogins.createNewFile();
            Writer w = new FileWriter(flogins);
            for (Cliente c : clientes) {
                w.write(c.getNome() + '\n');
                w.write(c.getTelefone() + '\n');
                w.write(c.getEmail() + '\n');
                w.write(c.getSenha() + '\n');
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File fvagas = new File("vagas.txt");
        if (fvagas.exists()) { fvagas.delete(); }

        try {
            fvagas.createNewFile();
            Writer w = new FileWriter(fvagas);
            for (Vaga v : vagas) {
                w.write(String.valueOf(v.getNumero()) + '\n');
                w.write(v.getLocalizacao() + '\n');
                w.write(v.getStatus() + '\n');
                w.write(v.getTipoVeiculo() + '\n');
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean cadastrarCliente(Cliente c) {
        if (emailsCadastrados.contains(c.getEmail())) { return false; }
        clientes.add(c);
        emailsCadastrados.add(c.getEmail());
        return true;
    }

    public static boolean removerCliente(Cliente c) {
        return clientes.remove(c);
    }

    public static Cliente tentarLogin(Cliente c) {
        for (Cliente cl : clientes) {
            if (c.loginEquals(cl)) {
                return cl;
            }
        }
        return null;
    }

    public static boolean addVaga(Vaga v) {
        if (getVaga(v.getNumero(), v.getLocalizacao()) != null) { return false; }
        vagas.add(v);
        return true;
    }

    public static boolean rmvVaga(Vaga v) {
        return vagas.remove(v);
    }

    public static Vaga getVaga(int numero, String localizacao) {
        for (Vaga v : vagas) {
            if (v.getNumero() == numero && v.getLocalizacao().equals(localizacao)) {
                return v;
            }
        }
        return null;
    }

    public static ArrayList<Vaga> getVagaList() {
        return vagas;
    }
}
