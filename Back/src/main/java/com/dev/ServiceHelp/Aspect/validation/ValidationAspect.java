package com.dev.ServiceHelp.Aspect.validation;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspect {

    @Before("@annotation(com.dev.ServiceHelp.Aspect.annotations.Validation)")
    public void validarRegra(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            log.warn("Usuário {} tentou acessar método {} sem permissão", auth != null ? auth.getName() : "ANÔNIMO", joinPoint.getSignature());
            throw new SecurityException("Acesso negado. Usuário não possui permissão necessária.");
        }
    }
}
