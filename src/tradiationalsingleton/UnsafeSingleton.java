package tradiationalsingleton;

/**
 * 这种方式看似安全，实则暗藏杀机。拥有特权的客户端可以通过AccessibleObject.setAccessible()
 * 通过反射机制来调用其私有构造器、访问私有变量私有或者使用私有方法，
 * 除非修改构造方法的逻辑，让它在试图创建第二个实例的时候抛出异常{@link ModifyPrivateDataTest}
 *
 * 其实也有第二种方式，对外提供静态工厂方法实现的单例{@link UnsafeSingleton2}
 *
 * 两者相比较，公有域的优势在于可读性好，final的存在确保所有的对象引用都是相同的，
 * 但其在性能上不具有任何优势，因为现代JVM机制几乎都可以将静态工厂方法的调用内联化
 *      注：调用的内联化(inline)，不再保存现场跳转函数执行结束后返回，
 *      而是直接将函数执行过程放在调用它的地方，尤其适用于小型的方法.
 *
 * @author LightDance
 */
public class UnsafeSingleton {

    public static final UnsafeSingleton OUR_INSTANCE = new UnsafeSingleton();

    public void leaveTheBuilding(){
        System.out.println("Whoa baby, I'm outta here!");
    }
    private UnsafeSingleton() {
        super();
    }

    public static void main(String[] args) {
        UnsafeSingleton.OUR_INSTANCE.leaveTheBuilding();
    }
}
