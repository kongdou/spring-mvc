package fun.deepsky.springmvc.first.annotation;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLogAspect {
    //@Pointcut(value="execution(public * fun.deepsky..*(..))")
	@Pointcut(value="@annotation(fun.deepsky.springmvc.first.annotation.MyLog)")
	private void pointcut() {
		System.out.println("............");
	}
	
	@Before(value="pointcut()")
	public void doBefore() {
		 System.out.println("doBefore");
	}
	
	@Around(value="pointcut() && @annotation(myLog)")
	 public Object around(ProceedingJoinPoint point, MyLog myLog) {
		System.out.println("++++执行了around方法++++");
        String requestUrl = myLog.requestUrl();
        //拦截的类名
        Class clazz = point.getTarget().getClass();
        //拦截的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        System.out.println("执行了 类:" + clazz + " 方法:" + method + " 自定义请求地址:" + requestUrl);

        try {
            return point.proceed(); //执行程序
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
	 }
	
	/**
     * 方法执行后
     *
     * @param joinPoint
     * @param myLog
     * @param result
     * @return
     */
    @AfterReturning(value = "pointcut() && @annotation(myLog)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, MyLog myLog, Object result) {


        System.out.println("++++执行了afterReturning方法++++");

        System.out.println("执行结果：" + result);

        return result;
    }

    /**
     * 方法执行后 并抛出异常
     *
     * @param joinPoint
     * @param myLog
     * @param ex
     */
    @AfterThrowing(value = "pointcut() && @annotation(myLog)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, MyLog myLog, Exception ex) {
        System.out.println("++++执行了afterThrowing方法++++");
        System.out.println("请求：" + myLog.requestUrl() + " 出现异常");
    }
    
    @After(value="pointcut() && @annotation(myLog)") 
    public void doAfter(JoinPoint joinPoint,MyLog myLog) {
    	Class clazz = joinPoint.getTarget().getClass();
    	//拦截的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

    	System.out.println("doAfter:"+clazz.getName()+" 方法："+method+"   url:"+myLog.requestUrl());
    }
}
