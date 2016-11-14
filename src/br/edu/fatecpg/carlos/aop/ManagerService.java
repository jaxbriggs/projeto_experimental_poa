package br.edu.fatecpg.carlos.aop;

import java.util.*;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class ManagerService extends EmployeeService {

    private Map<String, Manager> gerentes;

    public ManagerService() {
        gerentes = new HashMap<String, Manager>();
    }

    public Collection findAllManagers() {
        return gerentes.values();
    }

    public Manager getGerente(String id) {
        return (Manager)gerentes.get(id);
    }

    public Map<String, Manager> getGerentes() {
        return gerentes;
    }

}
