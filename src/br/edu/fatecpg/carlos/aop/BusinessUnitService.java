package br.edu.fatecpg.carlos.aop;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class BusinessUnitService {

    private Map<String, BusinessUnit> businessUnits;

    public BusinessUnitService() {
        businessUnits = new HashMap<String, BusinessUnit>();
    }

    /**
     * Pega todas as business units. Pointcut definido aqui
     * para popular o valor de retorno.
     * @return
     */
    public Collection findAllBusinessUnits() {
        return businessUnits.values();
    }

    public BusinessUnit getBusinessUnit(String id) {
        return businessUnits.get(id);
    }

    public Map<String, BusinessUnit> getBusinessUnits() {
        return businessUnits;
    }
}
