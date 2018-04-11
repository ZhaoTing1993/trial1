package pool;

/**
 * 池接口
 * Created by ZHAOTING001 on 2018/4/11.
 */
public interface Pool {
    MyExecutor getExecutor();

    void destroy();
}
