package tradiationalsingleton;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * 一般情况下，我们并不能对类的私有字段进行操作，利用反射也不例外，但有时候，例如要序列化时，
 * 我们又必须有能力去处理这些字段，可以用AccessibleObject.setAccessible()方法来允许这种访问，
 * 而由于反射类中的Field，Method和Constructor继承自AccessibleObject，
 * 因此，通过在这些类上调用setAccessible()方法，可以实现对这些字段的操作。
 *
 * 但有的时候这将会成为一个安全隐患，为此，我们可以启用java.security.manager
 * 来判断程序是否具有调用setAccessible()的权限。
 * 默认情况下，内核API和扩展目录的代码具有该权限，而类路径或通过URLClassLoader加载的程序没有。
 *
 * @author LightDance
 */
public class ModifyPrivateDataTest {
    public static void main(String[] args) {
        A a1 = new A();
        Field[] fields = a1.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        try {
            a1.printData();
            fields[0].setInt(a1, 150);
            a1.printData();
        } catch (IllegalAccessException | IllegalArgumentException ex1) {
            ex1.printStackTrace();
        }
    }
}