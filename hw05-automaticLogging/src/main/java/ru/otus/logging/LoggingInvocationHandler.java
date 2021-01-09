package ru.otus.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoggingInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInvocationHandler.class);
    private final Object loggedObject;
    private final Set<LoggingMethodMetaInfo> loggedMethodsInfos = new HashSet<>();

    public LoggingInvocationHandler(Object loggedObject) {
        this.loggedObject = loggedObject;
        scanClass();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (checkIsLoggedMethod(method)) {
            LOGGER.info("Executed method: {} with params: {}",
                    method.getName(),
                    Stream.of(args).map(String::valueOf).collect(Collectors.joining(", ")));
        }
        return method.invoke(loggedObject, args);
    }

    private void scanClass() {
        Arrays.stream(loggedObject.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Log.class))
                .forEach(method -> loggedMethodsInfos.add(getMethodMetaInfo(method)));
    }

    private boolean checkIsLoggedMethod(Method method) {
        return loggedMethodsInfos.contains(getMethodMetaInfo(method));
    }

    private LoggingMethodMetaInfo getMethodMetaInfo(Method method) {
        return new LoggingMethodMetaInfo(
                method.getName(), method.getParameterTypes()
        );
    }
}
