package pool.mythreadpool;

/**
 * Created by ZHAOTING001 on 2018/4/11.
 */
public interface MyTask {
    void execute();

    byte[] getResult();
}
