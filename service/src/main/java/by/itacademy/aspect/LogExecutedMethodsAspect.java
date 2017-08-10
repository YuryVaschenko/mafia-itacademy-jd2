package by.itacademy.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * Created by Yury V. on 18.07.17.
 */

@Aspect
public class LogExecutedMethodsAspect {

    private final Logger logger = Logger.getLogger(LogExecutedMethodsAspect.class);

    @Pointcut("@within(LogInvokedMethods)")
    public void logInvokedMethodsAnnotation() {
    }

    @Around("logInvokedMethodsAnnotation()")
    public Object logMethodInvoke(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature().getName() + " - method called.");
        logger.info(Arrays.toString(joinPoint.getArgs()) + " - args of method.");
        Object proceed = joinPoint.proceed();
        logger.info(joinPoint.getSignature().getName() + " - execution is done.");
        return proceed;
    }

}
