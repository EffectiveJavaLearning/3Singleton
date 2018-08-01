package serizablesingleton;

import java.io.*;

/**
 * 将单例变成可序列化的，仅仅加上implements Serializable还不够，需要声明所有实例域都是瞬时的，
 * 并且提供readResolve方法，否则每次对序列化实例进行反序列化时，都会创建一个新的实例
 * @see UnexpectedSingleton 可以去这个类查看反序列化的结果
 *
 * 因为反序列化操作的{@link ObjectInputStream#readOrdinaryObject(boolean)}里面，最靠下的位置上，有一行if判断：
 * {@link ObjectStreamClass#hasReadResolveMethod}，检查有没有readResolve方法，有的话调用一下。
 * {@link SerializableSingleton#readResolve()}里面应该返回单例并通知GC处理掉假冒的实例
 * 然后运行会发现结果变回了true
 *
 * 在Java1.5之后，由于枚举类Enum的引入，出现了单例的第三种写法{@link enumsingleton.EnumSingleton}
 *
 * @author LightDance
 */

public class SerializableSingleton implements Serializable {
    private static SerializableSingleton ourInstance = new SerializableSingleton();

    public static SerializableSingleton getInstance() {
        return ourInstance;
    }

    private SerializableSingleton() {
        super();
    }

    private Object readResolve(){
        //TODO let the garbage collector take care of the SerializableSingleton impersonator
        return ourInstance;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //write
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempfile2"));
        oos.writeObject(SerializableSingleton.getInstance());
        //read
        File file = new File("tempfile2");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        SerializableSingleton deserializedSingleton = (SerializableSingleton) ois.readObject();
        System.out.println(deserializedSingleton == SerializableSingleton.getInstance());
        //true
    }
}
