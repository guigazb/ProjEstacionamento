import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public abstract class Database {
    public static Cliente contaAtual = null;
    public static Veiculo veiculoEscolhido = null;

    public static final String CLIENTENAOREGISTRADO = "Sem Conta";
    public static final double REAISPORMINUTO = 1f;

    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static HashSet<String> emailsCadastrados = new HashSet<String>();
    private static ArrayList<Vaga> vagas = new ArrayList<Vaga>();
    private static ArrayList<Controle> vagasOcupadas = new ArrayList<Controle>();
    private static ArrayList<Pagamento> historicoDePagamentos = new ArrayList<Pagamento>();

    public static void iniciar() {

        // Lendo logins anteriores
        File flogins = new File("logins.txt");
        if (flogins.exists()) {
            try {
                Scanner sc = new Scanner(flogins);
                while (sc.hasNextLine()) {
                    String nome = sc.nextLine();
                    String tel = sc.nextLine();
                    String ema = sc.nextLine();
                    String sen = sc.nextLine();
                    Cliente c = new Cliente(nome, tel, ema, sen);
                    cadastrarCliente(c);
                    int nveiculos = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < nveiculos; i++) {
                        String placa = sc.nextLine();
                        String tipo = sc.nextLine();
                        if (tipo.equals("Carro")) {
                            String cor = sc.nextLine();
                            String marca = sc.nextLine();
                            String modelo = sc.nextLine();
                            Carro car = new Carro(placa, cor, marca, modelo);
                            c.addVeiculo(car);
                        } else if (tipo.equals("Moto")) {
                            String marca = sc.nextLine();
                            int cilindradas = Integer.parseInt(sc.nextLine());
                            Moto m = new Moto(placa, marca, cilindradas);
                            c.addVeiculo(m);
                        } else if (tipo.equals("Caminhao")) {
                            double cargaMaxima = Double.parseDouble(sc.nextLine());
                            double comprimento = Double.parseDouble(sc.nextLine());
                            Caminhao cam = new Caminhao(placa, cargaMaxima, comprimento);
                            c.addVeiculo(cam);
                        }
                    }
                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    

        // Lendo dados de Vagas
        File fvagas = new File("vagas.txt");
        if (fvagas.exists()) {
            try {
                Scanner sc = new Scanner(fvagas);
                while (sc.hasNextLine()) {
                    int num = Integer.parseInt(sc.nextLine());
                    String loc = sc.nextLine();
                    String stat = sc.nextLine();
                    String veic = sc.nextLine();
                    Vaga v = new Vaga(num, loc, veic);
                    if (!stat.equals("livre")) {
                        String emailCl = sc.nextLine();
                        v.setEmailClienteOcupando(emailCl);
                    }
                    v.setStatus(stat);
                    vagas.add(v);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        File fpagamentos = new File("pagamentos.txt");
        if (fpagamentos.exists()) {
            try {
                Scanner sc = new Scanner(fpagamentos);
                while (sc.hasNextLine()) {
                    double valPago = Double.parseDouble(sc.nextLine());
                    String formaPagamento = sc.nextLine();
                    int year = Integer.parseInt(sc.nextLine());
                    int month = Integer.parseInt(sc.nextLine());
                    int day = Integer.parseInt(sc.nextLine());
                    int hour = Integer.parseInt(sc.nextLine());
                    int minutes = Integer.parseInt(sc.nextLine());
                    LocalDateTime ldt = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minutes));
                    new Pagamento(valPago, formaPagamento, ldt);
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

        // Escrevendo dados de cada login
        try {
            flogins.createNewFile();
            Writer w = new FileWriter(flogins);
            for (Cliente c : clientes) {
                w.write(c.getNome() + '\n');
                w.write(c.getTelefone() + '\n');
                w.write(c.getEmail() + '\n');
                w.write(c.getSenha() + '\n');

                // Escrevendo os veiculos para cada cliente
                List<Veiculo> veiculos = c.getVeiculos();
                w.write(String.valueOf(veiculos.size()) + '\n');
                for (Veiculo v : veiculos) {
                    w.write(v.getPlaca() + '\n');
                    String tipo = v.getTipo();
                    w.write(tipo + '\n');
                    if (tipo.equals("Carro")) {
                        Carro car = (Carro) v;
                        w.write(car.getCor() + '\n');
                        w.write(car.getMarca() + '\n');
                        w.write(car.getModelo() + '\n');
                    } else if (tipo.equals("Moto")) {
                        Moto moto = (Moto) v;
                        w.write(moto.getMarca() + '\n');
                        w.write(String.valueOf(moto.getCilindradas()) + '\n');
                    } else if (tipo.equals("Caminhao")) {
                        Caminhao cam = (Caminhao) v;
                        w.write(String.valueOf(cam.getCargaMaxima()) + '\n');
                        w.write(String.valueOf(cam.getComprimento()) + '\n');
                    }
                }
                
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File fvagas = new File("vagas.txt");
        if (fvagas.exists()) { fvagas.delete(); }

        // Escrevendo os dados de cada vaga
        try {
            fvagas.createNewFile();
            Writer w = new FileWriter(fvagas);
            for (Vaga v : vagas) {
                w.write(String.valueOf(v.getNumero()) + '\n');
                w.write(v.getLocalizacao() + '\n');
                w.write(v.getStatus() + '\n');
                w.write(v.getTipoVeiculo() + '\n');
                if (!v.getStatus().equals("livre")) {
                    w.write(v.getEmailClienteOcupando() + '\n');
                }
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File fpagamentos = new File("pagamentos.txt");
        if (fpagamentos.exists()) { fpagamentos.delete(); }

        try {
            fpagamentos.createNewFile();
            Writer w = new FileWriter(fpagamentos);
            for (Pagamento p : historicoDePagamentos) {
                w.write(String.valueOf(p.getValorPago()) + '\n');
                w.write(p.getFormaPagamento() + '\n');
                w.write(String.valueOf(p.getHorarioPagamento().getYear()) + '\n');
                w.write(String.valueOf(p.getHorarioPagamento().getMonthValue()) + '\n');
                w.write(String.valueOf(p.getHorarioPagamento().getDayOfMonth()) + '\n');
                w.write(String.valueOf(p.getHorarioPagamento().getHour()) + '\n');
                w.write(String.valueOf(p.getHorarioPagamento().getSecond()) + '\n');
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

    public static Cliente achaCliente(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)) {
                return c;
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

    public static void pagarVaga(Vaga v) {
        Controle c = getControle(v);
        if (c == null) { return; }
        c.registrarSaida();
        double valorAPagar = c.valorAPagar();
        vagasOcupadas.remove(c);
        new PagamentoDialog(valorAPagar, v).throwScreen();
    }

    public static Controle getControle(Vaga v) {
        for (Controle c : vagasOcupadas) {
            if (c.getVaga().equals(v)) {
                return c;
            }
        }
        return null;
    }

    public static void reservarVaga(Vaga v) {
        v.setStatus("reservada");
    }

    public static ArrayList<Vaga> getVagaList() {
        return vagas;
    }

    public static void ocuparVaga(Vaga va, Veiculo veic) {
        if (!va.getStatus().equals("livre")) {
            new ThrowDialog("Erro", "Vaga nao esta livre para ser ocupada").throwScreen();
            return;
        } else if (!va.getTipoVeiculo().equals(veic.getTipo())) {
            new ThrowDialog("Erro", "Esse veiculo nao pode ocupar essa vaga").throwScreen();
            return;
        }
        vagasOcupadas.add(new Controle(veic, va));
    }

    public static ArrayList<Pagamento> getHistoricoDePagamentos() {
        return historicoDePagamentos;
    }

    public static ArrayList<Pagamento> getPagamentosDiario() {
        ArrayList<Pagamento> diario = new ArrayList<Pagamento>();
        LocalDate hoje = LocalDate.now();
        int i = historicoDePagamentos.size() - 1;
        while (i >= 0 && historicoDePagamentos.get(i).getHorarioPagamento().toLocalDate().isEqual(hoje)) {
            diario.add(historicoDePagamentos.get(i));
            i--;
        }
        return diario;
    }

    public static void addPagamento(Pagamento p) {
        historicoDePagamentos.add(p);
    }
}
