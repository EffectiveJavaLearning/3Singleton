package serizablesingleton;

import java.io.*;

/**
 * 这样的代码经过序列化、反序列化之后，单例的唯一性再次出现问题:本应是同一对象的判别出现了问题（见最后一句）
 *
 * 问题出在哪里？——反序列化的ObjectInputStream类。
 * 这个类的{@link ObjectInputStream#readOrdinaryObject(boolean)}方法中会判断是否可以实例化，
 * 如果可以的话，会通过反射机制调用无参的构造方法创建新实例，而不去管是不是private的
 *
 * 为了应对这一状况，得在需要单例属性的类里面添加readResolve方法，原理解释见{@link SerializableSingleton}
 *
 * @author LightDance
 */
public class UnexpectedSingleton implements Serializable{

    private static UnexpectedSingleton ourInstance = new UnexpectedSingleton();

    public static UnexpectedSingleton getInstance() {
        return ourInstance;
    }

    private UnexpectedSingleton() {
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //write
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempfile"));
        oos.writeObject(UnexpectedSingleton.getInstance());
        //read
        File file = new File("tempfile");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        UnexpectedSingleton deserializedSingleton = (UnexpectedSingleton) ois.readObject();
        System.out.println(deserializedSingleton == UnexpectedSingleton.getInstance());
        //false
    }
}

