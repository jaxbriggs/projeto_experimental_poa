package br.edu.fatecpg.carlos.aop.aspectos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import br.edu.fatecpg.carlos.aop.BusinessUnit;
import br.edu.fatecpg.carlos.aop.BusinessUnitFactory;
import br.edu.fatecpg.carlos.aop.BusinessUnitService;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
@Aspect
public class BusinessUnitPersistenceAspect {
	
    private BufferedReader buffFileReader;

    public BusinessUnitPersistenceAspect() {
    	
    }

    @Before(value="execution(* br.edu.fatecpg.carlos.aop.BusinessUnitService.findAllBusinessUnits(..))")
    public void findAllBusinessUnits(JoinPoint joinPoint) throws Throwable {
    	
    	assignReader();
    	
        System.out.println("Advice 'findAllBusinessUnits' chamado");
        
        Map<String, BusinessUnit> businessUnits = ((BusinessUnitService)joinPoint.getThis()).getBusinessUnits();
        String businessUnitRecord;
        
        while((businessUnitRecord = buffFileReader.readLine()) != null) {
            BusinessUnit businessUnit = BusinessUnitFactory.createBusinessUnit(businessUnitRecord);
            businessUnits.put(businessUnit.getId(), businessUnit);
        }
        
        releaseReader();
    }

    public void setBufferedReader(BufferedReader buffFileReader) {
        System.out.println("'BusinessUnitPersistenceAspect.setBufferedReader' chamado");
        this.buffFileReader = buffFileReader;
    }

    public BufferedReader getBufferedReader() {
        System.out.println("'BusinessUnitPersistenceAspect.getBufferedReader' chamado");
        return this.buffFileReader;
    }
    
    public void assignReader() throws Throwable {
        String fileName = BufferedFileReaderAspect.fileNames.get(BusinessUnitPersistenceAspect.class);
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        setBufferedReader(bufferedReader);
    }

    public void releaseReader() throws Throwable {
        getBufferedReader().close();
        setBufferedReader(null);
    }
}
