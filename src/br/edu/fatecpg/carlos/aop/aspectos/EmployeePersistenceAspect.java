package br.edu.fatecpg.carlos.aop.aspectos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.aspectj.lang.annotation.Aspect;

import br.edu.fatecpg.carlos.aop.Employee;
import br.edu.fatecpg.carlos.aop.EmployeeFactory;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
@Aspect
public class EmployeePersistenceAspect {

    private BufferedReader buffFileReader;

    protected String findEmployeeRecordById(String id) throws IOException, Throwable {
    	
    	assignReader();
    	
        Employee dummyEmployee = EmployeeFactory.createEmployee();
        String employeeRecord;
        while((employeeRecord=buffFileReader.readLine()) != null) {
            EmployeeFactory.populateEmployeeAttributes(dummyEmployee, employeeRecord);
            if(id.equals(dummyEmployee.getId())) {
                break;
            }
            employeeRecord = null;
        }
        
        releaseReader();
        
        return employeeRecord;
    }

    public void setBufferedReader(BufferedReader buffFileReader) {
        System.out.println("'EmployeePersistenceAspect.setBufferedReader' chamado");
        this.buffFileReader = buffFileReader;
    }

    public BufferedReader getBufferedReader() {
        System.out.println("'EmployeePersistenceAspect.getBufferedReader' chamado");
        return this.buffFileReader;
    }
    
    public void assignReader() throws Throwable {
        String fileName = BufferedFileReaderAspect.fileNames.get(EmployeePersistenceAspect.class);
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        setBufferedReader(bufferedReader);
    }

    public void releaseReader() throws Throwable {
        getBufferedReader().close();
        setBufferedReader(null);
    }
}
