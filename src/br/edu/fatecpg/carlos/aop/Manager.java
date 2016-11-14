package br.edu.fatecpg.carlos.aop;


/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class Manager extends Employee {

    private String projetoGerenciado;

    Manager() {
        projetoGerenciado = "NAOEXISTE";
    }

    public String getProjetoGerenciado() {
        return projetoGerenciado;
    }

    public void setProjetoGerenciado(String managingProject) {
        this.projetoGerenciado = managingProject;
    }

    public String toString() {
        return super.toString();
    }
}
