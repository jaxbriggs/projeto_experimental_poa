package br.edu.fatecpg.carlos.aop;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class BusinessUnit {
    private String id;
    private String nome;

    BusinessUnit() {
        id = "NAOEXISTE";
        nome = "NAOEXISTE";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public String toString() {
        return super.toString();
    }
}
