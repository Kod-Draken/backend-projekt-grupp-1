package se.yrgo.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * This class is an AspectJ-tool for clocking method-times during operation
 * @author Alrik, Mattias, Najib
 */
public class PerformanceTimingAdvice {

    public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable {
        //before
        long startTime = System.currentTimeMillis();
        try {
            //proceed to target
            Object value = method.proceed();
            return value;
        } finally {
            //after
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;
            System.out.println("Time taken for the method " + method.getSignature().getName() + " from the " + method.getTarget().getClass() + " took " + timeTaken + "ms");
        }
    }
}
