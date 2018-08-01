package tradiationalsingleton;

/**
 * 这个也是IDEA默认创建singleton时自动生成的代码，但仍然存在{@link UnsafeSingleton}中的隐患
 *
 * 静态工厂方法的优势在于其灵活性：在API不变的前提下，我们可以改变该类是否具有Singleton属性。
 * 比如，我们可以根据变化的需求将其更改为每个调用该方法的线程拥有唯一的实例
 * //其实好像还有其他优势，参见《Effective Java》，但内容在比较靠后的位置，暂时先不管
 *
 * 对于序列化单例的说明见{@link serizablesingleton.UnexpectedSingleton}
 * 和{@link serizablesingleton.SerializableSingleton}
 *
 * @author LightDance
 */
public class UnsafeSingleton2 {

    private static final UnsafeSingleton2 ourInstance = new UnsafeSingleton2();

    public static UnsafeSingleton2 getInstance() {
        return ourInstance;
    }

    public void leaveTheBuilding(){
        System.out.println("Whoa baby, I'm outta here!");
    }

    private UnsafeSingleton2() {
        super();
    }
}
