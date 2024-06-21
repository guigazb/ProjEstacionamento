public class Moto extends Veiculo {
    private String marca;
    private int cilindradas;

    public Moto(String placa, String marca, int cilindradas) {
        super(placa, "Moto");
        this.marca = marca;
        this.cilindradas = cilindradas;
    }

    public String getmarca(){
        return this.marca;
    }
    public double getCilindradas(){
        return this.cilindradas;
    }

    public void setmarca(String marca){
        this.marca = marca;
    }
    public void setcilindradas(int cilindradas){
        this.cilindradas = cilindradas;
    }

}