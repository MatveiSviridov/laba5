package com.laba5;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Injector {
    private String propertiesPath;

    /**
     * Getter for propertiesPath
     * @return propertiesPath
     */
    public String getPropertiesPath() {
        return propertiesPath;
    }

    /**
     * Setter for propertiesPath
     * @param propertiesPath path of the properties file
     */
    public void setPropertiesPath(String propertiesPath) {
        this.propertiesPath = propertiesPath;
    }

    /**
     * Class constructor
     * @param propertiesPath path of the properties file
     */

    public Injector(String propertiesPath){
        this.propertiesPath = propertiesPath;
    }

    /**
     * The class that implements dependency injection into any object that contains fields marked with an annotation.
     * @param object the object in which the fields will be initialized
     * @param <T> object type
     * @return an object with initialized fields
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T inject(T object) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(Injector.class.getClassLoader().getResourceAsStream(propertiesPath));
        for(Field field:object.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(AutoInjectable.class)){
                field.setAccessible(true);
                String objectName = properties.getProperty(field.getType().getCanonicalName());
                Object instance = Class.forName(objectName).getConstructor().newInstance();
                field.set(object, instance);
            }
        }
        return object;
    }
}
