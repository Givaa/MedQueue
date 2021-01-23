package business;

public class StrutturaBean {

    private int id;
    private String nome;
    private String indirizzo;
    private String numeroDiTelefono;

    public StrutturaBean(){}
    public StrutturaBean(int id, String nome, String indirizzo, String numeroDiTelefono) {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.numeroDiTelefono = numeroDiTelefono;
    }

    //GET
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getIndirizzo() { return indirizzo; }
    public String getNumeroDiTelefono() { return numeroDiTelefono; }

    //SET
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }
    public void setNumeroDiTelefono(String numeroDiTelefono) { this.numeroDiTelefono = numeroDiTelefono; }
}

