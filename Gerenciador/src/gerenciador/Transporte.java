package gerenciador;

public class Transporte {
    private int id;
    private String nome;
    private String tipo;
    private String local;
    private String diaViagem;

    public Transporte(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDiaViagem() {
        return diaViagem;
    }

    public void setDiaViagem(String diaViagem) {
        this.diaViagem = diaViagem;
    }
    
    
}
