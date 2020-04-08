package code.controller;


import code.domain.SysLog;
import code.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ISysLogService sysLogService;

    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法


    /**
     * 前置通知
     * 获取开始时间、执行的类是哪一个、访问的方法是哪一个
     */
    @Before("execution(* code.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        startTime = new Date();//当前时间就是开始访问时间
        executionClass = jp.getTarget().getClass();//具体要访问的类
       String methodName = jp.getSignature().getName();//获取访问的方法名
        Object[] args = jp.getArgs();//获取访问方法的参数

        //获取method对象
        if (args == null|| args.length == 0){
            executionMethod = executionClass.getMethod(methodName);
        }else {
            //有参数----解析参数的类，就将args中所有元素遍历，获取对应的Class，装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++){
                classArgs[i] = args[i].getClass();
            }
            //获取有参数的方法对象
            executionMethod = executionClass.getMethod(methodName,classArgs);
        }

    }

    /**
     * 后置通知
     */
    @After("execution(* code.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //1. 获取访问时长
        Long executionTime = new Date().getTime() - startTime.getTime();

        //2.获取url
        String url ="";
        if (executionClass != null && executionMethod !=null && executionClass != LogAop.class){
            RequestMapping classAnnotation = (RequestMapping)executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                String[] classValue = classAnnotation.value();
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }
        //3.获取访问的ip地址(request对象)
        String ip = httpServletRequest.getRemoteAddr();

        //4.获取当前操作的用户

        //4.1 可以通过securityContext获取
        //4.2 也可以从request.getSession中获取,request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();


        //5.将信息封装到sysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] "+executionClass.getName()+"[方法名] "+executionMethod.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(startTime);
        // 调用Service，调用dao将sysLog insert数据库
        sysLogService.save(sysLog);



    }

}
