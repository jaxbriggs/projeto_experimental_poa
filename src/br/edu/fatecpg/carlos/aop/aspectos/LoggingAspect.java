package br.edu.fatecpg.carlos.aop.aspectos;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * TODO
 *
 * @author Carlos Henrique
 */
@Aspect
public class LoggingAspect {

    @Around("execution(* br.edu.fatecpg.carlos.aop..*.toString(..))")
    
    public Object toStringAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("'toStringAdvice' chamado");
        String toString = (String)joinPoint.proceed();
        Object target = joinPoint.getThis();
        Field fields[] = target.getClass().getDeclaredFields();
        List members = new ArrayList(fields.length + 1);
        members.add(toString);
        for(Field field : fields) {
            field.setAccessible(true);
            Object member = field.get(target);
            members.add(field.getName() + "=" + member);
        }
        return members.toString();
    }
    
}
