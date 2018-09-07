package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ZHAOTING001 on 2018/8/16.
 */
public class BookFacadeProxy implements InvocationHandler {
    private Object target;

    public BookFacadeProxy(){

    }

    public BookFacadeProxy(Object target) {
        this.target = target;
    }

    public Object bind(Object target) {
        this.target = target;
        //创建一个代理对象并返回，用户进行业务调用时直接执行该代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before processing...");
        Object result = method.invoke(target, args);
        System.out.println("after processing...");
        return result;
    }

    public static void main(String[] args) {
        BookFacadeImpl bookFacadeImpl = new BookFacadeImpl();
        BookFacadeProxy bookFacadeProxy = new BookFacadeProxy();
        BookFacade bookFacade = (BookFacade) bookFacadeProxy.bind(bookFacadeImpl);
        bookFacade.addBook();
    }
}
