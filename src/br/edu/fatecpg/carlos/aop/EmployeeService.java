package br.edu.fatecpg.carlos.aop;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
public class EmployeeService {

    private static Random employeeIdGenerator;

    private Map<String, Employee> employees;

    public EmployeeService() {
        employees = new HashMap<String, Employee>();
    }

    public Employee createEmployee(String name, String contactNumber, BusinessUnit businessUnit, Manager manager) {
        String id = createNewEmployeeId();
        Employee employee = EmployeeFactory.createEmployee(id, name, contactNumber, businessUnit, manager);
        employees.put(id, employee);
        return employee;
    }

    public void darPontos(String id, int point) {
        Employee employee = findEmployee(id);
        employee.darPontos(point);
    }

    public Employee findEmployee(String id) {
        return employees.get(id);
    }

    public Collection findAllEmployees() {
        return employees.values();
    }

    public static String createNewEmployeeId() {
        if(employeeIdGenerator == null) {
            employeeIdGenerator = new Random(1000);
        }
        return String.valueOf(employeeIdGenerator.nextInt());
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

}
