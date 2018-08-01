package enumsingleton;

/**
 * Enum单例的测试类，序列化部分省略
 *
 * @author LightDance
 */
public class EnumSingletonTest {

    public static void main(String[] args) {

        B testB1 = EnumSingleton.INSTANCE.getB();
        B testB2 = EnumSingleton.INSTANCE.getB();
        System.out.println(testB1 == testB2);
    }
}
