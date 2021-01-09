package ru.otus.logging;

import java.util.Arrays;
import java.util.Objects;

public class LoggingMethodMetaInfo {
    private final String methodName;
    private final Class<?>[] methodParams;

    public LoggingMethodMetaInfo(String methodName, Class<?>[] methodParams) {
        this.methodName = methodName;
        this.methodParams = methodParams;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getMethodParams() {
        return methodParams == null ? null : methodParams.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggingMethodMetaInfo that = (LoggingMethodMetaInfo) o;
        return methodName.equals(that.methodName) && Arrays.equals(methodParams, that.methodParams);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(methodName);
        result = 31 * result + Arrays.hashCode(methodParams);
        return result;
    }
}
