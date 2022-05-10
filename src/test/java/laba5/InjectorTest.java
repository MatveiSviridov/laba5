package laba5;

import com.laba5.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class InjectorTest {

    @org.junit.jupiter.api.Test
    void inject() throws NoSuchFieldException, NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Field field1 = SomeBean.class.getDeclaredField("field1");
        Field field2 = SomeBean.class.getDeclaredField("field2");
        field1.setAccessible(true);
        field2.setAccessible(true);

        String path = "properties.txt";
        SomeBean sb = new Injector(path).inject(new SomeBean());
        assertTrue(field1.get(sb) instanceof SomeImpl);
        assertTrue(field2.get(sb) instanceof SODoer);


        String path2 = "properties2.txt";
        sb = new Injector(path2).inject(new SomeBean());
        assertTrue(field1.get(sb) instanceof OtherImpl);
        assertTrue(field2.get(sb) instanceof SODoer);
    }
}