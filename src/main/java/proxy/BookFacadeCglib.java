package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by ZHAOTING001 on 2018/8/16.
 */
public class BookFacadeCglib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target; //给业务对象赋值
        Enhancer enhancer = new Enhancer();//创建加强器，用来创建动态代理类
        enhancer.setSuperclass(this.target.getClass()); //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦截
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    // 实现回调方法
    @Override
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before processing...");
        methodProxy.invokeSuper(target, objects);
        System.out.println("after processing...");
        return null;
    }

    public static void main(String[] args) {
        BookFacade bookFacade = new BookFacadeImpl();
        BookFacadeCglib bookFacadeCglib = new BookFacadeCglib();
        BookFacade bookFacadeCglibInstance = (BookFacade) bookFacadeCglib.getInstance(bookFacade);
        bookFacadeCglibInstance.addBook();
    }
}
