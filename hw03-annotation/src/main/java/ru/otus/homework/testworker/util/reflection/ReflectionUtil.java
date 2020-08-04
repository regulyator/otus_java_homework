package ru.otus.homework.testworker.util.reflection;

import ru.otus.homework.exeptions.TestClassInstantiationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * класс хэлпер
 */
public class ReflectionUtil {

    /**
     * проверяем есть ли у класса дефолтный конструктор
     *
     * @param clazz класс
     * @return возвращает true - если есть дефолтный конструктор, иначе - false
     */
    public static boolean checkForDefaultConstructor(Class<?> clazz) {
        try {
            clazz.getConstructor();
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * проверяем не имеет ли метод параметров
     *
     * @param method метод
     * @return возвращает true - если нет параметров, иначе - false
     */
    public static boolean checkMethodHaveNoParams(Method method) {
        return method.getParameterCount() == 0;
    }

    /**
     * пробуем инстанцировать класс
     *
     * @param clazz класс
     * @return экзэмпляр класса
     * @throws TestClassInstantiationException
     */
    public static Object getClassInstance(Class<?> clazz) throws TestClassInstantiationException {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new TestClassInstantiationException("", e);
        }
    }

    /**
     * пробуем вызвать метод
     *
     * @param method        метод
     * @param classInstance экзэмпляр класса
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void invokeMethod(Method method, Object classInstance) throws InvocationTargetException, IllegalAccessException {
        method.invoke(classInstance);
    }

}
