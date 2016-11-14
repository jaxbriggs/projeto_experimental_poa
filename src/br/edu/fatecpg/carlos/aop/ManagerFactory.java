package br.edu.fatecpg.carlos.aop;

import java.util.StringTokenizer;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class ManagerFactory extends EmployeeFactory {

    public static Manager createManager() {
        return new Manager();
    }

    public static Manager criarGerente(String managerRecord, String employeeRecord) {
        Manager manager = createManager();
        populateEmployeeAttributes(manager, employeeRecord);
        popularAtributosGerente(manager, managerRecord);
        return manager;
    }

    public static Manager criarGerente(String managerRecord) {
        Manager manager = createManager();
        popularAtributosGerente(manager, managerRecord);
        return manager;
    }

    public static void popularAtributosGerente(Manager manager, String managerRecord) {
        if(managerRecord == null) {
            throw new IllegalArgumentException("String inválida passada para a criação de registro de gerente");
        }
        StringTokenizer managerAttributes = new StringTokenizer(managerRecord, ",");

        if(managerAttributes.countTokens() != 3) {
            throw new IllegalArgumentException("String inválida passada para a criação de registro de gerente");
        }

        manager.setId(managerAttributes.nextToken().trim());
        manager.setProjetoGerenciado(managerAttributes.nextToken().trim());
    }
}