package com.asgarov;

import java.io.Console;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MyCustomClassLoader extends ClassLoader {

    private Bean bean;

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if(bean.getClassName().contains(name)) {
            return Class.forName(bean.getClassName());
        }
        return super.loadClass(name);
    }

    public Object getBean(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> aClass = loadClass(name);
        Constructor constructor = getConstructor(aClass);
        constructor.setAccessible(true);
        return constructor.newInstance(bean.getArguments().values().stream().findFirst().orElseThrow());
    }

    private Constructor getConstructor(Class clazz) {
        Constructor[] ctors = clazz.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == bean.getArguments().size())
                return ctor;
        }
        throw new RuntimeException();
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }
}
