package com.github.smdj.marusei.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AccountDetailsMethodHandlerArgumentResolverImpl implements AccountDetailsMethodHandlerArgumentResolver {
    private static final Logger log = LoggerFactory.getLogger(AccountDetailsMethodHandlerArgumentResolverImpl.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (log.isTraceEnabled()) {
            log.trace("args : parameter={}", parameter);
        }

        return AccountDetails.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (log.isTraceEnabled()) {
            log.trace("args : parameter={}, mavContainer={}, webRequest={}, binderFactory={}", parameter, mavContainer, webRequest, binderFactory);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        if (AccountDetails.class.isAssignableFrom(principal.getClass())) {
            return (AccountDetails) principal;
        }

        return null;
    }
}
