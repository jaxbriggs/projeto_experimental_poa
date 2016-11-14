package br.edu.fatecpg.carlos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.edu.fatecpg.carlos.aop.BusinessUnit;
import br.edu.fatecpg.carlos.aop.BusinessUnitService;
import br.edu.fatecpg.carlos.aop.Employee;
import br.edu.fatecpg.carlos.aop.EmployeeService;
import br.edu.fatecpg.carlos.aop.Manager;
import br.edu.fatecpg.carlos.aop.ManagerService;
import br.edu.fatecpg.carlos.aop.aspectos.BusinessUnitConstants;
import br.edu.fatecpg.carlos.aop.aspectos.EmployeeConstants;
import br.edu.fatecpg.carlos.aop.aspectos.ManagerConstants;

public class MainApp {
	
	public static ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	
	private static Employee employee;
	private static EmployeeService employeeService;

	public static void main(String[] args) throws IOException {
		
		employeeService = (EmployeeService)context.getBean("employeeService");
		
		configuracaoInicial();
		alterarPontuacaoEmpregadoTeste();
		//criarEmpregadoTeste(context);
		
	}
	
	public static void configuracaoInicial() throws IOException {
        // Cria as Business Units necessárias para manipular os registros 
        File businessUnitsFile = new File(BusinessUnitConstants.FILE_NAME);
        businessUnitsFile.delete();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(businessUnitsFile));
            pw.println("1, Setor Bancário");
            pw.println("2, Telecomunicações");
            pw.println("3, Corporativo");
        } finally {
            if(pw != null) {
                pw.close();
            }
        }

        File employeesFile = new File(EmployeeConstants.FILE_NAME);
        employeesFile.delete();

        File managersFile = new File(ManagerConstants.FILE_NAME);
        managersFile.delete();

        try {
            pw = new PrintWriter(new FileWriter(employeesFile));
            pw.println("1, Maria, 99185-6492, -1, 1, 2");
            pw.println("2, João, 3487-5412, -1, 1, -1");
        } finally {
            if(pw != null) {
                pw.close();
            }
        }

        try {
            pw = new PrintWriter(new FileWriter(managersFile));
            pw.println("1, Antônio, Projeto 1");
            pw.println("2, Garcia, Projeto 2");
        } finally {
            if(pw != null) {
                pw.close();
            }
        }

    }
	
	public static void criarEmpregadoTeste(ApplicationContext context) {
        BusinessUnit businessUnit = null;
        Manager manager = null;
        
        //Simulação de escolha de business units disponíveis
        System.out.println("Procurando Business Units");
        BusinessUnitService businessUnitService = (BusinessUnitService)context.getBean("businessUnitService");
        
        Collection businessUnits = businessUnitService.findAllBusinessUnits();
        System.out.println("Business Units:" + businessUnits);
        Iterator businessUnitsIterator = businessUnits.iterator();
        if(businessUnitsIterator.hasNext()) {
            businessUnit = (BusinessUnit)businessUnitsIterator.next();
        }
        
        //Simulação de escolha de gerentes disponíveis
        ManagerService managerService = (ManagerService)context.getBean("managerService");
        
        Collection managers = managerService.findAllManagers();
        System.out.println("Managers:" + managers);
        Iterator managersIterator = managers.iterator();
        if(managersIterator.hasNext()) {
            manager = (Manager)managersIterator.next();
        }
        
        //Cria um novo empregado
        employee = employeeService.createEmployee("Carlos Henrique", "99171-5485", businessUnit, manager);
        
        //Log
        employee.toString();
        
        //Persiste o novo empregado
        File employeesFile = new File(EmployeeConstants.FILE_NAME);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(employeesFile,true));
            String emp_str = employee.getId() + ", " 
            			   + employee.getNome() + ", " 
            		       + employee.getNumeroContato() + ", " 
            			   + "-1, 1, 2";
            pw.append(emp_str);
        } catch (IOException ex) {
        	ex.printStackTrace();
        } finally {
            if(pw != null) {
                pw.close();
            }
        }
        
	}
	
    public static void alterarPontuacaoEmpregadoTeste() {
        criarEmpregadoTeste(context);
        System.out.println("Pontos antes: " + employee.getPontos());
        employeeService.darPontos(employee.getId(), 5);
        System.out.println("Pontos depois: " + employee.getPontos());
    }

}
