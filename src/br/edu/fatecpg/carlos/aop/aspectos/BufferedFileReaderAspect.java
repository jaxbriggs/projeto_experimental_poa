package br.edu.fatecpg.carlos.aop.aspectos;

import java.util.Map;
import java.util.HashMap;

import org.aspectj.lang.annotation.Aspect;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
@Aspect
public class BufferedFileReaderAspect {

    public static Map<Class, String> fileNames;

    public BufferedFileReaderAspect() {
        System.out.println("BufferedFileReaderAspect criado");
        fileNames = new HashMap<Class, String>();
        fillFileNames();
    }

    private void fillFileNames() {
        fileNames.put(BusinessUnitPersistenceAspect.class, BusinessUnitConstants.FILE_NAME);
        fileNames.put(EmployeePersistenceAspect.class, EmployeeConstants.FILE_NAME);
        fileNames.put(ManagerPersistenceAspect.class, ManagerConstants.FILE_NAME);
    }
}
