package gerenciador;

public class Viagem {
    private int id;
    private int pessoa;
    private int transporte;
    private int hospedagem;

    public Viagem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPessoa() {
        return pessoa;
    }

    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }

    public int getTransporte() {
        return transporte;
    }

    public void setTransporte(int transporte) {
        this.transporte = transporte;
    }

    public int getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(int hospedagem) {
        this.hospedagem = hospedagem;
    }
    
    
}
