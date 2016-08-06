package com.spring_ehcache.app.cache;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BinaryOperator;

public class KeyGeneratorByMethodName implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        String methodName = method.getName() + "_";
        if (params == null) return methodName;
        else {
            return methodName + getParamsHashCode(params);
        }
    }

    private String getParamsHashCode(Object[] params) {
        return Arrays.stream(params)
                .filter(Objects::nonNull)
                .map(Object::hashCode)
                .map(String::valueOf)
                .reduce(joinStrings())
                .orElse("");
    }

    private BinaryOperator<String> joinStrings() {
        return (param1, param2) -> param1 + param2;
    }

}
