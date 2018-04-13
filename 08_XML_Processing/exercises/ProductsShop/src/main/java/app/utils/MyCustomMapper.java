package app.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class MyCustomMapper {

    private MyCustomMapper() {
    }

    public static <T, P> T map(Class<T> cl, P object) {

        Constructor<T> constructor = null;
        try {
            constructor = cl.getConstructor(object.getClass());
            constructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

        try {
            return constructor.newInstance(object);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}


