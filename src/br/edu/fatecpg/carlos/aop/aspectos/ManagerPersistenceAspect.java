package br.edu.fatecpg.carlos.aop.aspectos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import br.edu.fatecpg.carlos.aop.Manager;
import br.edu.fatecpg.carlos.aop.ManagerFactory;
import br.edu.fatecpg.carlos.aop.ManagerService;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
@Aspect
public class ManagerPersistenceAspect {

    private BufferedReader buffFileReader;

    public ManagerPersistenceAspect() {
    }

    @Before("execution(* br.edu.fatecpg.carlos.aop.ManagerService.findAllManagers(..))")
    public void findAllManagers(JoinPoint joinPoint) throws Throwable {
    	
    	assignReader();
    	
        System.out.println("Advice 'findAllManagers' chamado");
        Map<String, Manager> gerentes = ((ManagerService)joinPoint.getThis()).getGerentes();
        EmployeePersistenceAspect employeeAspect = new EmployeePersistenceAspect();
        Manager dummyManager = ManagerFactory.createManager();
        String managerRecord;
        while((managerRecord = buffFileReader.readLine()) != null) {
            ManagerFactory.popularAtributosGerente(dummyManager, managerRecord);
            String employeeRecord = employeeAspect.findEmployeeRecordById(dummyManager.getId());
            Manager manager = ManagerFactory.criarGerente(managerRecord, employeeRecord);
            gerentes.put(manager.getId(), manager);
        }
        
        releaseReader();
        
    }

    public void setBufferedReader(BufferedReader buffFileReader) {
        System.out.println("'ManagerPersistenceAspect.setBufferedReader' chamado");
        this.buffFileReader = buffFileReader;
    }

    public BufferedReader getBufferedReader() {
        System.out.println("'ManagerPersistenceAspect.getBufferedReader' chamado");
        return this.buffFileReader;
    }
    
    public void assignReader() throws Throwable {
        String fileName = BufferedFileReaderAspect.fileNames.get(ManagerPersistenceAspect.class);
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        setBufferedReader(bufferedReader);
    }

    public void releaseReader() throws Throwable {
        getBufferedReader().close();
        setBufferedReader(null);
    }

}
