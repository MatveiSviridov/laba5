package com.laba5;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class laba5 {
    public static void main (String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        System.out.println("properties:");
        String path = "properties.txt";
        SomeBean sb = new Injector(path).inject(new SomeBean());
        sb.foo();

        System.out.println("properties2:");
        String path2 = "properties2.txt";
        sb = new Injector(path2).inject(new SomeBean());
        sb.foo();
    }
}
