import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public abstract class Database {
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static HashSet<String> emailsCadastrados = new HashSet<>();

    public static void iniciar() throws FileNotFoundException {
        File f = new File("logins.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            String nome = sc.nextLine();
            String tel = sc.nextLine();
            String ema = sc.nextLine();
            String sen = sc.nextLine();
            Cliente c = new Cliente(nome, tel, ema, sen);
            cadastrarCliente(c);
        }
        sc.close();
    }

    public static void fechar() throws IOException {
        File f = new File("logins.txt");
        if (f.exists()) {
            f.delete();
            f.createNewFile();
        }

        Writer w = new FileWriter(f);
        for (Cliente c : clientes) {
            w.write(c.getNome() + '\n');
            w.write(c.getTelefone() + '\n');
            w.write(c.getEmail() + '\n');
            w.write(c.getSenha() + '\n');
        }

        w.close();
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
}
