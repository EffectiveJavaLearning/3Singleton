package enumsingleton;

/**
 * 与{@link serizablesingleton.SerializableSingleton},
 *   {@link tradiationalsingleton.UnsafeSingleton}和
 *   {@link tradiationalsingleton.UnsafeSingleton2}相比，
 * 单元素枚举类型由于其: 功能完整、使用简洁、无偿地提供了序列化机制、
 * 面对复杂的序列化或反射攻击时仍然可以绝对防止多次实例化等优点，
 * 单元素的枚举类型被《Effective Java》的作者认为是实现Singleton的最佳方法
 *
 * Enum序列化机制简介：在序列化的时候Java仅仅是将枚举对象的name属性输出到结果中，
 * 反序列化的时候则是通过 java.lang.Enum 的 valueOf() 方法来根据名字查找枚举对象。
 *
 * @author LightDance
 */

public enum EnumSingleton {
    /**
     * Enum实现单例
     */
    INSTANCE;

    private B myB = null;

    private EnumSingleton(){
        myB = new B();
    }
    public B getB(){
        System.out.println("哔哔哔");
        return myB;
    }
}
