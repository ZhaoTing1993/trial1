package proxy;

/**
 * 业务实现类
 * Created by ZHAOTING001 on 2018/8/16.
 */
public class BookFacadeImpl implements BookFacade {
    @Override
    public void addBook() {
        System.out.println("a book is added...");
    }
}
