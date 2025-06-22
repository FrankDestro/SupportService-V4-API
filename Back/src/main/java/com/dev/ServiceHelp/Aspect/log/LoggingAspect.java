package com.dev.ServiceHelp.Aspect.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Pointcut("execution(* com.dev.ServiceHelp.services..*(..))")
    public void serviceMethods() {}

    @Pointcut("@annotation(com.dev.ServiceHelp.Aspect.annotations.Loggable)")
    public void annotatedWithLoggable() {}

    @Before("annotatedWithLoggable()")
    public void logBefore(JoinPoint joinPoint) {
        String user = getAuthenticatedUser();
        String method = joinPoint.getSignature().toShortString();
        String timestamp = LocalDateTime.now().format(formatter);
        Object[] args = joinPoint.getArgs();

        log.info("➡️ [{}] Usuário: {} | Iniciando: {} | Args: {}", timestamp, user, method, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "annotatedWithLoggable()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String user = getAuthenticatedUser();
        String method = joinPoint.getSignature().toShortString();
        String timestamp = LocalDateTime.now().format(formatter);

        log.info("✅ [{}] Usuário: {} | Finalizado: {} | Retorno: {}", timestamp, user, method, result.toString());
    }

    @AfterThrowing(pointcut = "annotatedWithLoggable()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String user = getAuthenticatedUser();
        String method = joinPoint.getSignature().toShortString();
        String timestamp = LocalDateTime.now().format(formatter);

        log.error("❌ [{}] Usuário: {} | Erro em: {} | Mensagem: {}", timestamp, user, method, ex.getMessage(), ex.toString());
    }


    @Around("annotatedWithLoggable()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        log.info("⏱️ Método {} executado em {} ms", joinPoint.getSignature().toShortString(), executionTime);
        return proceed;
    }

    private String getAuthenticatedUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (authentication != null && authentication.isAuthenticated())
                    ? authentication.getName()
                    : "ANÔNIMO";
        } catch (Exception e) {
            return "DESCONHECIDO";
        }
    }
}
