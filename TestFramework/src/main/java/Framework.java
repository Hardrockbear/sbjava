import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

public class Framework {
    private static int testCount = 0, failCount = 0;

    public static void run(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor();
        testCount = clazz.getDeclaredMethods().length;

        List<Method> beforeMethods = getAnnotatedMethods(clazz, Before.class);
        List<Method> testMethods = getAnnotatedMethods(clazz, Test.class);
        List<Method> afterMethods = getAnnotatedMethods(clazz, After.class);

        for (Method method : testMethods) {
            Object core = constructor.newInstance();

            //Before
            beforeMethods.forEach(m -> runMethod(m, core));

            //Test
            runMethod(method, core);

            //After
            afterMethods.forEach(m -> runMethod(m, core));

            System.out.println("---------------------------------");
        }

        System.out.println(String.format("Всего тестов: %s",testCount));
        System.out.println(String.format("Успешных тестов: %s",testCount - failCount));
        System.out.println(String.format("Тестов с ошибкой: %s",failCount));
    }

    private static void runMethod (Method method, Object object) {
        try {
            method.invoke(object);
            System.out.println(String.format("Метод %s выполнен успешно!",method.getName()));
        } catch (Exception e) {
            System.out.println(String.format("Метод %s выполнен c ошибкой!",method.getName()));
            failCount++;
        }
    }

    private static List<Method> getAnnotatedMethods (Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }
}
