package classes.model.bean.entity;

public class AmbulatoriBean {

  private int id;
  private String nome;
  private int idStruttura;

  public AmbulatoriBean(String nome, int idStruttura) {
    this.nome = nome;
    this.idStruttura = idStruttura;
  }

  public AmbulatoriBean() {}

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

  public int getIdStruttura() {
    return idStruttura;
  }

  public void setIdStruttura(int idStruttura) {
    this.idStruttura = idStruttura;
  }

  @Override
  public String toString() {
    return "AmbulatoriBean{"
        + "id="
        + id
        + ", nome='"
        + nome
        + '\''
        + ", idStruttura="
        + idStruttura
        + '}';
  }
}
