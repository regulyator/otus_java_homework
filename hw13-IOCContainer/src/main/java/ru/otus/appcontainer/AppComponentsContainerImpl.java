package ru.otus.appcontainer;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.appcontainer.exceptions.ComponentInitializeException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    public AppComponentsContainerImpl(Class<?>... configClasses) {
        Arrays.stream(configClasses)
                .filter(aClass -> aClass.isAnnotationPresent(AppComponentsContainerConfig.class))
                .sorted(Comparator.comparingInt(value -> value.getAnnotation(AppComponentsContainerConfig.class).order()))
                .forEach(this::processConfig);
    }

    public AppComponentsContainerImpl(String configPackage) {
        new Reflections(configPackage, new SubTypesScanner(), new TypeAnnotationsScanner())
                .getTypesAnnotatedWith(AppComponentsContainerConfig.class)
                .stream().sorted(Comparator.comparingInt(value -> value.getAnnotation(AppComponentsContainerConfig.class).order()))
                .forEach(this::processConfig);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        createComponents(configClass, Arrays.stream(configClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(AppComponent.class).order()))
                .collect(Collectors.toUnmodifiableList()));
        ;
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponents.stream()
                .filter(o -> componentClass.isAssignableFrom(o.getClass()))
                .findFirst().orElse(null);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.getOrDefault(componentName, null);
    }

    private void createComponents(Class<?> configClass, List<Method> configComponents) {
        if (!configComponents.isEmpty()) {
            try {
                Object configInstance = configClass.getConstructor().newInstance();
                configComponents.forEach(method -> {
                    instanceAndAddComponent(configInstance, method);

                });
            } catch (Exception ex) {
                throw new RuntimeException("Error when create component from config!");
            }
        }
    }

    private void instanceAndAddComponent(Object configInstance, Method componentMethod) {
        try {
            int componentDependencyCount = componentMethod.getParameterCount();
            Object[] resultDependencies = new Object[componentDependencyCount];
            if (componentDependencyCount > 0) {
                Class<?>[] componentDependenciesTypes = componentMethod.getParameterTypes();
                for (int i = 0; i < componentDependencyCount; i++) {
                    Object dependencyInstance = getAppComponent(componentDependenciesTypes[i]);
                    if (Objects.isNull(dependencyInstance)) {
                        throw new ComponentInitializeException(String.format("Error when create component instance! No required dependency!  %s", componentDependenciesTypes[i]));
                    }
                    resultDependencies[i] = getAppComponent(componentDependenciesTypes[i]);
                }
            }
            Object componentInstance = componentMethod.invoke(configInstance, resultDependencies);
            appComponents.add(componentInstance);
            appComponentsByName.put(componentMethod.getAnnotation(AppComponent.class).name(), componentInstance);
        } catch (Exception ex) {
            throw new ComponentInitializeException(String.format("Error when create component instance!  %s", componentMethod.getAnnotation(AppComponent.class).name()));
        }
    }
}
